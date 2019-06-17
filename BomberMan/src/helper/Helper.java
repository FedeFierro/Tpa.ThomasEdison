package helper;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Helper {
	
	public final static String IMG_EXT = ".png";
	public final static String IMG_EXT_GIF = ".gif";
	public final static int TIME_EXP =700; 
	public final static int PX = 40;
	public final static int MOV_JUG =10;
	public final static String SOUND_EXT = ".wav";
	public final static int HEAD_Y=80;
	public final static String METHOD_JUGADOR = "player%d_%s";
	public final static String METHOD_BOMBA = "bomba_0%d";
	public final static String METHOD_EXPLOSION = "explosion_%s";
	public final static String METHOD_FONDO = "fondo_0%d";
	public final static String METHOD_MURO = "muro_0%d";
	public final static String METHOD_PARED = "pared_0%d";
	public final static String TEXT_NIVEL = "NIVEL: %d";
	public final static String TEXT_TIEMPO = "Tiempo: %S";
	public final static String TEXT_PUNTOS = "%d/%d";
	public final static String TEXT_PUNTOS_NIVEL = "%d";
	public final static String TEXT_END_GAME = "FIN DEL JUEGO";
	public final static String TEXT_WIN = "GANADOR: %s";
	public final static String TEXT_EMPATE = "HAY EMPATE";
	
	public final static int PUNTO_NULOS =0;
	public final static int PUNTO_PARED =5;
	public final static int PUNTO_JUGADOR =10;
	public final static int PUNTO_PARTIDA=1;
	
	public final static int DELAY =5000;
	
	
	
	public static Image getImage(URL u) {
		try {
			
			return ImageIO.read(u);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Clip getSonido (InputStream stream) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(stream);
//			AudioFormat format = audioInputStream.getFormat();
//			DataLine.Info info = new DataLine.Info(Clip.class, format);
//			Clip sound = (Clip)AudioSystem.getLine(info);
			Clip sonido = AudioSystem.getClip();
			sonido.open(audioInputStream);
			return sonido;
			
		} catch (Exception e) {
			return null;
		}
	}
	
}

