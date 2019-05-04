package entidades;

public abstract class Elemento {
	protected int x;
	protected int y;
    protected Tablero tablero;
    protected boolean vivo;
    
	public Elemento(int x, int y, Tablero tablero) {
		this.x = x;
		this.y=y;
		this.tablero= tablero;
		this.tablero.agregarElemento(this);
	}

	/**
	 * Metodo abstracto para destruir. 
	 */
	public abstract void destruir();
	
	public boolean getVivo() {
		return vivo;
	}
	
	
	/**
	 *Obtiene el valor de la posici�n X 
	 */
	public int posicionX() {
		return x;
	}
	/**
	 *Obtiene el valor de la posici�n X
	 */
	public int posicionY() {
		return y;
	}
	/**
	 *Obtiene el tablero
	 */
	public Tablero tablero() {
		return tablero;
	}
	/**
	 *Setea el valor de la posici�n X 
	 *@param x donde se quiere posicionar
	 */
	public void posicionX(int x) {
		this.x = x;
	}
	/**
	 *Setea el valor de la posici�n X 
	 *@param y donde se quiere posicionar
	 */
	public void posicionY(int y) {
		this.y = y;
	}
}
