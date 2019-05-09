package entidades;

public class Coordenada {

	protected int x;
	protected int y;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
    public Coordenada(Coordenada pos) {
    	this.x = pos.x;
    	this.y = pos.y;
    }
	public boolean equals(Coordenada coor) {
		return (this.x == coor.x && this.y == coor.y);
	}

	protected boolean equals(int x, int y) {
		return (this.x == x && this.y == y);
	}
	
	protected void actualizarPosicion(int x, int y) {
		this.x += x;
		this.y+=y;
	}
	protected Coordenada obtenerPosicionProvisoria(int x,int y) {
		return new Coordenada(this.x + x,this.y+y);
	}
	public String toString() {
		return this.x +"  "+this.y;
	}
}
