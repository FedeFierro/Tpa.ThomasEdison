package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import entidades.Jugador;
import entidades.Tablero;

public class JugadorTest {

	Tablero t;
	Jugador j;
	
	@Before
	public void iniciar() {
		t = new Tablero(25,30); 
		j= new Jugador(1,1,t);
	}
	
	@Test
	public void constructor()
	{
		assertEquals(1,j.posicionX());
		assertEquals(1,j.posicionY());
		assertEquals(0,j.bombasPlantadas());
		System.out.println("X:" +j.posicionX()+" - Y:" +j.posicionY());
		System.out.println("bombas puestas:" + j.bombasPlantadas());
	}	
	@Test
	public void ponerBomba() {
		System.out.print("plantando bombas (...) -> ");
		j.plantarBomba();
		j.plantarBomba();
		j.plantarBomba();
		assertEquals(3,j.bombasPlantadas());
		System.out.println("bombas puestas:" + j.bombasPlantadas());
	}
	
	@Test
	public void destruirse() {
		assertEquals(true, j.getVivo());
		j.destruir();
		assertEquals(null, t.obtenerElemento(1, 1));
		assertEquals(false, j.getVivo());
	}
	
	@Test
	public void moverse() {
		System.out.println("posición inicial -> "+j.posicionX()+"-"+j.posicionY());
		j.moverse(23, 28);
		assertEquals(24,j.posicionX());
		assertEquals(29,j.posicionY());
		System.out.println("posición final -> "+j.posicionX()+"-"+j.posicionY());
	}
}
