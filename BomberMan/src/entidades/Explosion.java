package entidades;

import java.util.Timer;
import java.util.TimerTask;

import helper.DireccionEnum;
import helper.Helper;

public class Explosion extends Elemento {
	protected DireccionEnum tipo;
	protected Elemento elemento;
	protected Jugador jugador;

	public Explosion(Coordenada pos, Tablero tablero, DireccionEnum tipo, Elemento e,Jugador j) {
		super(pos, tablero);
		this.tipo = tipo;
		this.elemento = e;
		this.jugador=j;
		setImageName(0);
		loadSound();
		explotar();
	}

	@Override
	public int explotar() {
		int puntos =0;
		if (elemento == null || elemento.esTransitable()) {
			tablero.setExplosion(this);
		} else if (elemento instanceof Jugador) {
			tablero.setExplosion(this);
			puntos+=elemento.explotar();	
			

		} else

		{
			puntos +=elemento.explotar();
			sonido.start();
		}
		jugador.sumarPuntos(puntos);
		desaparecer();
		return Helper.PUNTO_NULOS;

	}

	protected void loadSound() {
		String name = "/explosion/Explosion" + Helper.SOUND_EXT;
		sonido = Helper.getSonido(getClass().getResourceAsStream(name));
	}

	protected void desaparecer() {
		Explosion e = this;
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
		imgFinal = String.format(Helper.METHOD_EXPLOSION, tipo.toString());
	}

}
