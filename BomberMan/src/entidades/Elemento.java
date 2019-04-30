package entidades;

public abstract class Elemento {
	private int _x;
	private int _y;
    protected Tablero _tablero;
	public Elemento(int x, int y, Tablero tablero) {
		_x = x;
		_y=y;
		_tablero= tablero;
	}

	
	public int posicionX() {
		return _x;
	}
	public int posicionY() {
		return _y;
	}
}
