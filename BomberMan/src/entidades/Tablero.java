package entidades;

public class Tablero {

	private int ancho = 15;
	private int largo = 15;
	private Elemento[] elementos;
	private Elemento[] explosiones;
	private Jugador[] elementosMov;
	private int nivel;

	/*
	 * Constructor de Tests
	 */
	public Tablero(int a, int l) {
		this.ancho = a;
		this.largo = l;
		inicializarArrays();
		construirMapaVacio();
	}

	public Tablero() {
		nivel = 1;
		iniciartablero();
	}

	private void iniciartablero() {
		inicializarArrays();
		construirMapaAleatorio();
	}

	public Elemento getElemento(Coordenada pos) {
		Elemento e = elementosMov[getIndex(pos)];
		e= e==null? explosiones[getIndex(pos)]: e;
		return e == null ? elementos[getIndex(pos)] : e;
	}
	
	public Elemento getElemento(int x, int y) {
		return elementos[getIndex(x,y)];
	}

	public void setElemento(Elemento e) {
		elementos[getIndex(e.pos)] = e;
	}

	public void quitarElemento(Elemento e) {
		elementos[getIndex(e.pos)] = new Fondo(e.pos, this);
	}
	public Elemento getExplosion(int x, int y) {
		return explosiones[getIndex(x,y)];
	}
	public void setExplosion(Elemento e) {
		explosiones[getIndex(e.pos)]=e;
	}
	public void quitarExplosion(Coordenada pos) {
		explosiones[getIndex(pos)]=null;
	}

	public void quitarElemento(int x, int y) {
		elementos[getIndex(x, y)] = new Fondo(x, y, this);
	}

	public boolean puedeMover(Coordenada pos) {
		return !fueraDeRango(pos) && getElemento(pos).esTransitable();
	}

	public boolean puedeExplotar(Coordenada pos) {
		return !fueraDeRango(pos);
	}

	public void quitarJugador(Jugador j) {
		elementosMov[getIndex(j.pos)] = null;
	}

	public void setJugador(Jugador j) {
		elementosMov[getIndex(j.pos)] = j;
	}

	public Elemento getJugador(Coordenada pos) {
		return elementosMov[getIndex(pos)];
	}
	public Elemento getJugador(int x, int y) {
		return elementosMov[getIndex(x,y)];
	}

	public void intercambiarJugador(Coordenada posAnterior) {
		Jugador aux = elementosMov[getIndex(posAnterior)];
		elementosMov[getIndex(posAnterior)] = null;
		setJugador(aux);

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
				if (y == 0 || x == 0 || (x % 2 == 0 && y % 2 == 0) 
						|| x==ancho-1 || y == largo-1) { /* En los Bordes y las posiciones (par,par) siempre Muro */
					agregarElemneto(new Muro(new Coordenada(x, y), this));
				} else if (esEspacioReservadoJugador(x, y)) { /* Espacio para que el jugador se pueda mover */
					agregarElemneto(new Fondo(new Coordenada(x, y), this));
				} else {
					agregarElemneto(objetoAleatorio(x, y));
				}
			}
		}
	}


	private boolean esEspacioReservadoJugador(int x, int y) {
		return ( x == 1 && (y == 1 || y == 2 || y == largo - 2 || y == largo - 3))
				|| (x == 2 && (y == 1 || y == largo - 2)) || 
				(x == ancho - 3 && (y == 1 || y == largo - 2))
				|| (x == ancho - 2 && (y == 1 || y == 2 || y == largo - 3 || y == largo - 2));
	}

	private Elemento objetoAleatorio(int x, int y) {
		double rnd = Math.random();
		double limite = 0.1 + nivel*0.025; /* para nivel 1 aprox 25% de paredes sube 5% por nivel*/
		
		if (rnd > 0.5 -limite && rnd < 0.5 + limite){
			return new Pared(new Coordenada(x, y), this);
		}
		return new Fondo(new Coordenada(x, y), this);
	}

	private void inicializarArrays() {
		explosiones = new Elemento[ancho * largo];
		elementos = new Elemento[ancho * largo];
		elementosMov = new Jugador[ancho * largo];
	}

	
}
