package entidades;

import java.util.Timer;
import java.util.TimerTask;

import helper.Helper;

public class Pared extends Elemento {

	public Pared(int x, int y, Tablero tablero) {
		super(x, y, tablero);
	}

	public Pared(Coordenada pos, Tablero tablero) {
		super(pos, tablero);
		setImageName(1);
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
	
	private void animateExplosion(Elemento e) {
		tablero.setExplosion(this);	
		tablero.quitarElemento(e);
		Timer t = new Timer();
		TimerTask d = new TimerTask() {
			int cont =1;
        	public void run() {
        		cont++;
        		if(cont > 4) {
        			
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
	
	
}
