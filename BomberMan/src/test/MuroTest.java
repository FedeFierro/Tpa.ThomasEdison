package test;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import entidades.Muro;
import entidades.Tablero;

public class MuroTest {

	private Muro m;
	private Tablero t;
	
	
	@Before
	public void IniciarTest() {
		t = new Tablero(25,40);
		m=new Muro(2,3,t);
	}
	
	
	
	@After
	public void finalizarTest() {
		t=null;
		m=null;
		
	}
	@Test
	public void constructorTest() {
		assertEquals(2, m.posicionX());
		assertEquals(3, m.posicionY());
	}
}