/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author valen
 */
public class Juego {
    private List<Jugador> jugadores;
    private Jugador jugadorActual;
    private Dado dado;
    private ReglasJuego reglas;
    private int turnoActual;
    private Tablero tablero; 

    public Juego() {
        jugadores = new ArrayList<>();
        dado = new Dado();
        reglas = new ReglasJuego();
        turnoActual = 0;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public ReglasJuego getReglas() {
        return reglas;
    }

    public void setReglas(ReglasJuego reglas) {
        this.reglas = reglas;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void iniciarJuego() {
        if (jugadores.isEmpty()) {
            jugadorActual = null;
            return;
        }
        turnoActual = 0;
        jugadorActual = jugadores.get(turnoActual);
    }

    public void siguienteTurno() {
        if (jugadores.isEmpty()) return;
        turnoActual = (turnoActual + 1) % jugadores.size();
        jugadorActual = jugadores.get(turnoActual);
    }

    public int lanzarDado() {
        return dado.lanzar();
    }
}
