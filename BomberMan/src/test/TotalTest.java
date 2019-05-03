package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.*;

public class TotalTest {
	private Tablero t;
	private Jugador j;
	private Pared p;
	private Muro m;
	
	@Before
	public void iniciarTest() {
	  t=new Tablero(25,25);
	  j=new Jugador(0,1,t);
	  m= new Muro(0,0,t);
	  p= new Pared(0,3,t);
	  
	}
	@After
	public void finalizarTest() {
		p=null;
		m=null;
		j=null;
		t=null;
	}
	@Test
	public void moverJugadorFueraDeLimite() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<3;i++) {
			j.moverse(-1,0);
		}
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
	}
	@Test
	public void moverJugadroContraMuro() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<3;i++) {
			j.moverse(0,-1);
		}
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
	
	}
	@Test
	public void moverJugadorHastaPared() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<5;i++) {
			j.moverse(0,1);
		}
		assertEquals(0, j.posicionX());
		assertEquals(2, j.posicionY());
		
	}
	

}
