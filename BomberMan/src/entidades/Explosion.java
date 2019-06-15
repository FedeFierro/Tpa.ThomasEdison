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
		setImageName(0);
		loadSound();
		explotar();
	}
	@Override
	public void explotar() {
		if(elemento == null || elemento.esTransitable()) {
			tablero.setExplosion(this);
		}else {
			elemento.explotar();
			sonido.start();
		}
		desaparecer();
		
	}
	protected void loadSound() {
		String name="/explosion/Explosion"+Helper.SOUND_EXT;
		sonido = Helper.getSonido(getClass().getResourceAsStream(name));
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
	@Override
	protected void setImageName(Integer numero) {
		imgFinal = "explosion_"+tipo.toString();
	}
	
	

}
