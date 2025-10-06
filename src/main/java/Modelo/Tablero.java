/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */

import java.util.List;

/**
 *
 * @author valen
 */
public class Tablero {
    private List<Casilla> casillas;

    public Tablero(List<Casilla> casillas) {
        this.casillas = casillas;
    }

    public void actualizarPosicion(Ficha ficha, Casilla nuevaCasilla) {
        ficha.avanzar(nuevaCasilla);
    }

    public Casilla obtenerCasilla(int id) {
        for (Casilla c : casillas) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<Casilla> casillas) {
        this.casillas = casillas;
    }
}
