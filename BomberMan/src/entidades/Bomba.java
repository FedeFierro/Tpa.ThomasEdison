package entidades;

public class Bomba extends ElementoDestruible{
	private Jugador _jugador;
	protected int _tiempoexplosion;
	/**
	 * Constructor de la clase Bomba
	 */
	public Bomba (int x, int y, Tablero tablero, Jugador jugador) {
		super(x,y,tablero);
		_tiempoexplosion=5;
		this._jugador = jugador;
	}
	
	public void explosion() {
		Explosion explosion= new Explosion(posicionX(),posicionY(),tablero(),this);
	}
	
	public int obtenerTiempo() {
		return _tiempoexplosion;
	}
	
}
