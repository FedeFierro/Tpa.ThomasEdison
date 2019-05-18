package test;

import static org.junit.Assert.*;

import org.junit.*;

import entidades.*;

public class MuroTest {

	private Muro muro;
	private Tablero tablero;
	
	
	@Before
	public void IniciarTest() {
		tablero = new Tablero(25,40);
		muro=new Muro(2,3,tablero);
	}
	
	@After
	public void finalizarTest() {
		tablero=null;
		muro=null;
		
	}
	@Test
	public void constructorTest() {
		assertEquals(muro, tablero.getElemento(muro.getPos()));
	}
	@Test
	public void explotarTest() {
		muro.explotar();
		assertEquals(muro, tablero.getElemento(muro.getPos()));		
	}
	@Test
	public void seguirExplotandoTransitableTest() {
		assertFalse(muro.puedeSeguirExplotando());
		assertFalse(muro.esTransitable());
	}
}