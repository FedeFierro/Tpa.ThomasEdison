package visual;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import entidades.*;
import helper.Helper;

public class JuegoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Tablero tablero;

	public JuegoPanel(Tablero tablero) {
		this.tablero = tablero;
		Timer b = new Timer();

		TimerTask c = new TimerTask() {
			public void run() {
				repaint();

			}
		};

		b.scheduleAtFixedRate(c, 0, 1000 / 144);
	}

	public void paintComponent(Graphics g) {

		for (int x = 0; x < tablero.getAncho(); x++) {
			for (int y = 0; y < tablero.getLargo(); y++) {

				Elemento e = tablero.getElemento(x, y);

				g.drawImage(e.show(), e.getPos().rx, e.getPos().ry, Helper.PX, Helper.PX, null);
				/* BOMBAS */
				e = tablero.getBomba(x, y);
				if (e != null) {
					g.drawImage(e.show(), e.getPos().rx, e.getPos().ry, Helper.PX, Helper.PX, null);

				}
				/* EXPLOSIONES */
				e = tablero.getExplosion(x, y);
				if (e != null) {
					g.drawImage(e.show(), e.getPos().rx, e.getPos().ry, Helper.PX, Helper.PX, null);

				}

			}
		}
		for (int x = 0; x < tablero.getAncho(); x++) {
			for (int y = 0; y < tablero.getLargo(); y++) {

				Elemento j = tablero.getJugador(x, y);
				if (j != null) {
					g.drawImage(j.show(), j.getPos().rx, j.getPos().ry, Helper.PX, Helper.PX, null);
				}
			}
		}
	}

}
