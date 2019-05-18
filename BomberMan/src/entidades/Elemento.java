package entidades;

import java.awt.Image;

public abstract class Elemento {
	protected Coordenada pos;
	protected Tablero tablero;
	protected boolean vivo;
	protected Image imgFinal;

	/*
	 *Constructor de Test 
	 */
	public Elemento(int x, int y, Tablero tablero) {
		this.pos = new Coordenada(x, y);
		this.tablero = tablero;
		this.tablero.setElemento(this);
		vivo=true;
		
	}
	public Elemento(Coordenada pos, Tablero tablero) {
		this.pos = new Coordenada(pos);
		this.tablero = tablero;
		vivo=true;
	}

	/**
	 * Metodo abstracto para destruir.
	 */
	public abstract void explotar();
	
	/*Hacer override en fondo*/
	public boolean esTransitable() {
		return false;
	}
	/*hacer override en muro y pared*/
	public boolean puedeSeguirExplotando() {
		return true;
	}

	public boolean getVivo() {
		return vivo;
	}
	public Coordenada getPos() {
		return this.pos;
	}
	public Image show() {
		return imgFinal;
	}
	
	protected abstract void loadImages();
	

}
