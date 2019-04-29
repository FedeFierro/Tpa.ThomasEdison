package entidades;

public abstract class Elemento {
	private int _x;
	private int _y;

	public Elemento(int x, int y) {
		_x = x;
		_y=y;
	}

	
	public int posicionX() {
		return _x;
	}
	public int posicionY() {
		return _y;
	}
}
