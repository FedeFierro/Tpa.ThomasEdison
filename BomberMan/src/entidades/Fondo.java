package entidades;

import helper.Helper;

public class Fondo extends Elemento {

	public Fondo(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
		loadImages();
	}
	public Fondo(int x,int y, Tablero tablero) {
		super(x, y, tablero);
	}
	
	@Override
	public void explotar() {
		return;
		
	}
	@Override
	public boolean esTransitable() {
		return true;
	}
	protected void loadImages() {
		
		imgFinal= Helper.getImage(getClass().getResource("/fondo/01"+Helper.IMG_EXT));
		
	}

	
	

}
