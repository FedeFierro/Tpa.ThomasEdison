package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import serializable.TableroInfo;

public class Client{
	private Socket client;
	private DataInputStream input;
	private DataOutputStream output;
	
	public Client(String ip, int puerto) {
		try {
			client = new Socket(ip, puerto);
			input = new DataInputStream(client.getInputStream());
			output = new DataOutputStream(client.getOutputStream());
			Thread thread = new Thread(new Runnable() {
				public void run() {
					getMessage();
					sendMessage();
				}
			});
			thread.start();
		} catch (IOException e) {
			System.out.println("Error del cliente..." + e.getMessage());
		}
	}
	
	public void sendMessage () {
		
		try {
			output = new DataOutputStream(client.getOutputStream());
//			output.writeUTF(text);
//			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error del cliente enviando mensaje..." + e.getMessage());
		}
	}
	
	public TableroInfo getMessage() {
//		String text =  input.readUTF();
		return null;

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
