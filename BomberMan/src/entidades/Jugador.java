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
	 * M�todo que mueve un jugador
	 * @param x Es el movimiento en x de l jugador
	 * @param y Es el movimiento en y de l jugador
	 */
	public void moverse(int x, int y) {

		if(this._tablero.obtenerAncho() <= posicionX() + x || posicionX() + x < 0)
			return;
		
		if(this._tablero.obtenerLargo() <= posicionY() + y || posicionY() + y < 0)
			return;
		
		if(this._tablero.obtenerElemento(posicionX() + x, posicionY() + y) != null)
			return;
		cambiarPosicionEnTablero(_x+x, _y+y);
	}
	
	/**
	 * Planta una bomba*/
	public void plantarBomba() {
		bombasPlantadas += 1;
		Bomba bomba = new Bomba(posicionX(), posicionY(), tablero(), this);
	}
	
	/**
	 * Devuelve la cantidad de bombas plantadas
	 * @return int: Cantidad de bombas*/
	public int bombasPlantadas() {
		return bombasPlantadas;
	}

	/**
	 * Setea la cantidad de bombas plantadas
	 * @return int: Cantidad de bombas*/
	public void bombasPlantadas(int bombasPlantadas) {
		this.bombasPlantadas = bombasPlantadas;
	}
	private void cambiarPosicionEnTablero(int xFinal, int yFinal) {
		Elemento e = _tablero.obtenerElemento(_x, _y);
			if(e instanceof Jugador) {
				_tablero.eliminarElemento(this);
			}
			_x=xFinal;
			_y=yFinal;
			_tablero.agregarElemento(this);
		
	}
}
