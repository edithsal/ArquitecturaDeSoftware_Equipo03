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
    }

    public boolean estaOcupada() {
        return ocupadaPor != null;
    }
    
    public boolean esMeta() { 
        return false; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ficha getOcupadaPor() {
        return ocupadaPor;
    }

    public void setOcupadaPor(Ficha ocupadaPor) {
        this.ocupadaPor = ocupadaPor;
    }
}
