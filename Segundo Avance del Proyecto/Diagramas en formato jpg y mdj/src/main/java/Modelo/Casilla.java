/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */

public class Casilla {
    
    private int id;
    private Ficha ocupadaPor;

    public Casilla(int id) {
        this.id = id;
        this.ocupadaPor = null;
    }

    public int getId() {
        return id;
    }

    public boolean estaOcupada() {
        return ocupadaPor != null;
    }

    public Ficha getOcupadaPor() {
        return ocupadaPor;
    }

    public void setOcupadaPor(Ficha ficha) {
        this.ocupadaPor = ficha;
    }
    
    public boolean esMeta() {
        return id >= 50;
    }
}
