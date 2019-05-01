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

	/**
	 *Obtiene el valor de la posición X 
	 */
	public int posicionX() {
		return _x;
	}
	/**
	 *Obtiene el valor de la posición X
	 */
	public int posicionY() {
		return _y;
	}
	/**
	 *Obtiene el tablero
	 */
	public Tablero tablero() {
		return _tablero;
	}
	/**
	 *Setea el valor de la posición X 
	 *@param x donde se quiere posicionar
	 */
	public void posicionX(int x) {
		_x = x;
	}
	/**
	 *Setea el valor de la posición X 
	 *@param y donde se quiere posicionar
	 */
	public void posicionY(int y) {
		_y = y;
	}
}
