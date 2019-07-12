package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import servidor.ObservableData;
import servidor.Servidor;;

@SuppressWarnings("serial")
public class ConsolePane extends JPanel implements PropertyChangeListener{
	JTextArea txtArea;
	Servidor serverClient;
	ObservableData data;
	String nombre;
	int port;
	int tiempo;
	int cantJugadores;
	int puntos;
	int puertoEspectador;
	
	JScrollPane jScroll;
	public ConsolePane(String nombre, int port, int tiempo, int cantJugadores,int puntos, int pEspectador) {
		this.nombre = nombre;
		this.port = port;
		this.tiempo=tiempo;
		this.cantJugadores=cantJugadores;
		this.puntos= puntos;
		this.puertoEspectador= pEspectador;
		setSize(400, 400);
		setLayout(new BorderLayout());
	
		txtArea = new JTextArea(10,10);
		txtArea.setBackground(Color.BLACK);
		txtArea.setForeground(Color.WHITE);
		txtArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		txtArea.setBounds(5,100,600,400);
		txtArea.setVisible(true);
		txtArea.setLineWrap (true);
		jScroll= new JScrollPane();
		jScroll.add(txtArea);
		jScroll.setBounds(5, 100, 590, 390);
		add(txtArea);
		data = new ObservableData();
		data.addPropertyChangeListener(this);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		txtArea.append(evt.getNewValue().toString()+ "\n");
	}
	public void startServer() {
		txtArea.setText("");
		serverClient = new Servidor(port,tiempo,puntos,cantJugadores,nombre, data, puertoEspectador);
	}
	public void stopServer() {
		serverClient.serverClose();
	}
	public void iniciarPartida() {
		serverClient.iniciarPartida();
	}

}
