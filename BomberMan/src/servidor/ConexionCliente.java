package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import entidades.Jugador;

public class ConexionCliente extends Thread {
	private Socket socketCliente;
	private Jugador jugador;
	public String usuario;
	DataInputStream input;
	DataOutputStream output;

	public ConexionCliente(Socket cliente, Jugador jugador) {
		this.usuario=jugador.info.nombre;
		this.jugador = jugador;
		socketCliente = cliente;

	}

	@Override
	public void run() {
		try {
			while (true && !socketCliente.isClosed() && socketCliente.isConnected()) {
				input = new DataInputStream(socketCliente.getInputStream());
				int keyCode = input.read();
				System.out.println(keyCode);
				jugador.setMovimeiento(keyCode);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public boolean sendData(String datos) {
		try {
			if (!socketCliente.isClosed() && socketCliente.isConnected()) {
			output = new DataOutputStream(socketCliente.getOutputStream());
			output.writeUTF(datos);
			output.flush();
			return true;
		}
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public void removerJugador() {
		this.jugador.desconectar();
	}

}
