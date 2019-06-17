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
import serializable.ElementoInfo;
import serializable.JugadorInfo;
import serializable.TableroInfo;

public class JuegoPanel extends JPanel {
	private Imagenes imgs;
	private static final long serialVersionUID = 1L;
	private Tablero tablero;
	private TimerTask repintar;
	public JuegoPanel(Tablero tablero) {
		this.tablero = tablero;
		imgs = new Imagenes();
		imgs.buildImagenes();
		Timer b = new Timer();
//		tablero.getSound().start();
		tablero.getSound().loop(tablero.getSound().LOOP_CONTINUOUSLY);
		repintar = new TimerTask() {
			public void run() {
				repaint();

			}
		};

		b.scheduleAtFixedRate(repintar, 0, 1000 / 144);
		
		tablero.iniciarJuego();
	}

	public void paintComponent(Graphics g) {
		setBackground(Color.BLACK);

		TableroInfo data = tablero.getSerializeInfo();
		
		if (data.finJuego) {
			Font f = new Font("Arial", Font.BOLD, 40);
			g.setColor(Color.WHITE);
			g.setFont(f);
			g.drawImage(imgs.getImage("otras_win"), 0, 50, 600,600, null);
			g.drawString(data.ganador, 100, 200);
			g.drawString(Helper.TEXT_END_GAME, 150, 100);
			repintar.cancel();
			
		}else if (data.pausa) {
			Font f = new Font("Arial", Font.BOLD, 40);
			g.setColor(Color.WHITE);
			g.setFont(f);
			g.drawString(data.ganador, 100, 200);
			
			g.drawString(String.format(Helper.TEXT_NIVEL, data.nivel), 200, 400);

		} else {
			Font f = new Font("Arial", Font.BOLD, 15);
			g.setColor(Color.WHITE);
			g.setFont(f);

			/* Este data hay que pedirselo al cliente */

			g.drawString(String.format(Helper.TEXT_NIVEL, data.nivel), 250, 30);
			g.drawString(String.format(Helper.TEXT_TIEMPO, data.tiempo), 250, 60);

			for (JugadorInfo info : data.jugadoresInfo) {
				g.drawImage(imgs.getImage(info.imagen), info.x, info.y, Helper.PX, Helper.PX, null);
				g.drawString(info.nombre, info.x + Helper.PX + 10, info.y + 20);
				g.drawString(String.format(Helper.TEXT_PUNTOS_NIVEL, info.puntosNivel), info.x + Helper.PX + 10,
						info.y + 40);
				g.drawString(String.format(Helper.TEXT_PUNTOS, info.puntoPartida, data.puntosPartida), info.x + 100,
						info.y + 40);

			}
			for (ElementoInfo e : data.elementos) {
				g.drawImage(imgs.getImage(e.imagen), e.x, e.y, e.width, e.height, null);

			}
		}
	}

}
