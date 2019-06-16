package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import entidades.*;
import helper.Helper;
import helper.Imagenes;
import serializable.ElementSerializable;
import serializable.JugadorInfo;
import serializable.Serialize;

public class JuegoPanel extends JPanel {
	private Imagenes imgs;
	private static final long serialVersionUID = 1L;
	private Tablero tablero;

	public JuegoPanel(Tablero tablero) {
		this.tablero = tablero;
		imgs = new Imagenes();
		imgs.buildImagenes();
		Timer b = new Timer();
//		tablero.getSound().start();
		tablero.getSound().loop(tablero.getSound().LOOP_CONTINUOUSLY);
		TimerTask c = new TimerTask() {
			public void run() {
				repaint();

			}
		};

		b.scheduleAtFixedRate(c, 0, 1000 / 144);
	}

	public void paintComponent(Graphics g) {
  		setBackground(Color.BLACK);
		Font f = new Font("Arial",Font.BOLD , 15);
		g.setColor(Color.WHITE);
		g.setFont(f);
		
		/*Este data hay que pedirselo al cliente*/
		Serialize data = tablero.getSerialize();
		g.drawString(String.format(Helper.TEXT_NIVEL, data.nivel), 250 , 30);
		g.drawString(String.format(Helper.TEXT_TIEMPO, data.tiempo), 250 , 60);
		
		for (JugadorInfo info : data.jugadoresInfo) {
			g.drawImage(imgs.getImage(info.imagen),info.x, info.y, Helper.PX, Helper.PX ,null);
			g.drawString(info.nombre, info.x+Helper.PX+10, info.y+20);
			g.drawString(String.format(Helper.TEXT_PUNTOS_NIVEL,info.puntosNivel) , info.x+Helper.PX+10, info.y+40);
			g.drawString(String.format(Helper.TEXT_PUNTOS,info.puntoPartida,data.nivel), info.x+100, info.y+40);
			
			
		}
		for(ElementSerializable e : data.elementos) {
			g.drawImage(imgs.getImage(e.img), e.x, e.y, e.width,e.height, null);
			
		}
	}

}
