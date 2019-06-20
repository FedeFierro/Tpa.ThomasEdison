package servidor;

import java.io.DataInputStream;
import java.net.Socket;
import entidades.Jugador;

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
	

}
