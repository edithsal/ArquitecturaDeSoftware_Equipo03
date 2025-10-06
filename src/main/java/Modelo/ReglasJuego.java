/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author LENOVO
 */
public class ReglasJuego {
    private int maxSeisConsecutivos;
    private boolean obligarComer;
    private boolean salidaCon5;
    private boolean limite3Tiros6;

    public ReglasJuego() {
        maxSeisConsecutivos = 3;
        obligarComer = true;
        salidaCon5 = true;
        limite3Tiros6 = true;
    }

    public int getMaxSeisConsecutivos() {
        return maxSeisConsecutivos;
    }

    public void setMaxSeisConsecutivos(int maxSeisConsecutivos) {
        this.maxSeisConsecutivos = maxSeisConsecutivos;
    }

    public boolean isObligarComer() {
        return obligarComer;
    }

    public void setObligarComer(boolean obligarComer) {
        this.obligarComer = obligarComer;
    }

    public boolean isSalidaCon5() {
        return salidaCon5;
    }

    public void setSalidaCon5(boolean salidaCon5) {
        this.salidaCon5 = salidaCon5;
    }

    public boolean isLimite3Tiros6() {
        return limite3Tiros6;
    }

    public void setLimite3Tiros6(boolean limite3Tiros6) {
        this.limite3Tiros6 = limite3Tiros6;
    }
    
    public boolean validarMovimiento(Ficha ficha, Casilla casilla) {
        return true; 
    }

    public boolean puedeSacarFicha(Jugador jugador) {
        return true; 
    }

    public void aplicarReglaCaptura(Ficha ficha, Casilla casilla) {
        if (casilla.estaOcupada()) {
            casilla.getOcupadaPor().capturar(); 
        }
        casilla.setOcupadaPor(ficha); 
    }
}
