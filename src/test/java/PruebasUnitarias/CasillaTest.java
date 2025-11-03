/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebasUnitarias;

/**
 *
 * @author LENOVO
 */


import Modelo.Casilla;
import Modelo.Ficha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CasillaTest {

    @Test
    public void testCasillaInicializacion() {
        System.out.println("Iniciando test: testCasillaInicializacion");
        Casilla casilla = new Casilla(5);
        System.out.println("Casilla creada con ID = " + casilla.getId());
        assertEquals(5, casilla.getId());
        assertFalse(casilla.estaOcupada());
        System.out.println("Casilla inicial no esta ocupada.\n");
    }

    @Test
    public void testOcuparCasilla() {
        System.out.println("Iniciando test: testOcuparCasilla");
        Casilla casilla = new Casilla(10);
        Ficha ficha = new Ficha("Rojo");
        casilla.setOcupadaPor(ficha);
        System.out.println("Ficha de color " + ficha.getColor() + " ocupa la casilla " + casilla.getId());
        assertTrue(casilla.estaOcupada());
        assertEquals(ficha, casilla.getOcupadaPor());
        System.out.println("Casilla marcada como ocupada correctamente.\n");
    }

    @Test
    public void testDesocuparCasilla() {
        System.out.println("Iniciando test: testDesocuparCasilla");
        Casilla casilla = new Casilla(3);
        Ficha ficha = new Ficha("Verde");
        casilla.setOcupadaPor(ficha);
        System.out.println("Casilla ocupada por ficha verde.");
        casilla.setOcupadaPor(null);
        assertFalse(casilla.estaOcupada());
        System.out.println("Casilla desocupada correctamente.\n");
    }

    @Test
    public void testEsMetaSiempreFalse() {
        System.out.println("Iniciando test: testEsMetaSiempreFalse");
        Casilla casilla = new Casilla(1);
        assertFalse(casilla.esMeta());
        System.out.println("Casilla no es meta (por diseño del juego).\n");
    }
}
