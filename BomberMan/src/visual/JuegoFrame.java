package visual;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import entidades.*;

public class JuegoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JuegoPanel  contentPane;
	
	public JuegoFrame() {
		
		super("BomberMan");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		Tablero t = new Tablero();
		contentPane = new JuegoPanel(t);
		setBackground(Color.WHITE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
			
	}
	

	public static void main(String[] args) throws IOException {
		new JuegoFrame().setVisible(true);
		}
}
