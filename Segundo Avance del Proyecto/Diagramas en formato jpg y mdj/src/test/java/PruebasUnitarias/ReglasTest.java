
package PruebasUnitarias;
import Modelo.Casilla;
import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.ReglasJuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Carlos
 */
public class ReglasTest {
    
    private ReglasJuego reglas;
    private Ficha fichaRoja;
    private Ficha fichaAzul;
    private Casilla casilla1;
    private Casilla casilla2;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        reglas = new ReglasJuego();
        fichaRoja = new Ficha("Rojo");
        fichaAzul = new Ficha("Azul");
        casilla1 = new Casilla(1);
        casilla2 = new Casilla(2);

        // Creamos una lista vacia de fichas para el jugador
        List<Ficha> fichasJugador = new ArrayList<>();
        fichasJugador.add(new Ficha("Rojo")); // ejemplo, se puede agregar mas fichas

        // Creamos el jugador con nombre, color de ficha y lista de fichas
        jugador = new Jugador("Jugador1", "Rojo", fichasJugador);
    }

    @Test
    public void testReglasIniciales() {
        System.out.println("Iniciando test: testReglasIniciales");
        assertEquals(3, reglas.getMaxSeisConsecutivos());
        assertTrue(reglas.isObligarComer());
        assertTrue(reglas.isSalidaCon5());
        assertTrue(reglas.isLimite3Tiros6());
        System.out.println("Reglas iniciales configuradas correctamente.\n");
    }

    @Test
    public void testSettersYGetters() {
        System.out.println("Iniciando test: testSettersYGetters");
        reglas.setMaxSeisConsecutivos(5);
        reglas.setObligarComer(false);
        reglas.setSalidaCon5(false);
        reglas.setLimite3Tiros6(false);

        assertEquals(5, reglas.getMaxSeisConsecutivos());
        assertFalse(reglas.isObligarComer());
        assertFalse(reglas.isSalidaCon5());
        assertFalse(reglas.isLimite3Tiros6());
        System.out.println("Setters y getters de reglas funcionan correctamente.\n");
    }

    @Test
    public void testValidarMovimiento() {
        System.out.println("Iniciando test: testValidarMovimiento");
        boolean valido = reglas.validarMovimiento(fichaRoja, casilla1);
        assertTrue(valido);
        System.out.println("Movimiento validado correctamente para ficha " + fichaRoja.getColor() + ".\n");
    }

    @Test
    public void testPuedeSacarFicha() {
        System.out.println("Iniciando test: testPuedeSacarFicha");
        boolean puedeSacar = reglas.puedeSacarFicha(jugador);
        assertTrue(puedeSacar);
        System.out.println("Jugador " + jugador.getNombre() + " puede sacar ficha correctamente.\n");
    }

    @Test
    public void testAplicarReglaCaptura() {
        System.out.println("Iniciando test: testAplicarReglaCaptura");

        // Colocamos ficha azul en la casilla
        casilla1.setOcupadaPor(fichaAzul);
        // Aplicamos captura con ficha roja
        reglas.aplicarReglaCaptura(fichaRoja, casilla1);

        assertEquals(fichaRoja, casilla1.getOcupadaPor());
        assertTrue(fichaAzul.isEnBase());
        System.out.println("Ficha Azul capturada y regresada a base.");
        System.out.println("Captura de ficha verificada correctamente.\n");
    }
}