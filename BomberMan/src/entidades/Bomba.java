package entidades;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import helper.Helper;


public class Bomba extends Elemento{
	private Jugador jugador;
	protected int tiempoexplosion;
	private int rango;
	private Image[] imgs;
	/**
	 * Constructor Solo para Test
	 */
	public Bomba(int x, int y, Tablero tablero, Jugador jugador) {
		super(x,y,tablero);
		tiempoexplosion=3000;
		this.jugador = jugador;
		rango=3;
		
	}
	public Bomba (Tablero tablero, Jugador jugador) {
		super(jugador.pos,tablero);
		tiempoexplosion=3000;
		
		this.jugador = jugador;
		tablero.setExplosion(this);
		rango=3;
		Timer t = new Timer();
		TimerTask c = new TimerTask() {
            public void run() {
                explotar();
            }
        };
        t.schedule(c, tiempoexplosion);
	}
	
	public void explotar() {
		if(vivo) {
			vivo =false;
			boolean aIzquierda , aDerecha, aArriba, aAbajo;
			 	aIzquierda = aDerecha =aArriba =aAbajo =true;
			Coordenada posExpIzq = new Coordenada(this.pos);
			Coordenada posExpDer = new Coordenada(this.pos);
			Coordenada posExpArr = new Coordenada(this.pos);
			Coordenada posExpAba = new Coordenada(this.pos);
			
			Elemento e = tablero.getJugador(this.pos);
				if (e!=null) e.explotar();
			
			for(int i=1; i<=rango; i++) {
				/*HACIA ATRAS*/
				posExpIzq.actualizarPosicion(-1, 0);
				aIzquierda=aIzquierda && tablero.puedeExplotar(posExpIzq);
				if(aIzquierda) {
					e = tablero.getElemento(posExpIzq);
					e.explotar();
				 aIzquierda = aIzquierda && e.puedeSeguirExplotando();
						
				}
				
				/*HACIA ADELANTE*/
				posExpDer.actualizarPosicion(1, 0);
				aDerecha= aDerecha && tablero.puedeExplotar(posExpDer);
				if(aDerecha) {
					e = tablero.getElemento(posExpDer);
					e.explotar();
					aDerecha =  aDerecha && e.puedeSeguirExplotando();
				}
				/*HACIA ARRIBA*/
				posExpArr.actualizarPosicion(0, -1);
				aArriba=aArriba && tablero.puedeExplotar(posExpArr);
				if(aArriba) {
					e = tablero.getElemento(posExpArr);
					e.explotar();
					aArriba = aArriba &&  e.puedeSeguirExplotando();
				}
				/*HACIA ABAJO*/
				posExpAba.actualizarPosicion(0, 1);
				aAbajo = aAbajo && tablero.puedeExplotar(posExpAba);
				if(aAbajo) {
					e = tablero.getElemento(posExpAba);
					e.explotar();
					aAbajo = aAbajo && e.puedeSeguirExplotando();
				}
			}
			this.jugador.explotoBomba();
			tablero.quitarExplosion(this);;
		}
	}//fin explotarBomba
	
	
	protected void loadImages() {
		imgs=new Image[3];
		for(int i=1; i<4;i++) {
			String name ="/bomba/0"+i+Helper.IMG_EXT;
			imgs[i-1]= Helper.getImage(getClass().getResource(name));
		}
		imgFinal=imgs[0];
		
	}
}
	