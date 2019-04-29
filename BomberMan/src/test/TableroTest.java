package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.Pared;
import entidades.Tablero;

public class TableroTest {

	Tablero t;
	Pared p;
	
	@Before
	public void iniciar() {
		t = new Tablero(25,30); 
		p= new Pared(1,1);
	}
	
	@Test
	public void constructor()
	{
		assertEquals(25,t.obtenerAncho());
		assertEquals(30, t.obtenerLargo());
	}
	@Test
	public void agregarElemento() {
		t.agregarElemento(p);
		assertEquals(p, t.obtenerElemento(1, 1));
	}
	@Test
	public void quitarElemento() {
		t.agregarElemento(p);
		t.eliminarElemento(p);
		assertEquals(null, t.obtenerElemento(1, 1));
	}
}
