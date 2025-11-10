
package PruebasUnitarias;
import Modelo.Casilla;
import Modelo.Ficha;
import Modelo.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JugadorTest {
    
    private Jugador jugador;
    private List<Ficha> fichas;

    @BeforeEach
    public void setUp() {
        fichas = Arrays.asList(new Ficha("A"), new Ficha("B"), new Ficha("C"), new Ficha("D"));
        jugador = new Jugador("Ramon", "Rojo", fichas);
    }

    @Test
    public void testSetNombre() {
        System.out.println("Iniciando test: testSetNombre");
        jugador.setNombre("Carlos");
        System.out.println("Nombre del jugador cambiado a " + jugador.getNombre());
        assertEquals("Carlos", jugador.getNombre(), "El nombre debe actualizarse a 'Carlos'");
        System.out.println("Nombre actualizado correctamente.\n");
    }

    @Test
    public void testSetColorFicha() {
        System.out.println("Iniciando test: testSetColorFicha");
        jugador.setColorFicha("Azul");
        System.out.println("Color de fichas cambiado a " + jugador.getColorFicha());
        assertEquals("Azul", jugador.getColorFicha(), "El color de la ficha debe actualizarse a 'Azul'");
        System.out.println("Color actualizado correctamente.\n");
    }

    @Test
    public void testSetFichas() {
        System.out.println("Iniciando test: testSetFichas");
        List<Ficha> nuevasFichas = Arrays.asList(new Ficha("E"), new Ficha("F"));
        jugador.setFichas(nuevasFichas);
        System.out.println("Fichas del jugador actualizadas a nuevas fichas");
        assertEquals(2, jugador.getFichas().size(), "El jugador debe tener 2 fichas nuevas");
        assertTrue(jugador.getFichas().containsAll(nuevasFichas), "El jugador debe contener las nuevas fichas");
        System.out.println("Fichas actualizadas correctamente.\n");
    }

    @Test
    public void testTirarDado() {
        System.out.println("Iniciando test: testTirarDado");
        for (int i = 0; i < 100; i++) {
            int resultado = jugador.tirarDado();
            assertTrue(resultado >= 1 && resultado <= 6, "El dado debe dar un valor entre 1 y 6");
        }
        System.out.println("Dado lanzado 100 veces, todos los resultados válidos.\n");
    }

    @Test
    public void testMoverFicha() {
        System.out.println("Iniciando test: testMoverFicha");
        Ficha ficha = fichas.get(0);
        Casilla casilla = new Casilla(5);
        jugador.moverFicha(ficha, casilla);
        System.out.println("Ficha " + ficha.getColor() + " movida a casilla ID: " + casilla.getId());
        assertNotNull(ficha.getPosicion(), "La ficha no debe estar en null después de moverse");
        assertEquals(casilla, ficha.getPosicion(), "La ficha debe estar en la casilla correcta");
        assertFalse(ficha.isEnBase(), "La ficha ya no debe estar en base");
        System.out.println("Ficha movida correctamente.\n");
    }
}