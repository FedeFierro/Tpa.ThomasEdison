package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

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
	private ObservableData data;

	public Servidor(int port, int tiempo, int puntosPartida, int cantJugadores, String nombre, ObservableData data) {
		try {
			InetAddress ipDireccion = InetAddress.getLocalHost();
			this.data = data;
			serverSocket = new ServerSocket(port);
			data.setData("Servidor iniciado IP: " + ipDireccion.getHostAddress().toString() + " puerto: " + port);
			tablero = new Tablero(tiempo, puntosPartida);
			/* guardar en la base de datos */
			listaClientes = new ArrayList<ConexionCliente>();
			buscarConexion = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						int cantJugador = cantJugadores;
						for (int i = 1; i <= cantJugador; i++) {
							Socket sc = serverSocket.accept();
							DataInputStream input = new DataInputStream(sc.getInputStream());
							String usuario = input.readUTF();
							Jugador j = new Jugador(tablero, usuario, i);
							ConexionCliente cliente = new ConexionCliente(sc, j);
							data.setData("Cliente " + i + " " + usuario + " conectado");
							listaClientes.add(cliente);
							data.setData("Clientes conectados: " + listaClientes.size() + "/" + cantJugadores);
							tablero.setJugador(j);
							cliente.start();

						}
						iniciarPartida();
					} catch (Exception e) {
						data.setData(e.getMessage());
					}

				}

			});
			buscarConexion.start();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void serverClose() {
		data.setData("cerrando Servidor...");
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciarPartida() {
		if (!tablero.info.iniciado) {
			data.setData("iniciando Partida....");
			buscarConexion.interrupt();
			timer = new Timer();
			sendData = new TimerTask() {
				@Override
				public void run() {
					TableroInfo ti = tablero.getSerializeInfo();
					Gson gson = new Gson();
					String datos = gson.toJson(ti);
					ArrayList<ConexionCliente> desconectados = new ArrayList<ConexionCliente>();
					for (ConexionCliente cc : listaClientes) {
						if (!cc.sendData(datos)) {
							desconectados.add(cc);
						}
					}
					elimiarDesconectados(desconectados);

				}
			};
			tablero.iniciarJuego();

			timer.schedule(sendData, 0, 1000 / 100);
		}
	}

	private void elimiarDesconectados(ArrayList<ConexionCliente> desconectados) {
		for (ConexionCliente desc : desconectados) {
			data.setData( desc.usuario+" desconectado");
			desc.removerJugador();
			listaClientes.remove(desc);
		}

	}

}
