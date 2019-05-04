package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.*;

public class ParedTest {
	private Pared p;
	private Tablero t;
	@Before
	public void iniciarTest() {
		t = new Tablero(25,40);
		p=new Pared(2,3,t);
	}
	@After
	public void finalizarTest() {
		p=null;
		t=null;
		
	}
	@Test
	public void constructorTest() {
		assertEquals(2, p.posicionX());
		assertEquals(3, p.posicionY());
	}
	@Test
	public void destruirTest() {
		assertEquals(true, p.estaVivo());
		p.destruirse();
		assertEquals(null, t.obtenerElemento(2, 3));
		assertEquals(false, p.estaVivo());
	}
}