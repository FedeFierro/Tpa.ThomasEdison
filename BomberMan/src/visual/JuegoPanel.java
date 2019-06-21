package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import cliente.Client;
import helper.Helper;
import helper.Imagenes;
import helper.Sonidos;
import serializable.ElementoInfo;
import serializable.JugadorInfo;
import serializable.TableroInfo;

@SuppressWarnings("serial")
public class JuegoPanel extends JPanel {
	private Imagenes imgs;
	private Sonidos snds;
	private TimerTask repintar;
	private Clip sonidoJuego;
	private Clip sonidoStart;
	private Clip sonidoEnd;
	private TableroInfo data;
	private Client client;

	public JuegoPanel(String ip, int port, String usuario) {
		data = new TableroInfo(0);
		imgs = new Imagenes();
		snds = new Sonidos();
		client = new Client(ip, port, data, usuario);
		Timer b = new Timer();
		repintar = new TimerTask() {
			public void run() {
				repaint();
			}
		};

		b.scheduleAtFixedRate(repintar, 0, 1000 / 144);
	}

	public void paintComponent(Graphics g) {
		setBackground(Color.BLACK);
		Font font;
		g.setColor(Color.WHITE);
		
		if(!data.iniciado) {
			font = new Font("Arial",Font.BOLD,20);
			g.setFont(font);
			g.drawString(data.mensaje, 0, 200);
			
		}else if (data.finJuego) {
			font= new Font("Arial", Font.BOLD, 40);
			g.setFont(font);
					 
			if (data.sonido != null && !data.sonido.isEmpty()) {
				snds.close(sonidoStart);
				snds.close(sonidoJuego);
				if (!snds.isRunning(sonidoEnd)) {
					sonidoEnd = snds.getSonido(data.sonido);
					snds.start(sonidoEnd, false);
				}
			}
			g.drawImage(imgs.getImage("otras_win"), 0, 50, 600, 600, null);
			g.drawString(data.ganador, 100, 200);
			g.drawString(Helper.TEXT_END_GAME, 150, 100);
			repintar.cancel();

		} else if (data.pausa) {
			font= new Font("Arial", Font.BOLD, 40);
			g.setFont(font);
			
			if (data.sonido != null && !data.sonido.isEmpty()) {
				snds.close(sonidoJuego);
				if (!snds.isRunning(sonidoStart)) {
					sonidoStart = snds.getSonido(data.sonido);
					snds.start(sonidoStart, false);
				}
			}
			g.drawString(data.ganador, 100, 200);

			g.drawString(String.format(Helper.TEXT_NIVEL, data.nivel), 200, 400);

		} else {
			font= new Font("Arial", Font.BOLD, 15);
			g.setFont(font);
			
			if (data.sonido != null && !data.sonido.isEmpty()) {
				snds.close(sonidoStart);
				if (!snds.isRunning(sonidoJuego)) {
					sonidoJuego = snds.getSonido(data.sonido);
					snds.start(sonidoJuego, true);
				}

			}
			
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
				if (e.sonido != null && !e.sonido.isEmpty()) {
					Clip sndElemento = snds.getSonido(e.sonido);
					if (sndElemento != null) {
						sndElemento.start();

					}
				}
				g.drawImage(imgs.getImage(e.imagen), e.x, e.y, e.width, e.height, null);

			}
		}
	}

	public void getCodTecla(int codTecla) {
		client.sendMessage(codTecla);
	}
	public void close() {
		client.close();
	}

}
