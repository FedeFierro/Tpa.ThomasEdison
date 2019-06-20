package servidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;

import entidades.Jugador;
import entidades.Tablero;

public class Servidor {
	private Tablero tablero;
	private ServerSocket serverSocket;
	private Thread buscarConexion;
	private ArrayList<ConexionCliente> listaClientes;

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
							Jugador j = new Jugador(tablero,nombre);
							ConexionCliente cliente = new ConexionCliente(serverSocket.accept(), j);
							listaClientes.add(cliente);
							tablero.setJugador(j);
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
		tablero.iniciarJuego();
	}
}
