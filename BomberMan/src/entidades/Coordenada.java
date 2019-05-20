package entidades;

import helper.Helper;

public class Coordenada {

	protected int x;
	protected int y;
	public int rx;
	public int ry;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
		this.rx = x*Helper.PX;
		this.ry= y*Helper.PX;
	}
    public Coordenada(Coordenada pos) {
    	this.x = pos.x;
    	this.y = pos.y;
    	this.rx = pos.rx;
    	this.ry = pos.ry;
    }
	public boolean equals(Coordenada coor) {
		return (this.x == coor.x && this.y == coor.y);
	}
	
	protected boolean equals(int x, int y) {
		return (this.x == x && this.y == y);
	}
	protected void actualizarPosicion(int x, int y) {
		this.x += x;
		this.y += y;
		this.rx = this.x * Helper.PX;
		this.ry = this.y * Helper.PX;
	}
	
	protected void actualizarPosicionJugador(int x, int y) {
		this.rx += x;
		this.ry+=y;
		int despX = rx%Helper.PX;
		int despY = ry%Helper.PX;
		this.x = despX < 20 ? (rx-despX) /Helper.PX : (rx +(Helper.PX -despX))/Helper.PX;
		this.y = despY < 20 ? (ry-despY) /Helper.PX : (ry +(Helper.PX -despY))/Helper.PX;
	}
	protected Coordenada obtenerPosicionProvisoria(int x,int y) {
		return new Coordenada(this.x + x,this.y+y);
	}
	protected Coordenada obtenerPosicionProvisoriaJugador(int x,int y) {
		Coordenada c = new Coordenada(this);
		c.actualizarPosicionJugador(x, y);
		return c;
		
	}
	public String toString() {
		return this.x +"  "+this.y;
	}
	
}
