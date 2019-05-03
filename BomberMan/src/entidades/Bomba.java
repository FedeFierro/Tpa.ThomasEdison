package entidades;

public class Bomba extends ElementoDestruible{
	private Jugador _jugador;
	/**
	 * Constructor de la clase Bomba
	 */
	public Bomba (int x, int y, Tablero tablero, Jugador jugador) {
		super(x,y,tablero);
		this._jugador = jugador;
	}
	
	
}
