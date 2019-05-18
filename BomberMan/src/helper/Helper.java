package helper;

import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;


public class Helper {
	
	public final static String IMG_EXT = ".png";
	public final static String PATH_IMG ="../recursos/";
	
	public static Image getImage(ClassLoader cl, String name) {
		try {
			URL path = cl.getResource(PATH_IMG +name+IMG_EXT);
			return ImageIO.read(path);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}

