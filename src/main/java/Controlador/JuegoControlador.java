
package Controlador;

/**
 *
 * @author LENOVO
 */
import Modelo.Ficha;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import Socket.Cliente;
import Socket.MensajeJuego;
import Socket.Servidor;
import Vista.PantallaTablero;
/**
 *
 * @author valen
 */
public class JuegoControlador {
    private Juego juego;
    private PantallaTablero vista;
    private ReglasJuego reglas;
    private Servidor servidor;
    private Cliente cliente;
    private boolean soyServidor;

    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        inicializarEventos();
    }

    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas, Servidor servidor) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        this.servidor = servidor;
        this.soyServidor = true;
        inicializarEventos();
        escucharMensajes();
    }

    public JuegoControlador(Juego juego, PantallaTablero vista, ReglasJuego reglas, Cliente cliente) {
        this.juego = juego;
        this.vista = vista;
        this.reglas = reglas;
        this.cliente = cliente;
        this.soyServidor = false;
        inicializarEventos();
        escucharMensajes();
    }

    private void inicializarEventos() {
        vista.getBtnTirarDado().addActionListener(e -> lanzarDado());
    }

    private void lanzarDado() {
    int resultado = juego.lanzarDado();
    Jugador jugadorActual = juego.getJugadorActual();

    if (jugadorActual.getFichas() == null || jugadorActual.getFichas().isEmpty()) {
        vista.mostrarMensaje("⚠️ El jugador " + jugadorActual.getNombre() + " no tiene fichas asignadas.");
        System.out.println("Error: jugador sin fichas -> " + jugadorActual.getNombre());
        return;
    }

    Ficha fichaAMover = jugadorActual.getFichas().get(0);

    int idCasillaActual = fichaAMover.getPosicion() == null ? -1 : fichaAMover.getPosicion().getId();
    int idCasillaNueva = idCasillaActual + resultado;

    if (idCasillaNueva >= juego.getTablero().getCasillas().size()) {
        idCasillaNueva = juego.getTablero().getCasillas().size() - 1;
    }

    fichaAMover.avanzar(juego.getTablero().obtenerCasilla(idCasillaNueva));
    vista.moverFicha(fichaAMover, idCasillaNueva);

    vista.actualizarTurnoYResultado(jugadorActual.getNombre(), resultado);

    MensajeJuego mensaje = new MensajeJuego();
    mensaje.setTipo("DADO");
    mensaje.setJugador(jugadorActual.getNombre());
    mensaje.setValorDado(resultado);

    try {
        if (soyServidor) servidor.enviar(mensaje);
        else cliente.enviar(mensaje);
    } catch (Exception e) {
        e.printStackTrace();
    }

    if (resultado != 6) {
        juego.siguienteTurno();
    } else {
        vista.mostrarMensaje("¡Sacaste un 6! Otro turno");
    }
}


    private void escucharMensajes() {
        if (soyServidor) {
            servidor.escucharMensajes(mensaje -> actualizarDesdeMensaje(mensaje));
        } else {
            cliente.escucharMensajes(mensaje -> actualizarDesdeMensaje(mensaje));
        }
    }

    private void actualizarDesdeMensaje(MensajeJuego mensaje) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            vista.actualizarTurnoYResultado(mensaje.getJugador(), mensaje.getValorDado());
            juego.siguienteTurno();
        });
    }

    public void iniciarJuego() {
        vista.mostrarMensaje("El juego ha comenzado.");
        vista.actualizarTurnoYResultado(juego.getJugadorActual().getNombre(), 0);
    }
}

