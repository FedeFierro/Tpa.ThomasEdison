package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.*;

public class TotalTest {
	private Tablero t;
	private Jugador j;
	private Pared p;
	private Muro m;
	private Bomba b;
	
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
	public void moverJugadorFueraDeLimiteTest() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<3;i++) {
			j.moverse(-1,0);
		}
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
	}
	@Test
	public void moverJugadroContraMuroTest() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<3;i++) {
			j.moverse(0,-1);
		}
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
	
	}
	@Test
	public void moverJugadorHastaParedTest() {
		assertEquals(0, j.posicionX());
		assertEquals(1, j.posicionY());
		for(int i=1; i<5;i++) {
			j.moverse(0,1);
		}
		assertEquals(0, j.posicionX());
		assertEquals(2, j.posicionY());
		
		  
	}
	
	@Test
	public void moverJugadorContraJugadorTest() {
		//al encontraser contra otro jugador, no puede seguir avanzando
		Jugador j2 =  new Jugador(3,3,t);
		j.moverse(1, 0);//se posicion en (1,1)
		for(int i=1; i<4;i++) {
			j.moverse(1,1);
		}
		assertEquals(2, j.posicionX());
		assertEquals(2, j.posicionY());		
	}
	@Test
	public void explotarBombaTest() {
		j.moverse(0, 1);
		assertEquals(0, j.posicionX());
		assertEquals(2, j.posicionY());
		
		b= new Bomba(0,1,t,j);
		b.explotar();
		assertEquals(m,t.obtenerElemento(0, 0));
		assertEquals(null,t.obtenerElemento(0, 2)); //jugador
		assertEquals(null,t.obtenerElemento(0, 1)); //bomba
		assertEquals(null,t.obtenerElemento(0, 3));//pared
			
	}
	@Test 
	public void explotarBombaJugadorTrasParedTest() {
		j.moverse(1,0);
		for(int i=1; i<4;i++) {
			j.moverse(0, 1);
		}
		j.moverse(-1, 0);
		assertEquals(0, j.posicionX());
		assertEquals(4, j.posicionY());
		Bomba b = new Bomba(0,2,t,j);
		b.explotar();
		assertEquals(null,t.obtenerElemento(0, 3));//pared
		assertEquals(j,t.obtenerElemento(0, 4));//jugador
		
	}
	@Test
    public void bombaBombaTest() {
    	b = new Bomba(5,5,t,j);
    	Bomba b2 = new Bomba(5,7,t,j);
    	assertEquals(b2, t.obtenerElemento(5, 7));
    	b.explotar();
    	assertEquals(null, t.obtenerElemento(5, 7));
    	assertEquals(false, b2.estaVivo());
    }
	@Test
	public void bombaNadaParedTest() {
		b = new Bomba(0,5,t,j);
		b.explotar();
		assertEquals(null,t.obtenerElemento(0, 3));//pared
    	
	}
}
