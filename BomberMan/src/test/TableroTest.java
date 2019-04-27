package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.Tablero;

public class TableroTest {

	Tablero t;
	
	@Before
	public void iniciar() {
		t = new Tablero(25,30); 
	}
	
	@Test
	public void constructor()
	{
		assertEquals(25,t.obtenerAncho());
		assertEquals(30, t.obtenerLargo());
	}
}
