package entidades;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import helper.Helper;

public class Pared extends Elemento {

	private Image[] imgs;
	
	
	public Pared(int x, int y, Tablero tablero) {
		super(x, y, tablero);
	}

	public Pared(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
		loadImages();
	}

	@Override
	public void explotar() {
		if(vivo) {
			super.vivo = false;
			animateExplosion(this);
//			tablero.quitarElemento(this);
		}
	}
	@Override
	public boolean puedeSeguirExplotando() {
		return false;
	}
	protected void loadImages() {
		imgs= new Image[4];
		for(int i=1;i<5;i++) {
			String name = "/pared/0"+i+Helper.IMG_EXT;
			imgs[i-1]=Helper.getImage(getClass().getResource(name));
		}
		imgFinal=imgs[0];
	}
	private void animateExplosion(Elemento e) {
		tablero.setExplosion(this);	
		Timer t = new Timer();
		TimerTask d = new TimerTask() {
			int cont =0;
        	public void run() {
        		cont++;
        		if(cont>3) {
        			tablero.quitarElemento(e);
        			cancel();        			
        		}else{
        		imgFinal=imgs[cont];
        		}
			}
        };
        
        t.schedule(d,0, Helper.TIME_EXP/3);
	}
	
	
}
