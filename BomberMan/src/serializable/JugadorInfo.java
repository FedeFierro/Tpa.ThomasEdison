package serializable;

import helper.Helper;

public class JugadorInfo {
	public int numero;
	public String nombre;
	public int puntosNivel;
	public int puntoPartida;
	public String imagen;
	public int x;
	public int y;
	
	public JugadorInfo(int numero) {
		this.numero = numero;
		this.imagen = String.format(Helper.METHOD_JUGADOR,numero,11);
		this.nombre = "Jugador "+numero;
		this.puntosNivel = 0;
		this.puntoPartida= 0;
		setPosition();
	}
	
	private void setPosition(){
		switch (numero) {
		case 1:
			x=5;
			y=0;
			break;
		case 3:
			x=5;
			y=40;
			break;
		case 2:
			x=400;
			y=0;
			break;
		case 4:
			x=400;
			y=40;
			break;
		default:
			break;
		}
		
	}
}
