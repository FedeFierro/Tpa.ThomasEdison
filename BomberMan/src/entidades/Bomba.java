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
		loadImages();
		tiempoexplosion=3000;
		activar();
		this.jugador = jugador;
		tablero.setExplosion(this);
		rango=3;
		
	}
	
	public void explotar() {
		if(vivo) {
			vivo =false;
			boolean aIzquierda , aDerecha, aArriba, aAbajo;
			 	aIzquierda = aDerecha =aArriba =aAbajo =true;
			 boolean fin=false;	
			Coordenada posExpIzq = new Coordenada(this.pos);
			Coordenada posExpDer = new Coordenada(this.pos);
			Coordenada posExpArr = new Coordenada(this.pos);
			Coordenada posExpAba = new Coordenada(this.pos);
			tablero.quitarExplosion(this);;
			Elemento e = tablero.getJugador(this.pos);
				new Explosion(posExpAba, tablero, "C", e);
				//if (e!=null) e.explotar();
			
			
			for(int i=1; i<=rango; i++) {
				fin = i==rango;
				/*HACIA ATRAS*/
				posExpIzq.actualizarPosicion(-1, 0);
				aIzquierda=aIzquierda && tablero.puedeExplotar(posExpIzq);
				if(aIzquierda) {
					e = tablero.getElemento(posExpIzq);
					new Explosion(posExpIzq, tablero, (fin? "W": "H"), e);
					//e.explotar();
				 aIzquierda = aIzquierda && e.puedeSeguirExplotando();
						
				}
				
				/*HACIA ADELANTE*/
				posExpDer.actualizarPosicion(1, 0);
				aDerecha= aDerecha && tablero.puedeExplotar(posExpDer);
				if(aDerecha) {
					e = tablero.getElemento(posExpDer);
					new Explosion(posExpDer, tablero,(fin ? "E" : "H"), e);  
					//e.explotar();
					aDerecha =  aDerecha && e.puedeSeguirExplotando();
				}
				/*HACIA ARRIBA*/
				posExpArr.actualizarPosicion(0, -1);
				aArriba=aArriba && tablero.puedeExplotar(posExpArr);
				if(aArriba) {
					e = tablero.getElemento(posExpArr);
					new Explosion(posExpArr, tablero, (fin? "N" : "V"), e);
					//e.explotar();
					aArriba = aArriba &&  e.puedeSeguirExplotando();
				}
				/*HACIA ABAJO*/
				posExpAba.actualizarPosicion(0, 1);
				aAbajo = aAbajo && tablero.puedeExplotar(posExpAba);
				if(aAbajo) {
					e = tablero.getElemento(posExpAba);
					new Explosion(posExpAba, tablero, (fin? "S" : "V"), e);
					//e.explotar();
					aAbajo = aAbajo && e.puedeSeguirExplotando();
				}
			}
			this.jugador.explotoBomba();
			
		}
	}//fin explotarBomba
	protected void activar() {
		Timer t = new Timer();
		
		TimerTask a = new TimerTask() {
			int cont = 0;
			@Override
			public void run() {
				imgFinal=imgs[cont];
				cont= cont==2?0:cont+1;
			}
		}; 
		
		TimerTask c = new TimerTask() {
            public void run() {
                explotar();
            }
        };
        t.schedule(a, 0, 1000/4);
        t.schedule(c, tiempoexplosion);
	}
	
	protected void loadImages() {
		imgs=new Image[3];
		for(int i=1; i<4;i++) {
			String name ="/bomba/0"+i+Helper.IMG_EXT;
			imgs[i-1]= Helper.getImage(getClass().getResource(name));
		}
		imgFinal=imgs[0];
		
	}
	
}