package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import entidades.Jugador;
import serializable.TableroInfo;

public class ConexionCliente extends Thread{
	private Socket socketCliente;
	private Jugador jugador;
	

	public ConexionCliente(Socket cliente, Jugador jugador) {
		this.jugador = jugador;
		socketCliente = cliente;
		
	}
	
	@Override
	public void run() {
		try {
		DataInputStream di= new DataInputStream(socketCliente.getInputStream());
		int keyCode = di.read();
		jugador.setMovimeiento(keyCode);

		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void sendData(TableroInfo ti) {
		try {
			DataOutputStream salida = new DataOutputStream(socketCliente.getOutputStream());
			Gson gson = new Gson();
			salida.writeUTF(gson.toJson(ti));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
