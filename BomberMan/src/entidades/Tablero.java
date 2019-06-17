package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import helper.Helper;
import serializable.ElementoInfo;
import serializable.JugadorInfo;
import serializable.TableroInfo;

public class Tablero {

	private int ancho = 15;
	private int largo = 15;
	private Elemento[][] elementos;
	private Elemento[][] bombas;
	private Elemento[][] explosiones;
	private Jugador[][] elementosMov;
	private List<Jugador> jugadores;
	private Clip sonido;
	private int tiempo;
	private long start;
	public TableroInfo info;

	/*
	 * Constructor de Tests
	 */
	public Tablero(int a, int l, int tiempo, int puntosPartida){
		this.ancho = a;
		this.largo = l;
		inicializarArrays();
		construirMapaVacio();
		info = new TableroInfo(puntosPartida);
		this.tiempo = tiempo;
		jugadores = new ArrayList<Jugador>();
	
	}

	public Tablero(int tiempo, int puntosPartida) {
		info = new TableroInfo(puntosPartida);
		this.tiempo = tiempo;
		jugadores = new ArrayList<Jugador>();
		loadSound();
	}

	private void iniciartablero() {
		inicializarArrays();
		construirMapaAleatorio();

	}

	public Elemento getElemento(Coordenada pos) {
		Elemento e = elementosMov[pos.x][pos.y];
		e = e == null ? explosiones[pos.x][pos.y] : e;
		e = e == null ? bombas[pos.x][pos.y] : e;
		return e == null ? elementos[pos.x][pos.y] : e;
	}

	public Elemento getElemento(int x, int y) {
		return elementos[x][y];
	}

	public void setElemento(Elemento e) {
		elementos[e.pos.x][e.pos.y] = e;
	}

	public void quitarElemento(Elemento e) {
		elementos[e.pos.x][e.pos.y] = new Fondo(e.pos, this);
	}

	public Elemento getBomba(int x, int y) {
		return bombas[x][y];
	}

	public void setBomba(Bomba b) {
		bombas[b.pos.x][b.pos.y] = b;
	}

	public void quitarBomba(Bomba b) {
		bombas[b.pos.x][b.pos.y] = null;
	}

	public Elemento getExplosion(int x, int y) {
		return explosiones[x][y];
	}

	public Elemento getExplosion(Coordenada pos) {
		return explosiones[pos.x][pos.y];
	}

	public void setExplosion(Elemento e) {
		explosiones[e.pos.x][e.pos.y] = e;
	}

	public void quitarExplosion(Elemento e) {
		explosiones[e.pos.x][e.pos.y] = null;
	}

	public void quitarElemento(int x, int y) {
		elementos[x][y] = new Fondo(x, y, this);
	}

	public boolean puedeMover(Coordenada pos) {
		return !fueraDeRango(pos) && getElemento(pos).esTransitable();
	}

	public boolean puedeExplotar(Coordenada pos) {
		return !fueraDeRango(pos);
	}

	public void quitarJugador(Jugador j) {
		elementosMov[j.pos.x][j.pos.y] = null;
	}

	public void setJugador(Jugador j) {
		// elementosMov[j.pos.x][j.pos.y] = j;
		jugadores.add(j);
		info.jugadoresInfo.add(j.info);
	}

	public Elemento getJugador(Coordenada pos) {
		return elementosMov[pos.x][pos.y];
	}

	public Elemento getJugador(int x, int y) {
		return elementosMov[x][y];
	}

	public void intercambiarJugador(Coordenada posAnterior) {
		Jugador aux = elementosMov[posAnterior.x][posAnterior.y];
		elementosMov[posAnterior.x][posAnterior.y] = null;
		elementosMov[aux.pos.x][aux.pos.y] = aux;
	}

	public int getAncho() {
		return this.ancho;
	}

	public int getLargo() {
		return this.largo;
	}

