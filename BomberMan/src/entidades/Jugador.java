package entidades;

public class Jugador extends Elemento{
	
	private final int MAXBOMBAS =2;
	private int bombasPlantadas;
	/**
	 * Constructor de la clase Jugador
	 */
	public Jugador (int x, int y, Tablero tablero) {
		super(new Coordenada (x,y),tablero);
		bombasPlantadas = 0;
		tablero.agregarJugador(this);
	}
	
	/**
	 * M�todo que mueve un jugador
	 * @param x Es el movimiento en x de l jugador
	 * @param y Es el movimiento en y de l jugador
	 */
	public void moverse(int x, int y) {
		if(vivo) {
			if(tablero.puedeMover(pos.obtenerPosicionProvisoria(x, y))) {
				Coordenada posAnterior = new Coordenada(this.pos);
				this.pos.actualizarPosicion(x, y);
				tablero.intercambiarJugador(posAnterior);
			}
			
		}
	}	
	/**
	 * Planta una bomba*/
	public void plantarBomba() {
		if(bombasPlantadas < MAXBOMBAS) {
			bombasPlantadas += 1;
			new Bomba(tablero, this);
		}
	}
	
	@Override
	public void explotar() {
		vivo = false;
		bombasPlantadas=0;
		tablero.eliminarJugador(this);
	}
	public void explotoBomba() {
	   bombasPlantadas--;	
	}
	
}
