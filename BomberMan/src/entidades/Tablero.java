package entidades;

public class Tablero {

	private int ancho = 15;
	private int largo = 15;
	private Elemento[] elementos;
	private Jugador[] elementosMov;

	/*
	 *Constructor de Tests 
	 */
	public Tablero(int a, int l) {
		this.ancho = a;
		this.largo = l;
		inicializarArrays();
		construirMapaVacio();
	}

	public Tablero() {
		inicializarArrays();
		construirMapaAleatorio();

	}

	public Elemento obtenerElemento(Coordenada pos) {
		Elemento e = elementosMov[getIndex(pos)];
		return e == null ? elementos[getIndex(pos)] : e;
	}

	public void agregarElemento(Elemento e) {
		elementos[getIndex(e.pos)] = e;
	}

	public void agregarElemento(Jugador j) {
		elementosMov[getIndex(j.pos)] = j;
	}

	public void eliminarElemento(Elemento e) {
		elementos[getIndex(e.pos)] = new Fondo(e.pos, this);
	}

	public void eliminarElemento(int x, int y) {
		elementos[getIndex(x, y)] = new Fondo(x, y, this);
	}

	public boolean puedeMover(Coordenada pos) {
		return !fueraDeRango(pos) && obtenerElemento(pos).esTransitable();
	}

	public boolean puedeExplotar(Coordenada pos) {
		return !fueraDeRango(pos);	
	}
	
	public void eliminarJugador(Jugador j) {
		elementosMov[getIndex(j.pos)] = null;
	}

	public void agregarJugador(Jugador j) {
		elementosMov[getIndex(j.pos)] = j;
	}

	public Elemento obtenerJugador(Coordenada pos) {
		return elementosMov[getIndex(pos)];
	}
	public void intercambiarJugador(Coordenada posAnterior) {
		Jugador aux = elementosMov[getIndex(posAnterior)];
		elementosMov[getIndex(posAnterior)] = null;
		agregarJugador(aux);

	}

	/*
	 * metodos privados internos
	 */

	private int getIndex(Coordenada pos) {
		return (pos.x * ancho) + pos.y;
	}

	private int getIndex(int x, int y) {
		return (x * ancho) + y;
	}

	private boolean fueraDeRango(Coordenada pos) {
		return pos.x < 0 || pos.x >= ancho || pos.y < 0 || pos.y >= largo;
	}

	private void agregarElemneto(Elemento e) {
		elementos[getIndex(e.pos)] = e;
	}

	private void construirMapaVacio() {
		for (int x = 0; x < ancho; x++) {

			for (int y = 0; y < largo; y++) {
				agregarElemneto(new Fondo(x, y, this));
			}
		}

	}

	private void construirMapaAleatorio() {
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < largo; y++) {
				if (y == 0 || x == 0
						|| (x % 2 == 0 && y % 2 == 0)) { /* En los Bordes y las posiciones (par,par) siempre Muro */
					agregarElemneto(new Muro(new Coordenada(x, y), this));
				} else if (esEspacioReservadoJugador(x, y)) { /* Espacio para que el jugador se pueda mover */
					agregarElemneto(new Fondo(new Coordenada(x, y), this));
				} else {
					objetoAleatorio(x, y);
				}
			}
		}
	}

	private boolean esEspacioReservadoJugador(int x, int y) {
		return (x == 1 && (y == 1 || y == 2 || y == ancho - 2 || y == ancho - 1))
				|| (x == 2 && (y == 1 || y == ancho - 1)) || (x == largo - 2 && (y == 1 || y == ancho - 1))
				|| (x == largo - 1 && (y == 1 || y == 2 || y == ancho - 2 || y == ancho - 1));
	}

	private Elemento objetoAleatorio(int x, int y) {
		double rnd = Math.random();
		if (rnd < 0.33 && rnd > 0.66) {
			return new Fondo(new Coordenada(x, y), this);
		}
		return new Pared(new Coordenada(x, y), this);

	}

	private void inicializarArrays() {
		elementos = new Elemento[ancho * largo];
		elementosMov = new Jugador[ancho * largo];
	}
}