	public Coordenada getPosicionInicialJugador(int numJugador) {
		int x = numJugador == 1 || numJugador == 3 ? 1 : ancho - 2;
		int y = numJugador == 1 || numJugador == 2 ? 1 : largo - 2;
		return new Coordenada(x, y);
	}
	public TableroInfo getSerializeInfo() {

		if (info.pausa) {
			return info;
		}
		List<JugadorInfo> jugInfo = new ArrayList<JugadorInfo>();
		List<ElementoInfo> elements = new ArrayList<ElementoInfo>();
		List<ElementoInfo> bombs = new ArrayList<ElementoInfo>();
		List<ElementoInfo> exps = new ArrayList<ElementoInfo>();
		List<ElementoInfo> players = new ArrayList<ElementoInfo>();
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < ancho; y++) {
				Elemento e = elementos[x][y];
				elements.add(new ElementoInfo(e.pos.rx, Helper.HEAD_Y + e.pos.ry, e.imgFinal, Helper.PX, Helper.PX));
				Elemento b = bombas[x][y];
				if (b != null) {
					bombs.add(new ElementoInfo(b.pos.rx, Helper.HEAD_Y + b.pos.ry, b.imgFinal, Helper.PX, Helper.PX));
				}
				Elemento exp = explosiones[x][y];
				if (exp != null) {
					exps.add(new ElementoInfo(exp.pos.rx, Helper.HEAD_Y + exp.pos.ry, exp.imgFinal, Helper.PX,
							Helper.PX));
				}
				Jugador j = elementosMov[x][y];
				if (j != null) {
					players.add(new ElementoInfo(j.pos.rx, Helper.HEAD_Y + j.pos.ry, j.imgFinal, Helper.PX, Helper.PX));
				}

			}
		}
		for (Jugador j : jugadores) {
			jugInfo.add(j.info);
		}
		info.tiempo = getTime().toString();
		info.elementos = new ArrayList<ElementoInfo>();
		info.elementos.addAll(elements);
		info.elementos.addAll(bombs);
		info.elementos.addAll(exps);
		info.elementos.addAll(players);
		return info;
	}
	public void iniciarJuego() {
		crearNuevoNivel();
	}


	/*
	 * metodos privados internos
	 */

	private boolean fueraDeRango(Coordenada pos) {
		return pos.x < 0 || pos.x >= ancho || pos.y < 0 || pos.y >= largo;
	}

	private void construirMapaVacio() {
		for (int x = 0; x < ancho; x++) {

			for (int y = 0; y < largo; y++) {
				setElemento(new Fondo(x, y, this));
			}
		}

	}

	private void construirMapaAleatorio() {
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < largo; y++) {
				if (y == 0 || x == 0 || (x % 2 == 0 && y % 2 == 0) || x == ancho - 1
						|| y == largo - 1) { /* En los Bordes y las posiciones (par,par) siempre Muro */
					setElemento(new Muro(new Coordenada(x, y), this));
				} else if (esEspacioReservadoJugador(x, y)) { /* Espacio para que el jugador se pueda mover */
					setElemento(new Fondo(new Coordenada(x, y), this));
				} else {
					setElemento(objetoAleatorio(x, y));
				}
			}
		}
	}

	private boolean esEspacioReservadoJugador(int x, int y) {
		return (x == 1 && (y == 1 || y == 2 || y == largo - 2 || y == largo - 3))
				|| (x == 2 && (y == 1 || y == largo - 2)) || (x == ancho - 3 && (y == 1 || y == largo - 2))
				|| (x == ancho - 2 && (y == 1 || y == 2 || y == largo - 3 || y == largo - 2));
	}

	private Elemento objetoAleatorio(int x, int y) {
		double rnd = Math.random();
		double limite = 0.1 + info.nivel * 0.025; /* para nivel 1 aprox 25% de paredes sube 5% por nivel */

		if (rnd > 0.5 - limite && rnd < 0.5 + limite) {
			return new Pared(new Coordenada(x, y), this);
		}
		return new Fondo(new Coordenada(x, y), this);
	}

	private void inicializarArrays() {
		explosiones = new Elemento[ancho][largo];
		bombas = new Elemento[ancho][largo];
		elementos = new Elemento[ancho][largo];
		elementosMov = new Jugador[ancho][largo];

	}

	protected void loadSound() {
		String name = "/sounds/StageTheme" + Helper.SOUND_EXT;
		sonido = Helper.getSonido(getClass().getResourceAsStream(name));
	}

	public Clip getSound() {
		return sonido;
	}

	
	private Integer getTime() {
		if (info.pausa || info.finJuego) {
			return 0;
		}
		int cont = 0;
		for (Jugador j : jugadores) {
			if (j.vivo) {
				cont++;
			}

		}
		int time = (int) ((System.currentTimeMillis() - start) / 1000);
		if (time > tiempo || cont == 1) {
			finalizarNivel();
			return 0;
		}
		return time;
	}
	
	private void finalizarNivel() {
		info.pausa = true;
		setGanador();
		crearNuevoNivel();
	}

	private void setGanador() {
		Jugador ganador = null;
		for(Jugador jug : jugadores){
			if(ganador==null) {
				ganador =jug;
			}
			else if(jug.info.puntosNivel > ganador.info.puntosNivel) {
				ganador = jug;
			}else if(jug.info.puntosNivel == ganador.info.puntosNivel ){
				
			}
		}
		if (ganador != null) {
			ganador.sumarPuntoPartida(Helper.PUNTO_PARTIDA);
			info.ganador = String.format(Helper.TEXT_WIN, ganador.info.nombre);
			if (ganador.info.puntoPartida >= info.puntosPartida) {
				finalizarJuego();
			}
		} else {
			info.ganador = Helper.TEXT_EMPATE;
		}
		

	}
	private void finalizarJuego() {
		info.finJuego=true;
		
	}
	
	
	private void crearNuevoNivel() {
		info.nivel++;
		Timer tim = new Timer();
		TimerTask cambio = new TimerTask() {

			@Override
			public void run() {
				iniciartablero();
				for (Jugador j : jugadores) {
					j.reiniciarNivel();
					elementosMov[j.pos.x][j.pos.y] = j;
				}
				info.pausa = false;
				start = System.currentTimeMillis();
			}
		};
		tim.schedule(cambio, Helper.DELAY);
	}

}
