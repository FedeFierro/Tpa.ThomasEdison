package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Bomba;
import entidades.Jugador;
import entidades.Tablero;

public class BombaTest {

	Bomba bomba;
	Tablero tablero;
	Jugador jugador;
	@Before
	public void inicializar() {
		tablero = new Tablero(25,30,200,4);
		jugador= new Jugador(1,1,tablero);
		bomba=new Bomba(5,5,tablero,jugador);
	}
	@After
	public void finalizarTest() {
		tablero=null;
		jugador=null;
		bomba=null;
		
	}

	@Test
	public void constructor() {
		assertEquals(bomba, tablero.getElemento(bomba.getPos()));
	}
	@Test
	public void explotarTest() throws InterruptedException{
		bomba.explotar();
		assertNotEquals(bomba, tablero.getElemento(bomba.getPos()));
	}
	

}
