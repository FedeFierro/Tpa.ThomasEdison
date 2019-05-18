package visual;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import entidades.*;

public class JuegoPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Tablero tablero;
	private final int cubo =40;
	private final int jugAncho =30;
	
	public JuegoPanel(Tablero tablero) {
		this.tablero = tablero;
		Timer b = new Timer();
		 
        TimerTask c = new TimerTask() {
            public void run() {
                repaint();
               
                
            }
        };
 
        b.scheduleAtFixedRate(c, 0, 1000/144);
	}
	
	public void paintComponent(Graphics g) {
		
		for(int x= 0; x<15;x++) {
			for(int y= 0; y<15;y++) {
				Elemento e = tablero.getElemento(x,y);
				g.setColor(Color.BLACK);
				g.drawImage(e.show(), x*cubo, y*cubo,cubo,cubo,null);
				Elemento exp = tablero.getExplosion(x, y);
				if(exp!=null) {
					g.drawImage(exp.show(), x*cubo, y*cubo,cubo,cubo,null);
				}
				Elemento jug = tablero.getJugador(x,y);
				if(jug!=null) {
					g.drawImage(jug.show(), x*cubo,y*cubo,jugAncho,cubo,null);
				}
			}	
		}
	}
		

}
