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

public class FichaTest {

    @Test
    public void testFichaInicialEnBase() {
        System.out.println("Iniciando test: testFichaInicialEnBase");
        Ficha ficha = new Ficha("Azul");
        System.out.println("Ficha creada con color: " + ficha.getColor());
        assertTrue(ficha.isEnBase());
        assertNull(ficha.getPosicion());
        System.out.println("Ficha comienza correctamente en base sin posicion.\n");
    }

    @Test
    public void testAvanzar() {
        System.out.println("Iniciando test: testAvanzar");
        Ficha ficha = new Ficha("Rojo");
        Casilla casilla = new Casilla(1);
        ficha.avanzar(casilla);
        System.out.println("Ficha " + ficha.getColor() + " avanza a casilla ID: " + casilla.getId());
        assertEquals(casilla, ficha.getPosicion());
        assertFalse(ficha.isEnBase());
        System.out.println("Ficha avanzo correctamente.\n");
    }

    @Test
    public void testCapturar() {
        System.out.println("Iniciando test: testCapturar");
        Ficha ficha = new Ficha("Verde");
        Casilla casilla = new Casilla(2);
        ficha.avanzar(casilla);
        System.out.println("Ficha verde avanza a casilla " + casilla.getId());
        ficha.capturar();
        System.out.println("Ficha capturada y regresada a base.");
        assertTrue(ficha.isEnBase());
        assertNull(ficha.getPosicion());
        System.out.println("Ficha capturada correctamente.\n");
    }

    @Test
    public void testSettersYGetters() {
        System.out.println("Iniciando test: testSettersYGetters");
        Ficha ficha = new Ficha("Rojo");
        ficha.setColor("Amarillo");
        ficha.setEnBase(false);
        Casilla casilla = new Casilla(10);
        ficha.setPosicion(casilla);

        System.out.println("Ficha ahora es color: " + ficha.getColor() + " en casilla ID: " + casilla.getId());
        assertEquals("Amarillo", ficha.getColor());
        assertFalse(ficha.isEnBase());
        assertEquals(casilla, ficha.getPosicion());
        System.out.println("Getters y Setters de ficha funcionan correctamente.\n");
    }
}
