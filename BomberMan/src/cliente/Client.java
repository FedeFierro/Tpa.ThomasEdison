package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import serializable.TableroInfo;

public class Client{
	private Socket client;
//	private DataInputStream input;
	private DataOutputStream output;
	private TableroInfo tableroInfo;
	
	public Client(String ip, int puerto, TableroInfo tableroInfo) {
		try {
			this.tableroInfo =  tableroInfo;
			
			client = new Socket(ip, puerto);
			output = new DataOutputStream(client.getOutputStream());
			
			Thread thread = new Thread(new Runnable() {
				public void run() {
					getMessage();
				}
			});
			thread.start();
			
		} catch (IOException e) {
			System.out.println("Error del cliente..." + e.getMessage());
		}
	}
	
	public void sendMessage (int codTecla) {
		
		try {
			output = new DataOutputStream(client.getOutputStream());
			output.write(codTecla);
//			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error del cliente enviando mensaje..." + e.getMessage());
		}
	}
	
	public void getMessage() {
		while(true) {
			try {
				DataInputStream input = new DataInputStream(client.getInputStream());
				String strInput = input.readUTF();
				Gson gson = new Gson();
				TableroInfo tb = gson.fromJson(strInput, tableroInfo.getClass());
				tableroInfo.copyData(tb);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
