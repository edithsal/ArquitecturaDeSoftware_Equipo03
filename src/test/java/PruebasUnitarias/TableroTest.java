
package PruebasUnitarias;

import Modelo.Casilla;
import Modelo.Ficha;
import Modelo.Tablero;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class TableroTest {
    
    private Tablero tablero;
    private List<Casilla> casillas;

    /**
     * Antes de cada prueba se crea un tablero nuevo con 10 casillas
     * numeradas del 0 al 9 para que todas las pruebas empiecen iguales.
     */
    @BeforeEach
    public void setUp() {
        casillas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            casillas.add(new Casilla(i));
        }
        tablero = new Tablero(casillas);
    }

    @Test
    public void testObtenerCasillaPorIdExistente() {
        System.out.println("Iniciando test: testObtenerCasillaPorIdExistente");

        // Buscamos una casilla que sabemos que existe
        Casilla c = tablero.obtenerCasilla(5);

        // Verificamos que la casilla no sea null y tenga el id correcto
        assertNotNull(c);
        assertEquals(5, c.getId());

        System.out.println("Casilla encontrada con ID: " + c.getId());
        System.out.println("Busqueda de casilla existente correcta.\n");
    }

    @Test
    public void testObtenerCasillaPorIdInexistente() {
        System.out.println("Iniciando test: testObtenerCasillaPorIdInexistente");

        // Buscamos una casilla que no existe
        Casilla c = tablero.obtenerCasilla(99);

        // Verificamos que no se encuentre ningun objeto
        assertNull(c);

        System.out.println("No se encontro casilla con ID 99, como se esperaba.\n");
    }

    @Test
    public void testActualizarPosicionFicha() {
        System.out.println("Iniciando test: testActualizarPosicionFicha");

        // Creamos una ficha y la movemos a una casilla del tablero
        Ficha ficha = new Ficha("Roja");
        Casilla destino = tablero.obtenerCasilla(3);
        tablero.actualizarPosicion(ficha, destino);

        // Verificamos que la ficha ahora tenga la nueva posicion y no este en base
        assertEquals(destino, ficha.getPosicion());
        assertFalse(ficha.isEnBase());

        System.out.println("Ficha " + ficha.getColor() + " movida a casilla ID: " + destino.getId());
        System.out.println("Posicion de ficha actualizada correctamente.\n");
    }

    @Test
    public void testListaDeCasillasInicial() {
        System.out.println("Iniciando test: testListaDeCasillasInicial");

        // Verificamos que el tablero tenga la cantidad correcta de casillas
        assertEquals(10, tablero.getCasillas().size());

        System.out.println("Tablero contiene la cantidad correcta de casillas: " + tablero.getCasillas().size() + "\n");
    }

    @Test
    public void testCambiarCasillasEnTablero() {
        System.out.println("Iniciando test: testCambiarCasillasEnTablero");

        // Reemplazamos la lista de casillas del tablero
        List<Casilla> nuevas = new ArrayList<>();
        nuevas.add(new Casilla(100));
        tablero.setCasillas(nuevas);

        // Verificamos que la lista se haya actualizado correctamente
        assertEquals(1, tablero.getCasillas().size());
        assertEquals(100, tablero.getCasillas().get(0).getId());

        System.out.println("Lista de casillas del tablero reemplazada correctamente con ID: " + tablero.getCasillas().get(0).getId() + "\n");
    }

    @Test
    public void testCapturaFichaRegresaABase() {
        System.out.println("Iniciando test: testCapturaFichaRegresaABase");

        // Creamos una ficha, la movemos a una casilla y luego la capturamos
        Ficha ficha = new Ficha("Azul");
        Casilla destino = tablero.obtenerCasilla(4);
        tablero.actualizarPosicion(ficha, destino);

        ficha.capturar();

        // Verificamos que la ficha haya regresado a base y su posicion sea null
        assertNull(ficha.getPosicion());
        assertTrue(ficha.isEnBase());

        System.out.println("Ficha " + ficha.getColor() + " capturada y regresada a base.");
        System.out.println("Captura de ficha verificada correctamente.\n");
    }
}
