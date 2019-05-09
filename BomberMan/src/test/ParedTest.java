package test;

import static org.junit.Assert.*;
import org.junit.*;

import entidades.*;

public class ParedTest {
	private Pared pared;
	private Tablero tablero;
	@Before
	public void IniciarTest() {
		tablero = new Tablero(25,40);
		pared=new Pared(2,3,tablero);
	}
	@After
	public void finalizarTest() {
		pared=null;
		tablero=null;
		
	}
	@Test
	public void constructorTest() {
		assertEquals(pared, tablero.obtenerElemento(pared.getPos()));
	}
	@Test
	public void explotarTest() {
		pared.explotar();
		assertNotEquals(pared, tablero.obtenerElemento(pared.getPos()));		
	}
	@Test
	public void seguirExplotandoTransitableTest() {
		assertFalse(pared.puedeSeguirExplotando());
		assertFalse(pared.esTransitable());
	}
}