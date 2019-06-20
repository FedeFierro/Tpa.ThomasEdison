package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Servidor.Conexion;
import entidades.Tablero;
import entidades.Jugador;

public class Servidor {
	private Tablero tablero;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public static void main(String[] args) throws IOException{
		Servidor server = new Servidor();
		server.iniciarConexion();
		server.tablero=new Tablero();
		
	}
	
	public void iniciarConexion() throws IOException {
		int puerto=5000;
		ServerSocket servidor = null; 
        Socket nuevousuario = null;
        try {
        	
        	servidor = new ServerSocket(puerto);
	            
            while (true) {
            	System.out.println("esperando que se conecten");
                nuevousuario = servidor.accept();
                Jugador jugador=new Jugador(this.tablero);
                Conexion cc = new Conexion(nuevousuario);
				cc.start();
            }
        } catch (IOException ex) {
        	System.out.println("Error: " + ex.getMessage());
        } finally {
        	servidor.close();
		}
	}
	
}
	
