package visual;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class JuegoFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;
	private JuegoPanel  contentPane;
	public JuegoFrame(String ip, int port, String usuario, boolean espectador) {
		super("BomberMan");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		if(!espectador) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});
		}
		contentPane = new JuegoPanel(ip,port,usuario);

		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
			}
		});
	}
	
		public void setMovimiento(KeyEvent evento) {
		/* pasarlo al cliente y que el cliente se lo envie al server y este al jugador que le corresponda*/	
			contentPane.getCodTecla(evento.getKeyCode());
			
		}
		public void close() {
			contentPane.close();
			System.exit(0);
		}
	
}
