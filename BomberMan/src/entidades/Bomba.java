package entidades;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import helper.DireccionEnum;
import helper.Helper;

public class Bomba extends Elemento {
	private Jugador jugador;
	protected int tiempoexplosion;
	private int rango;
	private Image[] imgs;

	/**
	 * Constructor Solo para Test
	 */
	public Bomba(int x, int y, Tablero tablero, Jugador jugador) {
		super(x, y, tablero);
		tiempoexplosion = 3000;
		this.jugador = jugador;
		rango = 3;

	}

	public Bomba(Tablero tablero, Jugador jugador) {
		super(new Coordenada(jugador.pos.x, jugador.pos.y), tablero);
		loadImages();
		loadSound();
		tiempoexplosion = 3000;
		activar();		
		this.jugador = jugador;
		tablero.setBomba(this);
		rango = 3;

	}

	public void explotar() {
		if (vivo) {
			vivo = false;
			boolean oeste, este, norte, sur;
			oeste = true;
			este = true;
			norte = true;
			sur = true;
			boolean fin = false;
			Coordenada posOeste = new Coordenada(this.pos);
			Coordenada posEste = new Coordenada(this.pos);
			Coordenada posNorte = new Coordenada(this.pos);
			Coordenada posSur = new Coordenada(this.pos);
			tablero.quitarBomba(this);
			
			Elemento e = tablero.getJugador(this.pos);
			new Explosion(this.pos, tablero, DireccionEnum.C, e);
			// if (e!=null) e.explotar();

			for (int i = 1; i <= rango; i++) {
				fin = i == rango;
				/* HACIA ATRAS */
				posOeste.actualizarPosicion(-1, 0);
				oeste = oeste && tablero.puedeExplotar(posOeste);
				if (oeste) {
					e = tablero.getElemento(posOeste);
					new Explosion(posOeste, tablero, (fin ? DireccionEnum.O : DireccionEnum.H), e);
					// e.explotar();
					oeste = oeste && e.puedeSeguirExplotando();

				}

				/* HACIA ADELANTE */
				posEste.actualizarPosicion(1, 0);
				este = este && tablero.puedeExplotar(posEste);
				if (este) {
					e = tablero.getElemento(posEste);
					new Explosion(posEste, tablero, (fin ? DireccionEnum.E : DireccionEnum.H), e);
					// e.explotar();
					este = este && e.puedeSeguirExplotando();
				}
				/* HACIA ARRIBA */
				posNorte.actualizarPosicion(0, -1);
				norte = norte && tablero.puedeExplotar(posNorte);
				if (norte) {
					e = tablero.getElemento(posNorte);
					new Explosion(posNorte, tablero, (fin ? DireccionEnum.N : DireccionEnum.V), e);
					// e.explotar();
					norte = norte && e.puedeSeguirExplotando();
				}
				/* HACIA ABAJO */
				posSur.actualizarPosicion(0, 1);
				sur = sur && tablero.puedeExplotar(posSur);
				if (sur) {
					e = tablero.getElemento(posSur);
					new Explosion(posSur, tablero, (fin ? DireccionEnum.S : DireccionEnum.V), e);
					// e.explotar();
					sur = sur && e.puedeSeguirExplotando();
				}
			}
			this.jugador.explotoBomba();

		}
	}// fin explotarBomba

	protected void activar() {
		Timer t = new Timer();
		
		TimerTask a = new TimerTask() {
			int cont = 0;

			@Override
			public void run() {
				sonido.start();
				imgFinal = imgs[cont];
				cont = cont == 2 ? 0 : cont + 1;
			}
		};

		TimerTask c = new TimerTask() {
			public void run() {
				explotar();
			}
		};
		t.schedule(a, 0, 1000 / 4);
		t.schedule(c, tiempoexplosion);
	}

	protected void loadImages() {
		imgs = new Image[3];
		for (int i = 1; i < 4; i++) {
			String name = "/bomba/0" + i + Helper.IMG_EXT;
			imgs[i - 1] = Helper.getImage(getClass().getResource(name));
		}
		imgFinal = imgs[0];

	}
	
	
	protected void loadSound() {
		String name="/bomba/Bomba"+Helper.SOUND_EXT;
		sonido = Helper.getSonido(getClass().getResourceAsStream(name));
	}

}