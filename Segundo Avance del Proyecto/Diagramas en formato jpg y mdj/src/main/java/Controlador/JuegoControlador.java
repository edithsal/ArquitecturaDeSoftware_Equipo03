package Controlador;

import Modelo.Ficha;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import Socket.Cliente;
import Socket.MensajeJuego;
import Socket.Servidor;
import Vista.PantallaTablero;
import Blackboard.Blackboard;
import Blackboard.FrmBlackboard;
import ProtocoloDispatch.BlackboardListener;

import javax.swing.SwingUtilities;

/**
 * Controlador principal del juego.
 * implementa BlackboardListener para reaccionar a los mensajes del Blackboard.
 * 
 * @author Emma
 */
public class JuegoControlador implements BlackboardListener {

    private Juego juego;
    private PantallaTablero vista;
    private ReglasJuego reglas;
    private Servidor servidor;
    private Cliente cliente;
    private boolean soyServidor = false;
    private String miNombre;


    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        inicializarBlackboard();
        inicializarEventos();
    }

    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas, Servidor servidor) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        this.servidor = servidor;
        this.soyServidor = true;
        this.miNombre = "Jugador 1"; // <-- marcar nombre local
        inicializarBlackboard();
        inicializarEventos();
        escucharMensajes();
    }

    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas, Cliente cliente) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        this.cliente = cliente;
        this.soyServidor = false;
        this.miNombre = "Jugador 2"; // <-- marcar nombre local
        inicializarBlackboard();
        inicializarEventos();
        escucharMensajes();
    }

    private void inicializarBlackboard() {
        FrmBlackboard frm = new FrmBlackboard();
        frm.setVisible(true);

        Blackboard bb = Blackboard.getInstancia();
        bb.setVista(frm);
        bb.agregarListener(this); 

        bb.publicar("Pizarra lista. Esperando movimientos...");
    }

    private void inicializarEventos() {
        vista.getBtnTirarDado().addActionListener(e -> lanzarDado());
    }



    private void lanzarDado() {
        int resultado = juego.lanzarDado();
        Jugador jugadorActual = juego.getJugadorActual();

        if (jugadorActual.getFichas() == null || jugadorActual.getFichas().isEmpty()) {
            String msg = "El jugador " + jugadorActual.getNombre() + " no tiene fichas asignadas.";
            vista.mostrarMensaje(msg);
            Blackboard.getInstancia().publicar(msg);
            return;
        }

        Ficha fichaAMover = jugadorActual.getFichas().get(0);
        int idCasillaActual = fichaAMover.getPosicion() == null ? -1 : fichaAMover.getPosicion().getId();
        int idCasillaNueva = Math.min(idCasillaActual + resultado, juego.getTablero().getCasillas().size() - 1);

        fichaAMover.avanzar(juego.getTablero().obtenerCasilla(idCasillaNueva));
        vista.moverFicha(fichaAMover, idCasillaNueva);
        vista.actualizarTurnoYResultado(jugadorActual.getNombre(), resultado);

        Blackboard.getInstancia().publicar(
            jugadorActual.getNombre() + " saco " + resultado + " y movio ficha a casilla " + idCasillaNueva
        );

        MensajeJuego mensaje = new MensajeJuego();
        mensaje.setTipo("DADO");
        mensaje.setJugador(jugadorActual.getNombre());
        mensaje.setValorDado(resultado);
        mensaje.setIdCasilla(idCasillaNueva);

        if (soyServidor) servidor.enviar(mensaje);
        else cliente.enviar(mensaje);

        String siguienteTurno;
        if (resultado != 6) {
            int idxActual = juego.getJugadores().indexOf(jugadorActual);
            int idxSiguiente = (idxActual + 1) % juego.getJugadores().size();
            siguienteTurno = juego.getJugadores().get(idxSiguiente).getNombre();
            juego.setJugadorActual(juego.getJugadores().get(idxSiguiente));

            String msgTurno = "Turno de: " + siguienteTurno;
            vista.mostrarMensaje(msgTurno);
            vista.setTurnoJugador(siguienteTurno);
            Blackboard.getInstancia().publicar(msgTurno);
        } else {
            siguienteTurno = jugadorActual.getNombre();
            String msgSeis = "" + jugadorActual.getNombre() + " saco un 6! Juega de nuevo.";
            vista.mostrarMensaje(msgSeis);
            vista.setTurnoJugador(jugadorActual.getNombre());
            Blackboard.getInstancia().publicar(msgSeis);
        }

        MensajeJuego mensajeTurno = new MensajeJuego();
        mensajeTurno.setTipo("TURNO");
        mensajeTurno.setJugador(siguienteTurno);

        if (soyServidor) servidor.enviar(mensajeTurno);
        else cliente.enviar(mensajeTurno);

        vista.getBtnTirarDado().setEnabled(resultado == 6);
    }

   

    private void escucharMensajes() {
        if (soyServidor) servidor.escucharMensajes(this::actualizarDesdeMensaje);
        else cliente.escucharMensajes(this::actualizarDesdeMensaje);
    }

    private void actualizarDesdeMensaje(MensajeJuego mensaje) {
        System.out.println("[Cliente recibe mensaje] " + mensaje.getTipo() + " de " + mensaje.getJugador());
        SwingUtilities.invokeLater(() -> {
            switch (mensaje.getTipo()) {
                case "DADO":
                    vista.actualizarTurnoYResultado(mensaje.getJugador(), mensaje.getValorDado());
                    Blackboard.getInstancia().publicar("Recibido: " + mensaje.getJugador() + " tiro " + mensaje.getValorDado());
                    break;

                case "TURNO":
                    juego.setJugadorActual(
                        juego.getJugadores().stream()
                            .filter(j -> j.getNombre().equals(mensaje.getJugador()))
                            .findFirst()
                            .orElse(juego.getJugadorActual())
                    );

                    vista.setTurnoJugador(mensaje.getJugador());
                    String msgTurno = "Turno de: " + mensaje.getJugador();
                    vista.mostrarMensaje(msgTurno);
                    Blackboard.getInstancia().publicar(msgTurno);

                    boolean esMiTurno = miNombre != null 
                        && mensaje.getJugador().trim().equalsIgnoreCase(miNombre.trim());
                    vista.getBtnTirarDado().setEnabled(esMiTurno);
                    System.out.println("Mi nombre local: [" + miNombre + "] -> Mensaje TURNO para: [" + mensaje.getJugador() + "]");
                    System.out.println("¿Es mi turno?: " + esMiTurno);

                    vista.getBtnTirarDado().setEnabled(esMiTurno);
                    System.out.println("Se habilita botón tirar dado: " + esMiTurno);
                    break;
            }
        });
    }


    @Override
    public void onMensajePublicado(String mensaje) {
       
        if (mensaje.contains("gano")) {
            vista.mostrarMensaje("¡Se detecto un ganador!");
        }

        System.out.println("[JuegoControlador escucha Blackboard] " + mensaje);
    }


    public void iniciarJuego() {
        vista.mostrarMensaje("El juego ha comenzado.");
        vista.actualizarTurnoYResultado(juego.getJugadorActual().getNombre(), 0);

        // habilitar el boton si soy yo el que inicia
        boolean esMiTurnoInicial = miNombre != null && miNombre.equals(juego.getJugadorActual().getNombre());
        vista.getBtnTirarDado().setEnabled(esMiTurnoInicial);

        Blackboard.getInstancia().publicar("El juego ha comenzado. Turno de " + juego.getJugadorActual().getNombre());
    }
}
