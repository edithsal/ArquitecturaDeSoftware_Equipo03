/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebasUnitarias;

/**
 *
 * @author LENOVO
 */



import Modelo.Dado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DadoTest {

    @Test
    public void testLanzarDadoEntre1y6() {
        System.out.println("Iniciando test: testLanzarDadoEntre1y6");
        Dado dado = new Dado();
        for (int i = 0; i < 10; i++) {
            int valor = dado.lanzar();
            System.out.println("Lanzamiento " + (i + 1) + ": " + valor);
            assertTrue(valor >= 1 && valor <= 6, "El valor debe estar entre 1 y 6");
        }
        System.out.println("Todos los lanzamientos del dado están entre 1 y 6.\n");
    }

    @Test
    public void testSetYGetValor() {
        System.out.println("Iniciando test: testSetYGetValor");
        Dado dado = new Dado();
        dado.setValor(4);
        System.out.println("Valor del dado configurado manualmente a " + dado.getValor());
        assertEquals(4, dado.getValor());
        System.out.println("Getter y Setter del dado funcionan correctamente.\n");
    }
}
