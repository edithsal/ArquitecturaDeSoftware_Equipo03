/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.util.List;

/**
 *
 * @author Edith
 */

public class Jugador {
    private String nombre;
    private String colorFicha;
    private List<Ficha> fichas;

    public Jugador(String nombre, String colorFicha, List<Ficha> fichas) {
        this.nombre = nombre;
        this.colorFicha = colorFicha;
        this.fichas = fichas;
    }

    public int tirarDado() {
        return (int)(Math.random() * 6) + 1;
    }

    public void moverFicha(Ficha ficha, Casilla casilla) {
        ficha.avanzar(casilla);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColorFicha() {
        return colorFicha;
    }

    public void setColorFicha(String colorFicha) {
        this.colorFicha = colorFicha;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }
}
