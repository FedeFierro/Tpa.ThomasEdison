package entidades;

import java.util.Timer;
import java.util.TimerTask;

import helper.Helper;

public class Pared extends Elemento {
	private boolean powerUp;

	public Pared(int x, int y, Tablero tablero) {
		super(x, y, tablero);
	}

	public Pared(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
		setImageName(1);
	}

	@Override
	public int explotar() {
		if(vivo) {
			super.vivo = false;
			animateExplosion(this);
//			tablero.quitarElemento(this);
			return Helper.PUNTO_PARED;
		}
		return Helper.PUNTO_NULOS;
	}
	@Override
	public boolean puedeSeguirExplotando() {
		return false;
	}
	
	private void animateExplosion(Elemento e) {
		tablero.setExplosion(this);	
		tablero.quitarElemento(e);
		if(powerUp) {
			tablero.agregarPowerUp(new PowerUp(this.pos, this.tablero));
		}
		Timer t = new Timer();
		TimerTask d = new TimerTask() {
			int cont =1;
        	public void run() {
        		cont++;
        		if(cont > 4) {
        			tablero.quitarExplosion(e);
        			cancel();        			
        		}else{
        		setImageName(cont);
        		}
			}
        };
        
        t.schedule(d,0, Helper.TIME_EXP/3);
	}

	@Override
	protected void setImageName(Integer numero) {
		imgFinal= String.format(Helper.METHOD_PARED, numero);
		
	}
	public void setPowerUp() {
		this.powerUp=true;
	}
	
	
}
