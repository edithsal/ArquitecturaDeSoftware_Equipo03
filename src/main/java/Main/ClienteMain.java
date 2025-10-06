
package Main;

/**
 *
 * @author LENOVO
 */
import Controlador.JuegoControlador;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import Vista.PantallaTablero;
import java.util.ArrayList;
import Socket.Cliente;

/**
 *
 * @author Edith 
 */
public class ClienteMain {

    public static void main(String[] args) {

       String ipServidor = "192.168.1.3"; 
       int puerto = 5000; 
        
        
        Cliente cliente = new Cliente();
        cliente.conectar("localhost", 5000);

        Juego juego = new Juego();
        juego.agregarJugador(new Jugador("Jugador 1", "Rojo", new ArrayList<>()));
        juego.agregarJugador(new Jugador("Jugador 2", "Azul", new ArrayList<>()));
        juego.iniciarJuego();

        PantallaTablero vista = new PantallaTablero();
        ReglasJuego reglas = new ReglasJuego();
        JuegoControlador controlador = new JuegoControlador(juego, vista, reglas, cliente);
        controlador.iniciarJuego();
        vista.setVisible(true);
    }
}
