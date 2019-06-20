package test;

import static org.junit.Assert.*;
import org.junit.*;

import entidades.*;
import helper.Helper;

public class IntegralTest {
	private Tablero tablero;
	private Jugador jugador;
	private Pared pared;
	private Muro muro;
	private Bomba bomba;
	Coordenada coordenada;
	Jugador jugador2;
	Bomba bomba2;
	@Before
	public void iniciarTest() {
	  tablero=new Tablero(25,25,200,5);
	  jugador=new Jugador(0,1,tablero);
	  tablero.setJugadorTest(jugador);
	  muro= new Muro(0,0,tablero);
	  pared= new Pared(0,3,tablero);
	  
	  
	}
	@After
	public void finalizarTest() {
		pared=null;
		muro=null;
		jugador=null;
		tablero=null;
		bomba=null;
		coordenada=null;
		jugador2=null;
		bomba2=null;
	}
	@Test
	public void moverJugadorFueraDeLimiteTest() {
		coordenada=new Coordenada(jugador.getPos());
		jugador.moverse(-(Helper.MOV_JUG*4), 0);
		jugador.moverse(-(Helper.MOV_JUG*4), 0);
		jugador.getPos().toString();
		assertTrue(coordenada.equals(jugador.getPos()));
	}
	
	@Test
	public void moverJugadroContraMuroTest() {
		coordenada=new Coordenada(jugador.getPos());
		jugador.moverse(0, -1);
		jugador.moverse(0, -1);
		assertTrue(coordenada.equals(jugador.getPos()));
	}
	@Test
	public void moverJugadorHastaParedTest() {
		jugador.moverse(0, Helper.MOV_JUG*4);
		jugador.moverse(0, Helper.MOV_JUG*4);
		jugador.moverse(0, Helper.MOV_JUG*4);
		coordenada = new Coordenada(0,2);
		assertTrue(coordenada.equals(jugador.getPos()));
	}
	
	@Test
	public void moverJugadorContraJugadorTest() {
		jugador2 =  new Jugador(3,3,tablero);
		jugador.moverse(1, 0);
		jugador.moverse(1, 0);
		jugador.moverse(1, 0);
		jugador.moverse(0, 1);
		jugador.moverse(0, 1);
		coordenada=new Coordenada(3,2);
		assertTrue(coordenada.equals(jugador.getPos()));
		jugador2.moverse(0, -1);
		coordenada=new Coordenada(3,3);
		assertTrue(coordenada.equals(jugador2.getPos()));
		
	}
	@Test
	public void explotarBombaTest() throws InterruptedException {
		jugador.moverse(0, Helper.MOV_JUG*4);
		bomba= new Bomba(0,1,tablero,jugador);
		bomba.explotar();
		assertEquals(muro,tablero.getElemento(muro.getPos()));
		assertNotEquals(jugador,tablero.getElemento(jugador.getPos())); //jugador
		assertNotEquals(bomba,tablero.getElemento(bomba.getPos())); //bomba
		assertNotEquals(pared,tablero.getElemento(pared.getPos()));//pared

	}
	@Test 
	public void explotarBombaJugadorTrasParedTest() throws InterruptedException {
		jugador.moverse(Helper.MOV_JUG*4,0);
		jugador.moverse(0,Helper.MOV_JUG*4);
		jugador.moverse(0,Helper.MOV_JUG*4);
		jugador.moverse(0,Helper.MOV_JUG*4);
		jugador.moverse(-(Helper.MOV_JUG*4),0);
	    bomba = new Bomba(0,2,tablero,jugador);
		bomba.explotar();
		Thread.sleep(2000);
		assertNotEquals(pared,tablero.getElemento(pared.getPos()));//pared
		assertEquals(jugador,tablero.getJugador(jugador.getPos()));//jugador
	
	}
	@Test
    public void bombaBombaTest() {
    	bomba = new Bomba(5,5,tablero,jugador);
    	bomba2 = new Bomba(5,7,tablero,jugador);
    	assertEquals(bomba2, tablero.getElemento(bomba2.getPos()));
    	bomba.explotar();
    	assertNotEquals(bomba2, tablero.getElemento(bomba2.getPos()));
    }
	@Test
	public void bombaNadaParedTest() {
		bomba = new Bomba(0,5,tablero,jugador);
		bomba.explotar();
		assertNotEquals(bomba, tablero.getElemento(bomba.getPos()));
		
		
	}
}
