package visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class ServerFrame  extends JFrame{
	JTextArea txtArea;
	JButton btnStart;
	JButton btnStop;
	JButton btnIniciarPartida;
	JPanel contentPane;
	ConsolePane consolePane;

	public ServerFrame() {	
	setTitle("Chat Server");
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 600, 500);
	JPanel pane1 = new JPanel();
	contentPane = new JPanel();
	contentPane.setSize(600, 500);
	contentPane.setLayout(new BorderLayout());
	btnStart = new JButton("Iniciar Server");
	btnStart.setBounds(50, 0, 150, 50);
	btnStart.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        startServer();
	    }

	});
	pane1.add(btnStart);
	btnStop = new JButton("Finalizar Server");
	btnStop.setBounds(250, 0, 150, 50);
	btnStop.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        stopServer();
	    }

	});	
	btnStop.setEnabled(false);
	pane1.add(btnStop);
	btnIniciarPartida = new JButton("Iniciar Partida");
	btnIniciarPartida.setBounds(450, 0, 150, 50);
	btnIniciarPartida.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        iniciarPartida();
	    }

	});	
	btnIniciarPartida.setEnabled(false);
	pane1.add(btnIniciarPartida);
	add(pane1, BorderLayout.BEFORE_FIRST_LINE);
	consolePane  = new ConsolePane();
	add(consolePane);
	
	addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent arg0) {
			stopServer();
			close();
		}
	});
	
	setLocationRelativeTo(null);
	}
	
	private void close() {
		System.exit(0);
	}
	private void startServer() {
		consolePane.startServer();
		btnStart.setEnabled(false);
		btnStop.setEnabled(true);
		btnIniciarPartida.setEnabled(true);
	}
	private void stopServer() {
		consolePane.stopServer();
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		btnIniciarPartida.setEnabled(false);
	}
	private void iniciarPartida() {
		btnIniciarPartida.setEnabled(false);
		consolePane.iniciarPartida();
	}
}
