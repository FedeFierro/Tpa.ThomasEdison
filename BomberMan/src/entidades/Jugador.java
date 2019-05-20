package entidades;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import helper.DireccionEnum;
import helper.Helper;

public class Jugador extends Elemento {

	private static int cont = 0;
	private final int MAXBOMBAS = 2;
	private int bombasPlantadas;
	private Image[] imgs;
	private Image[] imgN, imgS, imgO, imgE;
	private int numeroJugador;
	private int index = 0;
	private DireccionEnum direccion;

	/**
	 * Constructor de la clase Jugador
	 */
	public Jugador(int x, int y, Tablero tablero) {
		super(new Coordenada(x, y), tablero);
		cont++;
		numeroJugador = cont;
		bombasPlantadas = 0;
		loadImages();
		tablero.setJugador(this);
	}

	/**
	 * M�todo que mueve un jugador
	 * 
	 * @param x Es el movimiento en x de l jugador
	 * @param y Es el movimiento en y de l jugador
	 */
	/*
	 * public void moverse(int x, int y) { if(vivo) {
	 * if(tablero.puedeMover(pos.obtenerPosicionProvisoria(x, y))) { Coordenada
	 * posAnterior = new Coordenada(this.pos); this.pos.actualizarPosicion(x, y);
	 * tablero.intercambiarJugador(posAnterior); Elemento e =
	 * tablero.getExplosion(pos); if(e!=null) { explotar(); } }
	 * 
	 * } }
	 */

	public void moverse(int x, int y) {
		if (vivo) {
			setDireccion(x, y);
			animateMove();
			if (puedeMover(x, y)) {
				Coordenada posAnt = new Coordenada(pos);
				pos.actualizarPosicionJugador(x, y);
				if (!pos.equals(posAnt)) {
					tablero.intercambiarJugador(posAnt);
					Elemento exp = tablero.getExplosion(pos);
					if (exp != null) {
						explotar();
					}

				}
			}

		}
	}

	/**
	 * Planta una bomba
	 */
	public void plantarBomba() {
		if (bombasPlantadas < MAXBOMBAS) {
			bombasPlantadas += 1;
			new Bomba(tablero, this);
		}
	}

	@Override
	public void explotar() {
		vivo = false;
		bombasPlantadas = 0;
		animateExplosion(this);
	}

	public void explotoBomba() {
		bombasPlantadas--;
	}

	protected void loadImages() {
		imgs = new Image[5];
		imgN = new Image[5];
		imgS = new Image[5];
		imgE = new Image[5];
		imgO = new Image[5];
		for (int i = 1; i < 6; i++) {
			String name = "/player" + numeroJugador + "/%d%d" + Helper.IMG_EXT;
			imgs[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 4, i)));
			imgN[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 0, i)));
			imgS[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 1, i)));
			imgO[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 2, i)));
			imgE[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 3, i)));
		}
		imgFinal = imgS[0];

	}

	private void animateExplosion(Jugador j) {

		Timer t = new Timer();
		TimerTask d = new TimerTask() {
			int cont = 0;

			public void run() {
				cont++;
				if (cont > 4) {
					tablero.quitarJugador(j);
					cancel();
				} else {
					imgFinal = imgs[cont];
				}
			}
		};

		t.schedule(d, 0, Helper.TIME_EXP / 5);
	}

	private void setDireccion(int x, int y) {
		DireccionEnum nvaDir = x < 0 ? DireccionEnum.O
				: x > 0 ? DireccionEnum.E : y < 0 ? DireccionEnum.N : y > 0 ? DireccionEnum.S : null;
		if (direccion != nvaDir) {
			direccion = nvaDir;
			index = 0;
		}
	}

	private void animateMove() {

		switch (direccion) {
		case O:
			imgFinal = imgO[index];
			break;
		case E:
			imgFinal = imgE[index];
			break;
		case N:
			imgFinal = imgN[index];
			break;
		case S:
			imgFinal = imgS[index];
			break;
		default:
			break;
		}
		index = index > 3 ? 0 : index + 1;
	}

	private Boolean puedeMover(int x, int y) {
		switch (direccion) {
		case E:
		case O:
			if (pos.ry % Helper.PX > 0) {
				return false;
			}
			if (pos.rx % Helper.PX > 0) {
				return true;
			}
			return tablero.puedeMover(pos.obtenerPosicionProvisoriaJugador(x*5, y*5));

		case N:
		case S:
			if (pos.rx % Helper.PX > 0) {
				return false;
			}
			if (pos.ry % Helper.PX > 0) {
				return true;
			}
			return tablero.puedeMover(pos.obtenerPosicionProvisoriaJugador(x*5, y*5));
		default:
			break;

		}
		return false;
	}

}
