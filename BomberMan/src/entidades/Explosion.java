package entidades;

import java.util.Timer;
import java.util.TimerTask;

import helper.DireccionEnum;
import helper.Helper;

public class Explosion extends Elemento {
	protected DireccionEnum tipo;
	protected Elemento elemento;
	
	public Explosion(Coordenada pos, Tablero tablero, DireccionEnum tipo,Elemento e) {
		super(pos, tablero);
		this.tipo=tipo;
		this.elemento= e;
		loadImages();
		explotar();
	}
	@Override
	public void explotar() {
		if(elemento == null || elemento.esTransitable()) {
			tablero.setExplosion(this);
		}else {
			elemento.explotar();
		}
		desaparecer();
		
	}

	@Override
	protected void loadImages() {
		String name="/explosion/"+tipo.toString()+Helper.IMG_EXT;
		imgFinal= Helper.getImage(getClass().getResource(name));
	}
	
	protected void desaparecer() {
		Explosion e=this;
		Timer t = new Timer();
		
		TimerTask c = new TimerTask() {    
			public void run() {
            	
                tablero.quitarExplosion(e);
            }
        };
        t.schedule(c, Helper.TIME_EXP);
  	}
	public boolean esTransitable() {
		return true;
	}
	
	

}
