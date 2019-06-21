package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import serializable.TableroInfo;

public class Client {
	private Socket client;
	private TableroInfo tableroInfo;
	private Thread hilo;
	private String usuario;
	private DataInputStream input;
	private DataOutputStream output;

	public Client(String ip, int puerto, TableroInfo tableroInfo, String usuario) {
		try {
			this.usuario = usuario;
			this.tableroInfo = tableroInfo;
			client = new Socket(ip, puerto);
			output = new DataOutputStream(client.getOutputStream());
			output.writeUTF(this.usuario);
			hilo = new Thread(new Runnable() {
				public void run() {
					getMessage();
				}
			});
			hilo.start();

		} catch (IOException e) {
			System.out.println("Error del cliente..." + e.getMessage());
		}
	}

	public void sendMessage(int codTecla) {

		try {
			if(!client.isClosed()&& client.isConnected()) {
			output = new DataOutputStream(client.getOutputStream());
			output.write(codTecla);
			}else {
				conexionPerdia();
			}

		} catch (IOException e) {
			System.out.println("Error del cliente enviando mensaje..." + e.getMessage());
		}
	}

	public void getMessage() {
		try {
			while (true && !client.isClosed() && client.isConnected()) {

				input = new DataInputStream(client.getInputStream());
				String strInput = input.readUTF();
				Gson gson = new Gson();
				TableroInfo tb = gson.fromJson(strInput, tableroInfo.getClass());
				tableroInfo.copyData(tb);

			}
			} catch (Exception e) {
			System.out.println("Error del cliente enviando mensaje..." + e.getMessage());
		}
		finally {
			conexionPerdia();
		}

	}
	public void close() {
		try {
			client.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void conexionPerdia() {
		tableroInfo.iniciado = false;
		tableroInfo.mensaje = "Se perdio la conexión con el servidor";
	
	}
}
