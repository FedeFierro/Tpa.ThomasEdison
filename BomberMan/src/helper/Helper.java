package helper;

import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;

public class Helper {
	
	public final static String IMG_EXT = ".png";
	public final static int TIME_EXP =700; 
	public static Image getImage(URL u) {
		try {
			
			return ImageIO.read(u);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
