package entidades;


public class Bomba extends Elemento{
	private Jugador _jugador;
	protected int _tiempoexplosion;
	private int rango;
	/**
	 * Constructor de la clase Bomba
	 */
	public Bomba (int x, int y, Tablero tablero, Jugador jugador) {
		super(x,y,tablero);
		_tiempoexplosion=3000;
		this._jugador = jugador;
		rango=3;
	
	}
	
	public void explotar() {
		explotaIzq();
		explotaDer();
		explotaArriba();
		explotaAbajo();
		this.destruir();
		
	}//fin explotarBomba
	private void explotaIzq() {
		int posFinal = (x -rango) < 0 ? 0: x-rango ;
		for (int i = x ; i >= posFinal; i--) {
			if(! puedeContinuar(i, y)) {
				return;	
			}
		}
	}
	
	private void explotaDer() {
		int posFinal = (x + rango) > tablero.obtenerAncho() ? tablero.obtenerAncho(): x+rango ;
		for (int i = x; i <= posFinal; i++) {
			if(!puedeContinuar(i,y)) {
				return;
			}
		}
	}
	private void explotaArriba() {
		int posFinal = (y - rango) < 0 ? 0: x-rango ;
		for (int i = y; i <= posFinal; i--) {
			if(!puedeContinuar(x,i)) {
				return;
			}
		}
	}
	private void explotaAbajo() {
		int posFinal = (y + rango) > tablero.obtenerLargo() ? tablero.obtenerLargo(): y + rango ;
		for (int i = y; i <= posFinal; i++) {
			if(!puedeContinuar(x,i)) {
				return;
			}
		}
	}
	
	private boolean puedeContinuar(int x, int y) {
		Elemento e = tablero.obtenerElemento(x,y);
		if(e instanceof ElementoDestruible) {
			if(e instanceof Bomba && e != this) {
				((Bomba) e).explotar();
			}else {
			  ((ElementoDestruible) e).destruirse();
			}
		}
		if(e instanceof Pared || e instanceof Muro) {
			return false;
		}
		return true;
	}

	
	public void destruir() {
		this.vivo = false;
	}
}
