package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entidades.Bomba;
import entidades.Jugador;
import entidades.Tablero;

public class BombaTest {

	Bomba b;
	Tablero t;
	Jugador j;
	@Before
	public void inicializar() {
		t = new Tablero(25,30);
		j= new Jugador(1,1,t);
		b=new Bomba(5,5,t,j);
	}

	@Test
	public void constructor() {
		assertEquals(5, b.posicionX());
		assertEquals(5, b.posicionY());
	}
	@Test
	public void destruir() {
		assertEquals(true, b.estaVivo());
		b.destruirse();
		assertEquals(null, t.obtenerElemento(5, 5));
		assertEquals(false, b.estaVivo());
	}

}
