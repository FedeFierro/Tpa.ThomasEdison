package helper;

import java.awt.Image;
import java.lang.reflect.Field;

public class Imagenes {
	public Image bomba_01;
	public Image bomba_02;
	public Image bomba_03;

	public Image explosion_E;
	public Image explosion_N;
	public Image explosion_O;
	public Image explosion_S;
	public Image explosion_V;
	public Image explosion_H;
	public Image explosion_C;

	public Image fondo_01;
	public Image muro_01;

	public Image pared_01;
	public Image pared_02;
	public Image pared_03;
	public Image pared_04;

	public Image player1_01;
	public Image player1_02;
	public Image player1_03;
	public Image player1_04;
	public Image player1_05;
	public Image player1_11;
	public Image player1_12;
	public Image player1_13;
	public Image player1_14;
	public Image player1_15;
	public Image player1_21;
	public Image player1_22;
	public Image player1_23;
	public Image player1_24;
	public Image player1_25;
	public Image player1_31;
	public Image player1_32;
	public Image player1_33;
	public Image player1_34;
	public Image player1_35;
	public Image player1_41;
	public Image player1_42;
	public Image player1_43;
	public Image player1_44;
	public Image player1_45;

	public Image player2_01;
	public Image player2_02;
	public Image player2_03;
	public Image player2_04;
	public Image player2_05;
	public Image player2_11;
	public Image player2_12;
	public Image player2_13;
	public Image player2_14;
	public Image player2_15;
	public Image player2_21;
	public Image player2_22;
	public Image player2_23;
	public Image player2_24;
	public Image player2_25;
	public Image player2_31;
	public Image player2_32;
	public Image player2_33;
	public Image player2_34;
	public Image player2_35;
	public Image player2_41;
	public Image player2_42;
	public Image player2_43;
	public Image player2_44;
	public Image player2_45;

	public Image player3_01;
	public Image player3_02;
	public Image player3_03;
	public Image player3_04;
	public Image player3_05;
	public Image player3_11;
	public Image player3_12;
	public Image player3_13;
	public Image player3_14;
	public Image player3_15;
	public Image player3_21;
	public Image player3_22;
	public Image player3_23;
	public Image player3_24;
	public Image player3_25;
	public Image player3_31;
	public Image player3_32;
	public Image player3_33;
	public Image player3_34;
	public Image player3_35;
	public Image player3_41;
	public Image player3_42;
	public Image player3_43;
	public Image player3_44;
	public Image player3_45;

	public Image player4_01;
	public Image player4_02;
	public Image player4_03;
	public Image player4_04;
	public Image player4_05;
	public Image player4_11;
	public Image player4_12;
	public Image player4_13;
	public Image player4_14;
	public Image player4_15;
	public Image player4_21;
	public Image player4_22;
	public Image player4_23;
	public Image player4_24;
	public Image player4_25;
	public Image player4_31;
	public Image player4_32;
	public Image player4_33;
	public Image player4_34;
	public Image player4_35;
	public Image player4_41;
	public Image player4_42;
	public Image player4_43;
	public Image player4_44;
	public Image player4_45;
	public Image otras_win;
	
	public void buildImagenes() {
		/**
		 * Utiliza reflection, cicla por todas las propiedaes de este objeto parsea su
		 * nombre para generar la url de la imagen y carga la Imagen y la propiedad
		 * correspondinte;
		 **/
		Field[] properties = this.getClass().getFields();
		try {
			for (Field prop : properties) {
				String ruta = "/" + prop.getName().replace('_', '/');
				if (prop.getName().startsWith("otras")) {
					ruta = ruta + Helper.IMG_EXT_GIF;
				} else {
					ruta = ruta + Helper.IMG_EXT;
				}
				prop.set(this, Helper.getImage(this.getClass().getResource(ruta)));

			}

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image getImage(String name) {
		Field prop;
		try {
			prop = this.getClass().getField(name);
			return (Image) prop.get(this);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}