package entidades;

import java.util.Timer;
import java.util.TimerTask;
import helper.DireccionEnum;
import helper.Helper;
import serializable.JugadorInfo;

public class Jugador extends Elemento {

	private static int cont = 0;
	private final int MAXBOMBAS = 2;
	private int bombasPlantadas;
	private int index = 0;
	private DireccionEnum direccion;
	public JugadorInfo info;
	
	/**
	 * Constructor de la clase Jugador
	 */
	public Jugador(Tablero tablero) {
		super(tablero);
		cont++;
		info = new JugadorInfo(cont);
		pos =  tablero.getPosicionInicialJugador(info.numero);
		bombasPlantadas = 0;
		setImageName(11);
		loadSound();
	}
	
	public Jugador(int x, int y, Tablero tablero) {
		super(new Coordenada(x, y), tablero);
		cont++;
		info= new JugadorInfo(cont);
		bombasPlantadas = 0;
		setImageName(11);
		loadSound();
		tablero.setJugador(this);
	}

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
		if(vivo)
			if (bombasPlantadas < MAXBOMBAS) {
				bombasPlantadas += 1;
				new Bomba(tablero, this);
			}
	}

	@Override
	public int explotar() {
		bombasPlantadas = 0;
		animateExplosion();
		return Helper.PUNTO_JUGADOR;
	}

	public void explotoBomba() {
		bombasPlantadas--;
	}


	private void animateExplosion() {
		Jugador j = this;
		Timer t = new Timer();
		sonido.start();
		TimerTask d = new TimerTask() {
			int cont = 1;

			public void run() {
				cont++;
				if (cont > 5) {
					vivo = false;
					tablero.quitarJugador(j);
					cancel();
				} else {
					setImageName(40+cont);
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
			setImageName(20+index+1); 
			break;
		case E:
			setImageName(30+index+1); 
			break;
		case N:
			setImageName(index+1);
			break;
		case S:
			setImageName(10+index+1);
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
	
	protected void loadSound() {
		String name="/sounds/LifeLost"+Helper.SOUND_EXT;
		sonido = Helper.getSonido(getClass().getResourceAsStream(name));
	}

	@Override
	protected void setImageName(Integer numero) {
		String num = numero<10?"0"+numero: numero.toString();
		imgFinal =  String.format(Helper.METHOD_JUGADOR, info.numero,num);
		
	}
	public void sumarPuntos(int puntos) {
		if(vivo) {
		info.puntosNivel+=puntos;
		}
	}
	public void sumarPuntoPartida(int puntos) {
		if(vivo) {
			info.puntoPartida+=puntos;
		}
	}
	public void reiniciarNivel() {
		vivo=true;
		info.puntosNivel=0;
		setImageName(11);
		pos = tablero.getPosicionInicialJugador(info.numero);
	}

}
