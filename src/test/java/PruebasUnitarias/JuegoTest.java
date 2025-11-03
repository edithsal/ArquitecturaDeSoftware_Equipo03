/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebasUnitarias;

/**
 *
 * @author LENOVO
 */

import Modelo.Dado;
import Modelo.Juego;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    @Test
    public void testAgregarJugador() {
        System.out.println("Agregar Jugador");
        Juego juego = new Juego();
        Jugador jugador = new Jugador("Juan", "Rojo", new ArrayList<>());
        juego.agregarJugador(jugador);

        System.out.println("Jugador agregado: " + jugador.getNombre() + " (" + jugador.getColorFicha() + ")");
        System.out.println("Jugadores totales: " + juego.getJugadores().size());

        assertEquals(1, juego.getJugadores().size());
        assertEquals("Juan", juego.getJugadores().get(0).getNombre());
    }

    @Test
    public void testIniciarJuegoSinJugadores() {
        System.out.println("Iniciar Juego sin jugadores");
        Juego juego = new Juego();
        juego.iniciarJuego();
        System.out.println("Jugador actual: " + juego.getJugadorActual());
        assertNull(juego.getJugadorActual());
    }

    @Test
    public void testIniciarJuegoConJugadores() {
        System.out.println("Iniciar Juego con jugadores");
        Juego juego = new Juego();
        Jugador j1 = new Jugador("Emma", "Verde", new ArrayList<>());
        juego.agregarJugador(j1);

        System.out.println("Jugadores agregados: " + juego.getJugadores().size());
        juego.iniciarJuego();

        System.out.println("Jugador actual al iniciar: " + juego.getJugadorActual().getNombre());
        assertEquals(j1, juego.getJugadorActual());
    }

    @Test
    public void testSiguienteTurno() {
        System.out.println("Ciclo de Turnos");
        Juego juego = new Juego();
        Jugador j1 = new Jugador("A", "Rojo", new ArrayList<>());
        Jugador j2 = new Jugador("B", "Azul", new ArrayList<>());
        juego.agregarJugador(j1);
        juego.agregarJugador(j2);
        juego.iniciarJuego();

        System.out.println("Turno inicial: " + juego.getJugadorActual().getNombre());
        juego.siguienteTurno();
        System.out.println("Despues del primer cambio: " + juego.getJugadorActual().getNombre());
        assertEquals(j2, juego.getJugadorActual());

        juego.siguienteTurno();
        System.out.println("Despues del segundo cambio (vuelve al primero): " + juego.getJugadorActual().getNombre());
        assertEquals(j1, juego.getJugadorActual());
    }

    @Test
    public void testLanzarDado() {
        System.out.println("Lanzar Dado");
        Juego juego = new Juego();
        int valor = juego.lanzarDado();
        System.out.println("Valor obtenido del dado: " + valor);
        assertTrue(valor >= 1 && valor <= 6);
    }

    @Test
    public void testSettersYGetters() {
        System.out.println("Setters y Getters de Juego");
        Juego juego = new Juego();
        ReglasJuego reglas = new ReglasJuego();
        Dado dado = new Dado();

        juego.setReglas(reglas);
        juego.setDado(dado);

        System.out.println("Reglas establecidas: " + (juego.getReglas() != null));
        System.out.println("Dado asignado correctamente: " + (juego.getDado() != null));

        assertEquals(reglas, juego.getReglas());
        assertEquals(dado, juego.getDado());
    }
}


