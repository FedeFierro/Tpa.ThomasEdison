package entidades;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import helper.Helper;

public class Jugador extends Elemento{
	
	private static int cont=0;
	private final int MAXBOMBAS =2;
	private int bombasPlantadas;
	private Image[] imgs;
	private Image[] imgArriba, imgAbajo, imgIzquierda, imgDerecha;
	private int numeroJugador;
	/**
	 * Constructor de la clase Jugador
	 */
	public Jugador (int x, int y, Tablero tablero) {
		super(new Coordenada (x,y),tablero);
		cont++;
		numeroJugador =cont;
		bombasPlantadas = 0;
		loadImages();
		tablero.setJugador(this);
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
				Elemento e = tablero.getExplosion(pos);
				if(e!=null) {
					explotar();
				}
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
		animateExplosion(this);
	}
	public void explotoBomba() {
	   bombasPlantadas--;	
	}
	protected void loadImages() {
		imgs = new Image[5];
		imgArriba = new Image[5];
		imgAbajo = new Image[5];
		imgDerecha = new Image[5];
		imgIzquierda = new Image[5];
		for (int i = 1; i < 6; i++) {
			String name = "/player" + numeroJugador + "/%d%d"+Helper.IMG_EXT;
			imgs[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 4, i)));
			imgArriba[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 0, i)));
			imgAbajo[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 1, i)));
			imgIzquierda[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 2, i)));
			imgDerecha[i - 1] = Helper.getImage(getClass().getResource(String.format(name, 3, i)));
		}
		imgFinal = imgAbajo[0];

	}
	private void animateExplosion(Jugador j) {
		
		Timer t = new Timer();
		TimerTask d = new TimerTask() {
			int cont =0;
        	public void run() {
        		cont++;
        		if(cont>4) {
        			tablero.quitarJugador(j);
        			cancel();
        		}else{
        		imgFinal=imgs[cont];
        		}
			}
        };
        
        t.schedule(d,0, Helper.TIME_EXP/5);
	}
	
}
