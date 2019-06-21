package helper;

import javax.sound.sampled.Clip;

public class Sonidos {
	
	public static final String END_SND = "ending";
	public static final String START_SND = "start";
	public static final String GAME_SND = "theme";
	public static final String BOMB_SND = "bomba";
	public static final String EXP_SND = "explosion";
	public static final String LIFE_SND = "lifeLost";

	public Clip getSonido(String name) {
		String ruta = "/sounds/" + name;
		ruta = ruta + Helper.SOUND_EXT;
		return Helper.getSonido(this.getClass().getResourceAsStream(ruta));
	}
	public void start(Clip sonido, boolean loop) {
		if(sonido!=null) {
			if(loop) {
				sonido.loop(Clip.LOOP_CONTINUOUSLY);
				return;
			}
			sonido.start();
		}
	}
		
	public void close(Clip sonido) {
		if( sonido!=null) {
			sonido.close();
			return;
		}
		
	}
	public boolean isRunning(Clip sonido) {
		if(sonido==null) {
			return false;
		}
		return sonido.isRunning();

	}

	
}
