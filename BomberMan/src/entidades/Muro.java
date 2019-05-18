package entidades;

import java.net.URL;

import helper.Helper;

public class Muro extends Elemento{
	
	
	public Muro (int x, int y, Tablero tablero) {
		super(x,y,tablero);
	}
	public Muro (Coordenada pos, Tablero tablero) {
		super(pos,tablero);
	}
	

	@Override
	public void explotar() {
		return;
		
	}
	@Override
	public boolean puedeSeguirExplotando() {
		return false;
	}
	protected void loadImages() {
		imgFinal= Helper.getImage(getClass().getResource("/muro/01"+Helper.IMG_EXT));
		
	}
	
}
