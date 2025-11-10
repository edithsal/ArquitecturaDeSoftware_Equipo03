
package Main;

/**
 *
 * @author LENOVO
 */
import Blackboard.FrmBlackboard;
import Controlador.JuegoControlador;
import Modelo.Casilla;
import Modelo.Ficha;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import Modelo.Tablero;
import Vista.PantallaTablero;
import java.util.ArrayList;
import Socket.Cliente;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Edith 
 */
public class ClienteMain {

    public static void main(String[] args) {

       String ipServidor = "192.168.1.12"; 
       int puerto = 3333; 
        
        
        Cliente cliente = new Cliente();
        cliente.conectar("192.168.1.12", 3333);
        
        List<Casilla> casillas = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            casillas.add(new Casilla(i)); // construye cada casilla con un id
        }
       
           Juego juego = new Juego(); // <-- mover aquí
    Tablero tablero = new Tablero(casillas);
    juego.setTablero(tablero);
        
        Ficha ficha1 = new Ficha("Rojo");
        Ficha ficha2 = new Ficha("Azul");
        
        juego.agregarJugador(new Jugador("Jugador 1", "Rojo", Arrays.asList(ficha1)));
        juego.agregarJugador(new Jugador("Jugador 2", "Azul", Arrays.asList(ficha2)));
        juego.iniciarJuego();

        PantallaTablero vista = new PantallaTablero();
        

        ReglasJuego reglas = new ReglasJuego();
        JuegoControlador controlador = new JuegoControlador(juego, vista, reglas, cliente);
        controlador.iniciarJuego();
        vista.setVisible(true);
        
        vista.setTitle("Jugador 2 - Cliente");
    }
}
