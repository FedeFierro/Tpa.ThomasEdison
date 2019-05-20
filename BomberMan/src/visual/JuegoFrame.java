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
	private Jugador j;
	public JuegoFrame() {
		
		
		super("BomberMan");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});
		Tablero t = new Tablero();
		j = new Jugador(1, 1, t);
		t.setJugador(j);
		contentPane = new JuegoPanel(t);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
			
	}
	
		public void setMovimiento(KeyEvent evento) {
			
			if(evento.getKeyCode() == KeyEvent.VK_UP) {
				j.moverse(0,-Helper.MOV_JUG);
				
			}
			if(evento.getKeyCode() == KeyEvent.VK_DOWN) {
				j.moverse(0,Helper.MOV_JUG);
						
			}
			if(evento.getKeyCode() == KeyEvent.VK_LEFT) {
				j.moverse(-Helper.MOV_JUG,0);
				
			}
			if(evento.getKeyCode() == KeyEvent.VK_RIGHT) {
				j.moverse(Helper.MOV_JUG,0);
				
			}
			if(evento.getKeyCode() == KeyEvent.VK_SPACE) {
				j.plantarBomba();
				
			}
			
		
		}

	public static void main(String[] args) throws IOException {
		new JuegoFrame().setVisible(true);
		}
}
