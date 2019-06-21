package visual;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

import entidades.*;
import helper.Helper;

public class JuegoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JuegoPanel  contentPane;
	public JuegoFrame() {
		
		
		super("BomberMan");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});
		contentPane = new JuegoPanel();
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
	}
	
		public void setMovimiento(KeyEvent evento) {
		/* pasarlo al cliente y que el cliente se lo envie al server y este al jugador que le corresponda*/	
			contentPane.getCodTecla(evento.getKeyCode());
			
		}

	public static void main(String[] args) throws IOException {
		new JuegoFrame().setVisible(true);
		}
	
}
