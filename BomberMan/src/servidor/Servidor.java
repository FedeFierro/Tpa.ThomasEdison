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

import database.DataBase;
import database.SalaDB;
import entidades.Jugador;
import entidades.Tablero;
import serializable.TableroInfo;

public class Servidor {
	private Tablero tablero;
	private ServerSocket serverSocket;
	private ServerSocket espectadorSocket;
	private Thread buscarConexion;
	private Thread buscarEspectadores;
	private ArrayList<ConexionCliente> listaClientes;
	private ArrayList<ConexionCliente> listaEspectadores;
	private Timer timer;
	private TimerTask sendData;
	private ObservableData data;
	private DataBase db;
	private SalaDB sala;

	public Servidor(SalaDB sala,ObservableData data) {
		this.sala = sala;
		db = new DataBase();
		try {
			
			InetAddress ipDireccion = InetAddress.getLocalHost();
			this.data = data;
			serverSocket = new ServerSocket(sala.getPuerto());
			data.setData("Servidor iniciado IP: "+ ipDireccion.getHostAddress().toString() + " puerto: "+sala.getPuerto());
			sala.setIP(ipDireccion.getHostAddress().toString());
			sala.setEstado(1);
			db.guardarSala(sala);
			

			data.setData("Servidor iniciado IP: " + ipDireccion.getHostAddress().toString() + " puerto: " + sala.getPuerto());

			tablero = new Tablero(sala.getTiempo(), sala.getPuntos());
			listaClientes = new ArrayList<ConexionCliente>();
			listaEspectadores = new ArrayList<ConexionCliente>();
			buscarConexion = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						int cantJugador = sala.getCantJugadores();
						for (int i = 1; i <= cantJugador; i++) {
							Socket sc = serverSocket.accept();
							DataInputStream input = new DataInputStream(sc.getInputStream());
							String usuario = input.readUTF();
							Jugador j = new Jugador(tablero, usuario, i);
							ConexionCliente cliente = new ConexionCliente(sc, j);
							data.setData("Cliente " + i + " " + usuario + " conectado");
							listaClientes.add(cliente);
							data.setData("Clientes conectados: " + listaClientes.size() + "/" + sala.getCantJugadores());
							tablero.setJugador(j);
							cliente.start();

						}
						iniciarPartida();
					} catch (Exception e) {
						data.setData(e.getMessage());
						System.out.println("ERROR");
					}

				}

			});
			buscarEspectadores= new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						espectadorSocket= new ServerSocket(sala.getPuertoEspectador());
						
					for(int e =0; e<100; e++) {
						Socket sc = espectadorSocket.accept();
						listaEspectadores.add(new ConexionCliente(sc));
						
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			buscarConexion.start();
			if(sala.getPuertoEspectador()>0) {
				buscarEspectadores.start();
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void serverClose() {
		sala.setEstado(3);
		db.guardarSala(sala);
		db.borrarSala(sala);
		data.setData("cerrando Servidor...");
		try {
			serverSocket.close();
			if(espectadorSocket!=null) {
				espectadorSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciarPartida() {
		if (!tablero.info.iniciado) {
			data.setData("iniciando Partida....");
			sala.setEstado(2);
			db.guardarSala(sala);
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
					ArrayList<ConexionCliente> espDesconectados = new ArrayList<ConexionCliente>();
					for(ConexionCliente ec: listaEspectadores) {
						if(!ec.sendData(datos)) {
							espDesconectados.add(ec);
						}
					}
					elimiarDesconectados(desconectados);
					elimiarEspectador(espDesconectados);

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
	private void elimiarEspectador(ArrayList<ConexionCliente> desconectados) {
		for (ConexionCliente desc : desconectados) {
			listaEspectadores.remove(desc);
		}

	}

}
