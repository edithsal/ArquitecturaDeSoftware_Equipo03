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

/**
 *
 * @author valen
 */
public class Juego {
    private List<Jugador> jugadores;
    private Tablero tablero;
    private Jugador turnoActual;
    private Dado dado;
    private ReglasJuego reglas;

    public Juego() {
        this.dado = new Dado();
        this.reglas = new ReglasJuego();
        this.tablero = new Tablero(new ArrayList<>()); 
        this.jugadores = new ArrayList<>();
        this.turnoActual = null;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
        System.out.println("Jugador agregado: " + jugador.getNombre());
    }

    public void iniciarJuego() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para iniciar el juego.");
            return;
        }
        turnoActual = jugadores.get(0);
        System.out.println("El juego ha iniciado. Turno de: " + turnoActual.getNombre());
    }

    public int lanzarDado() {
        return dado.lanzar();
    }

    public void siguienteTurno() {
        if (jugadores.isEmpty()) return;
        int indiceActual = jugadores.indexOf(turnoActual);
        turnoActual = jugadores.get((indiceActual + 1) % jugadores.size());
        System.out.println("Turno de: " + turnoActual.getNombre());
    }

    public Jugador getJugadorActual() {
        return turnoActual;
    }

    public void setJugadorActual(Jugador jugador) {
        this.turnoActual = jugador;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public ReglasJuego getReglas() {
        return reglas;
    }

    public void setReglas(ReglasJuego reglas) {
        this.reglas = reglas;
    }
}
