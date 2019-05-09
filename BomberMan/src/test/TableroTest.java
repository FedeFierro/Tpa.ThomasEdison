package test;

import static org.junit.Assert.*;
import org.junit.*;
import entidades.*;

public class TableroTest {

	Tablero tablero;
	Pared pared;
	Jugador jugador;
	Muro muro;
	Coordenada coordenada;
	
	@Before
	public void iniciarTest() {
		tablero = new Tablero(5,5); 
		pared= new Pared(1,1,tablero);
		jugador = new Jugador(2,3,tablero);
		muro = new Muro(4,4,tablero);
		coordenada = new Coordenada(1,2);
 	}
	@After
	public void finalizarTest() {
		tablero=null;
		pared=null;
		jugador=null;
		muro = null;
		coordenada=null;
	}
	
	
	@Test
	public void agregarQuitarElementoTest() {
		tablero.agregarElemento(pared);
		assertEquals(pared, tablero.obtenerElemento(pared.getPos()));
		tablero.eliminarElemento(pared);
		assertNotEquals(pared, tablero.obtenerElemento(pared.getPos()));
	
	}
	@Test
	public void agregarQuitarJugadorTest() {
		assertEquals(jugador,tablero.obtenerElemento(jugador.getPos()));
		tablero.eliminarJugador(jugador);
		assertNull(tablero.obtenerJugador(jugador.getPos()));
	}
	@Test
	public void puedeExplotarTest() {
		assertTrue(tablero.puedeExplotar(pared.getPos()));
		coordenada=new Coordenada(-1,-3);
		assertFalse(tablero.puedeExplotar(coordenada));
	}
	@Test
	public void puedeMoverseTest() {
		assertTrue(tablero.puedeMover(coordenada));
		assertFalse(tablero.puedeMover(muro.getPos()));
	}
}
