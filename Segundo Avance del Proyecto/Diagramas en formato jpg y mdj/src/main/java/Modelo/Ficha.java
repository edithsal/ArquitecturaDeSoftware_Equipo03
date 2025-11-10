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
    private boolean enBase;
    private boolean enMeta;
    private Casilla posicion;

    public Ficha(String color) {
        this.color = color;
        this.enBase = true;
        this.enMeta = false;
        this.posicion = null;
    }

    // Mueve la ficha a una nueva casilla
    public void avanzar(Casilla nuevaCasilla) {
        this.posicion = nuevaCasilla;
        this.enBase = false;
    }

    // Devuelve la ficha a la base
    public void capturar() {
        this.posicion = null;
        this.enBase = true;
        this.enMeta = false;
    }

    // --- Getters y Setters ---
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEnBase() {
        return enBase;
    }

    public void setEnBase(boolean enBase) {
        this.enBase = enBase;
    }

    public boolean isEnMeta() {
        return enMeta;
    }

    public void setEnMeta(boolean enMeta) {
        this.enMeta = enMeta;
    }

    public Casilla getPosicion() {
        return posicion;
    }

    public void setPosicion(Casilla posicion) {
        this.posicion = posicion;
    }

    // Para mover mediante reglas
    public void moverA(Casilla casilla) {
        this.posicion = casilla;
        this.enBase = false;
    }
}

