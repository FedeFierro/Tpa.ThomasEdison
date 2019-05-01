package entidades;

public class Jugador extends ElementoDestruible{
	
	private int bombasPlantadas;
	/**
	 * Constructor de la clase Jugador
	 */
	public Jugador (int x, int y, Tablero tablero) {
		super(x,y,tablero);
		bombasPlantadas = 0;
	}
	
	/**
	 * Método que mueve un jugador
	 * @param x Es el movimiento en x de l jugador
	 * @param y Es el movimiento en y de l jugador
	 */
	public void moverse(int x, int y) {
		posicionX(posicionX() + x);
		posicionY(posicionY() + y);
	}
	
	public void plantarBomba() {
		Bomba bomba = new Bomba(posicionX(), posicionY(), tablero());
		bombasPlantadas += 1;
	}
	
	public int bombasPlantadas() {
		return bombasPlantadas;
	}
	
}
