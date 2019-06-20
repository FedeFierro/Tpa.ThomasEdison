package servidor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import entidades.Jugador;
import entidades.Tablero;
import serializable.TableroInfo;

public class Servidor {
	private Tablero tablero;
	private ServerSocket serverSocket;
	private Thread buscarConexion;
	private ArrayList<ConexionCliente> listaClientes;
	private Timer timer;
	private TimerTask sendData;

	public Servidor(int port, int tiempo, int puntosPartida, int cantJugadores, String nombre) {
		try {
			InetAddress ipDireccion = InetAddress.getLocalHost();
			serverSocket = new ServerSocket(port);
			tablero = new Tablero(tiempo, puntosPartida);
			/* guardar en la base de datos */
			listaClientes = new ArrayList<ConexionCliente>();
			buscarConexion = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						int cantJugador = cantJugadores;
						for (int i = 0; i < cantJugador; i++) {
							Jugador j = new Jugador(tablero,"Oscar");
							Jugador j2 = new Jugador(tablero, "Jonny");
							ConexionCliente cliente = new ConexionCliente(serverSocket.accept(), j);
							listaClientes.add(cliente);
							tablero.setJugador(j);
							tablero.setJugador(j2);
							cliente.start();
							
						}
						iniciarPartida();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					
				}

			});
			buscarConexion.start();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void serverClose() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciarPartida() {
		buscarConexion.interrupt();
		timer = new Timer();
		sendData = new TimerTask() {
			
			@Override
			public void run() {
				for(ConexionCliente cc : listaClientes) {
					TableroInfo ti =tablero.getSerializeInfo();
					cc.sendData(ti);
				}
				
			}
		};
		tablero.iniciarJuego();
		timer.schedule(sendData, 0, 1000/60);
	}

}
