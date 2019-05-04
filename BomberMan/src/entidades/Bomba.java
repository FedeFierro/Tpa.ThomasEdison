package entidades;


public class Bomba extends ElementoDestruible{
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
		this.destruirse();
		
	}//fin explotarBomba
	private void explotaIzq() {
		int posFinal = (_x -rango) < 0 ? 0: _x-rango ;
		for (int i = _x ; i >= posFinal; i--) {
			if(! puedeContinuar(i, _y)) {
				return;	
			}
		}
	}
	
	private void explotaDer() {
		int posFinal = (_x + rango) > _tablero.obtenerAncho() ? _tablero.obtenerAncho(): _x+rango ;
		for (int i = _x; i <= posFinal; i++) {
			if(!puedeContinuar(i,_y)) {
				return;
			}
		}
	}
	private void explotaArriba() {
		int posFinal = (_y - rango) < 0 ? 0: _x-rango ;
		for (int i = _y; i >= posFinal; i--) {
			if(!puedeContinuar(_x,i)) {
				return;
			}
		}
	}
	private void explotaAbajo() {
		int posFinal = (_y + rango) > _tablero.obtenerLargo() ? _tablero.obtenerLargo(): _y + rango ;
		for (int i = _y; i <= posFinal; i++) {
			if(!puedeContinuar(_x,i)) {
				return;
			}
		}
	}
	
	private boolean puedeContinuar(int x, int y) {
		Elemento e = _tablero.obtenerElemento(x,y);
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
}
