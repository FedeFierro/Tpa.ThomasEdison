package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import entidades.Coordenada;
import entidades.Jugador;
import entidades.Tablero;

public class JugadorTest {

	Tablero tablero;
	Jugador jugador;
	Coordenada coordenada;
	@Before
	public void iniciarTest() {
		tablero = new Tablero(25,30); 
		jugador= new Jugador(1,1,tablero);
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
		jugador.moverse(1, 0);
		jugador.moverse(1, 0);
		assertNotEquals(jugador, tablero.getElemento(coordenada));
	}
	
	@Test
	public void explotarTest() {
		jugador.explotar();
		assertNotEquals(jugador, tablero.getElemento(jugador.getPos()));
	}
	
	@Test
	public void moverseTest() {
		jugador.moverse(1, 0);
		jugador.moverse(1,0);
		coordenada = new Coordenada(3,1);
		assertTrue(coordenada.equals(jugador.getPos()));
	}
}
