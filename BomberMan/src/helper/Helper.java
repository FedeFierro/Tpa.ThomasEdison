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
	public final static int TIME_EXP =700; 
	public final static int PX = 40;
	public final static int MOV_JUG =10;
	public final static String SOUND_EXT = ".wav";
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

