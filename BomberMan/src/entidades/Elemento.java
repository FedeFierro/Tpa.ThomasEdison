package entidades;

public abstract class Elemento {
	protected Coordenada pos;
	protected Tablero tablero;
	protected boolean vivo;
	protected String imgFinal;
	protected String sonido;
	protected boolean playSound;

	/*
	 *Constructor de Test 
	 */
	public Elemento(int x, int y, Tablero tablero) {
		this.pos = new Coordenada(x, y);
		this.tablero = tablero;
		this.tablero.setElemento(this);
		vivo=true;
		
	}
	public Elemento(Tablero tablero) {
		this.tablero = tablero;
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
	public abstract int explotar();
	
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
	public String show() {
		return imgFinal;
	}
	
	public String getSound() {
		if(sonido!=null && !sonido.isEmpty() && !playSound) {
			playSound=true;
		}else {
			playSound=false;
			sonido="";
		}
		return sonido;
	}
	protected abstract void setImageName(Integer numero);
}
