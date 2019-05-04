package entidades;

public class Jugador extends Elemento{
	
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

		
	if(this.tablero.obtenerElemento(posicionX() + x, posicionY() + y) == null ) 
	{
		if( x + posicionX() > 0 && x + posicionX() <= this.tablero.obtenerAncho())
			cambiarPosicionEnTablero(this.x+x, this.y+y);
		
		if(y+posicionY() > 0 && y + posicionY() <= this.tablero.obtenerLargo())
			cambiarPosicionEnTablero(this.x+x, this.y+y);
	}
		
//		if(this.tablero.obtenerAncho() <= posicionX() + x || posicionX() + x < 0)
//			return;
		
//		if(this.tablero.obtenerLargo() <= posicionY() + y || posicionY() + y < 0)
//			return;
		
		
//			return;
//		cambiarPosicionEnTablero(this.x+x, this.y+y);
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
		Elemento e = tablero.obtenerElemento(this.x, this.y);
			if(e instanceof Jugador) {
				tablero.eliminarElemento(this);
			}
			x=xFinal;
			y=yFinal;
			tablero.agregarElemento(this);
		
	}
	
	public void destruir() {
		this.vivo = false;
	}
	
}
