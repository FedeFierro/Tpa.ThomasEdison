package entidades;

import java.awt.Image;

import helper.Helper;

public class Pared extends Elemento {

	private Image[] imgs;
	
	
	public Pared(int x, int y, Tablero tablero) {
		super(x, y, tablero);
	}

	public Pared(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
	}

	@Override
	public void explotar() {
		super.vivo = false;
		tablero.quitarElemento(this);
	}
	@Override
	public boolean puedeSeguirExplotando() {
		return false;
	}
	protected void loadImages() {
		imgs= new Image[4];
		for(int i=1;i<5;i++) {
			String name = "pared/0"+i;
			imgs[i-1]=Helper.getImage(this.getClass().getClassLoader(), name);
		}
		imgFinal=imgs[0];
	}
	
	
}
