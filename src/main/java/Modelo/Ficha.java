/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */


public class Ficha {
    private String color;
    private Casilla posicion;
    private boolean enBase;

    public Ficha(String color) {
        this.color = color;
        this.enBase = true;
    }

    public void avanzar(Casilla casilla) {
        this.posicion = casilla;
        this.enBase = false;
    }

    public void capturar() {
        this.enBase = true;
        this.posicion = null;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Casilla getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla posicion) {
        this.posicion = posicion;
    }

    public boolean isEnBase() {
        return enBase;
    }

    public void setEnBase(boolean enBase) {
        this.enBase = enBase;
    }
}

