/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import Socket.Servidor;

/**
 *
 * @author Edith
 */
public class ServidorMain {
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor(5000);

        Juego juego = new Juego();
        juego.agregarJugador(new Jugador("Jugador 1", "Rojo", new ArrayList<>()));
        juego.agregarJugador(new Jugador("Jugador 2", "Azul", new ArrayList<>()));
        juego.iniciarJuego();
        PantallaTablero vista = new PantallaTablero();
        ReglasJuego reglas = new ReglasJuego();
        JuegoControlador controlador = new JuegoControlador(juego, vista, reglas, servidor);
        controlador.iniciarJuego();
        vista.setVisible(true);

        System.out.println("Servidor listo. Esperando que el jugador 2 se conecte...");
    }
}
