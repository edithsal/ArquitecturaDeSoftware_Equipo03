/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ReglasJuego {
    private int maxSeisConsecutivos = 3;
    private boolean obligarComer = true;
    private boolean salidaCon5 = true;
    private boolean limite3Tiros6 = true;


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

    public boolean puedeMover(Ficha ficha, int dado, Tablero tablero) {
        if (ficha.isEnMeta()) return false;
        if (ficha.isEnBase() && dado != 5) return false; 
        return true;
    }

    public void moverFicha(Ficha ficha, int dado, Tablero tablero, Jugador jugador) {
        List<Casilla> casillas = tablero.getCasillas();

        if (ficha.isEnBase() && dado == 5) {
            Casilla salida = casillas.get(0);
            aplicarReglaCaptura(ficha, salida);
            ficha.moverA(salida);
            salida.setOcupadaPor(ficha);
            System.out.println(jugador.getNombre() + " sacó una ficha!");
            return;
        }

        if (ficha.getPosicion() == null) return;

        int nuevaPos = ficha.getPosicion().getId() + dado;
        if (nuevaPos >= casillas.size() - 1) {
            ficha.setEnMeta(true);
            System.out.println("Ficha de " + jugador.getNombre() + " llegó a meta!");
            return;
        }

        Casilla nuevaCasilla = casillas.get(nuevaPos);
        aplicarReglaCaptura(ficha, nuevaCasilla);
        ficha.moverA(nuevaCasilla);
        nuevaCasilla.setOcupadaPor(ficha);
    }

    public void aplicarReglaCaptura(Ficha ficha, Casilla casilla) {
        if (casilla.estaOcupada() && casilla.getOcupadaPor() != ficha) {
            Ficha otra = casilla.getOcupadaPor();
            otra.capturar();
            casilla.setOcupadaPor(null);
            System.out.println("💥 Ficha comida!");
        }
    }

    public boolean validarMovimiento(Ficha ficha, Casilla destino) {
        return destino == null || !destino.estaOcupada() || destino.getOcupadaPor() != ficha;
    }

    public boolean puedeSacarFicha(Jugador jugador) {
        for (Ficha f : jugador.getFichas()) {
            if (f.isEnBase()) return true;
        }
        return false;
    }
}
