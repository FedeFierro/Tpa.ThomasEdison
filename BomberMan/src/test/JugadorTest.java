package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import entidades.Coordenada;
import entidades.Jugador;
import entidades.Tablero;
import helper.Helper;

public class JugadorTest {

	Tablero tablero;
	Jugador jugador;
	Coordenada coordenada;
	@Before
	public void iniciarTest() {
		tablero = new Tablero(25,30,200,5); 
		jugador= new Jugador(1,1,tablero);
		tablero.setJugadorTest(jugador);
	}
	@After
	public void finalizarTest() {
		tablero=null;
		jugador=null;
		coordenada=null;
	}
	
	@Test
	public void constructorTest()
	{
		assertEquals(jugador,tablero.getJugador(jugador.getPos()));
	}	
	@Test
	public void ponerBombaTest() {
		jugador.plantarBomba();
		coordenada = new Coordenada(jugador.getPos());
		jugador.moverse(Helper.MOV_JUG*4, 0);
		jugador.moverse(Helper.MOV_JUG*4, 0);
		assertNotEquals(jugador, tablero.getElemento(coordenada));
	}
	
	@Test
	public void explotarTest() throws InterruptedException {
		jugador.explotar();
		Thread.sleep(1000);
		assertNotEquals(jugador, tablero.getElemento(jugador.getPos()));
	}
	
	@Test
	public void moverseTest() {
		jugador.moverse(Helper.MOV_JUG*4, 0);
		jugador.moverse(Helper.MOV_JUG*4,0);
		coordenada = new Coordenada(3,1);
		assertTrue(coordenada.equals(jugador.getPos()));
	}
}
