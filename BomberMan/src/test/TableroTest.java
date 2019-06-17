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
		tablero = new Tablero(5,5,100,2); 
		pared= new Pared(2,2,tablero);
		jugador = new Jugador(1,1,tablero);
		tablero.setJugador(jugador);
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
		tablero.setElemento(pared);
		assertEquals(pared, tablero.getElemento(pared.getPos()));
		tablero.quitarElemento(pared);
		assertNotEquals(pared, tablero.getElemento(pared.getPos()));
	
	}
	@Test
	public void agregarQuitarJugadorTest() {
		assertEquals(jugador,tablero.getElemento(jugador.getPos()));
		tablero.quitarJugador(jugador);
		assertNull(tablero.getJugador(jugador.getPos()));
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
