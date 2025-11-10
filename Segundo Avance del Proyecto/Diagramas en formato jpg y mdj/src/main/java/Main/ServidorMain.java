/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import Socket.Servidor;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Edith
 */
public class ServidorMain {
    
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor(3333);

        Juego juego = new Juego();

        List<Casilla> casillas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            casillas.add(new Casilla(i));
        }
        Tablero tablero = new Tablero(casillas);

        juego.setTablero(tablero);

        Ficha ficha1 = new Ficha("Rojo");
        Ficha ficha2 = new Ficha("Azul");

        juego.agregarJugador(new Jugador("Jugador 1", "Rojo", Arrays.asList(ficha1)));
        juego.agregarJugador(new Jugador("Jugador 2", "Azul", Arrays.asList(ficha2)));
        juego.iniciarJuego();
        
        PantallaTablero vista = new PantallaTablero();
        ReglasJuego reglas = new ReglasJuego();

        JuegoControlador controlador = new JuegoControlador(juego, vista, reglas, servidor);
        controlador.iniciarJuego();

        vista.setVisible(true);
        vista.setTitle("Jugador 1 - Servidor");
        vista.getBtnTirarDado().setEnabled(true);

        System.out.println("Servidor listo. Esperando que el jugador 2 se conecte...");
    }
}
