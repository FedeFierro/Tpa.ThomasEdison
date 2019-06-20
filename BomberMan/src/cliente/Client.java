package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private Socket client;
	private DataInputStream input;
	private DataOutputStream output;
	
	public Client(String ip, int puerto) {
		try {
			client = new Socket(ip, puerto);
		} catch (IOException e) {
			System.out.println("Error del cliente..." + e.getMessage());
		}
	}
	
	public void sendMessage (String text) {
		
		try {
			output = new DataOutputStream(client.getOutputStream());
			output.writeUTF(text);
//			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error del cliente enviando mensaje..." + e.getMessage());
		}
	}
	
	public String getMessage() {
		try {
			input = new DataInputStream(client.getInputStream());
			String text =  input.readUTF();
//			input.close();
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	public void closeClient() {
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error cerrando el cliente... " + e.getMessage());
		}
	}
}
