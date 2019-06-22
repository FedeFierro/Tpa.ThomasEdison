package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import cliente.Client;
import database.Usuario;

public class Menu extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	protected boolean conectado = false;
	private int menuOpt = 1;
	private String nombre;
	Client cliente;
	private Usuario usuario = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		super("Bomberman");

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 436);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(810, 400));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		setLocationRelativeTo(null);

		Font fntPlain = new Font("Tahoma", Font.PLAIN, 21);
		Font fntBold = new Font("Tahoma", Font.BOLD, 21);

		// declaraciones labels
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setFocusTraversalPolicyProvider(true);
		btnIniciarSesion.setBorderPainted(false);
		btnIniciarSesion.setContentAreaFilled(false);
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFocusPainted(false);
		btnRegistrarse.setFocusTraversalPolicyProvider(true);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setContentAreaFilled(false);
		JButton btnSala = new JButton("Salas");
		btnSala.setFocusPainted(false);
		btnSala.setFocusTraversalPolicyProvider(true);
		btnSala.setBorderPainted(false);
		btnSala.setContentAreaFilled(false);
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFocusPainted(false);
		btnSalir.setFocusTraversalPolicyProvider(true);

		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		conectado=true;

		btnIniciarSesion.setFont(fntBold);

		/*
		 * btnIniciarSesion.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyPressed(KeyEvent e) {
		 * 
		 * if (e.getKeyCode() == KeyEvent.VK_DOWN && menuOpt == 1) {
		 * btnIniciarSesion.setFont(fntPlain); btnSala.setFont(fntPlain);
		 * btnRegistrarse.setFont(fntBold); menuOpt = 2; }
		 * 
		 * if (e.getKeyCode() == KeyEvent.VK_UP && menuOpt == 1) {
		 * btnIniciarSesion.setFont(fntPlain); btnRegistrarse.setFont(fntPlain);
		 * btnSala.setFont(fntBold); menuOpt = 3; } } });
		 */

		// opcion por clic
		btnIniciarSesion.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				btnIniciarSesion.setFont(fntBold);
				btnRegistrarse.setFont(fntPlain);
				btnSala.setFont(fntPlain);
				btnSalir.setFont(fntPlain);

				if (menuOpt == 1 && btnIniciarSesion.isFocusOwner()) {
					JFrame iniciar = new IniciarSesion(usuario);
					iniciar.setVisible(true);
				}
				menuOpt = 1;
			}
		});

		btnIniciarSesion.setForeground(Color.YELLOW);
		btnIniciarSesion.setBounds(305, 264, 246, 23);
		btnIniciarSesion.setFocusable(true);
		contentPane.add(btnIniciarSesion);
		// FIN INICIAR SESION

		// INICIO REGISTRARSE

		btnRegistrarse.setFont(fntPlain);
		/*
		 * btnRegistrarse.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyPressed(KeyEvent e) { if (e.getKeyCode() ==
		 * KeyEvent.VK_DOWN && menuOpt == 2) { btnSala.setFont(fntBold);
		 * btnRegistrarse.setFont(fntPlain); btnIniciarSesion.setFont(fntPlain); menuOpt
		 * = 3; }
		 * 
		 * if (e.getKeyCode() == KeyEvent.VK_UP && menuOpt == 2) {
		 * btnRegistrarse.setFont(fntPlain); btnSala.setFont(fntPlain);
		 * btnIniciarSesion.setFont(fntBold); menuOpt = 1; }
		 * 
		 * }
		 * 
		 * });
		 */

		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnRegistrarse.setFont(fntBold);
				btnIniciarSesion.setFont(fntPlain);
				btnSala.setFont(fntPlain);
				btnSalir.setFont(fntPlain);
				if (menuOpt == 2 && btnRegistrarse.isFocusOwner()) {
					new Registrarse().setVisible(true);

				}
				menuOpt = 2;

			}
		});

		btnRegistrarse.setForeground(Color.YELLOW);
		btnRegistrarse.setFocusable(true);
		btnRegistrarse.setBounds(305, 298, 246, 23);
		contentPane.add(btnRegistrarse);
//FIN Label Registrarse

//INICIO Label SALA
		btnSala.setFont(fntPlain);
		/*
		 * btnSala.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyPressed(KeyEvent e) { if (e.getKeyCode() ==
		 * KeyEvent.VK_DOWN && menuOpt == 3) { btnIniciarSesion.setFont(fntBold);
		 * btnSala.setFont(fntPlain); btnRegistrarse.setFont(fntPlain); menuOpt = 1; }
		 * 
		 * if (e.getKeyCode() == KeyEvent.VK_UP && menuOpt == 3) {
		 * btnRegistrarse.setFont(fntBold); btnIniciarSesion.setFont(fntPlain);
		 * btnSala.setFont(fntPlain); menuOpt = 2; } }
		 * 
		 * });
		 */
		btnSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnSala.setFont(fntBold);
				btnIniciarSesion.setFont(fntPlain);
				btnRegistrarse.setFont(fntPlain);
				btnSalir.setFont(fntPlain);
				if (usuario == null) {
					JOptionPane.showMessageDialog(null, "PRIMERO DEBE INICIAR SESION");
				}
				if (menuOpt == 3 && btnSala.isFocusOwner() && usuario != null) {
					new Sala(nombre, cliente).setVisible(true);

				}
				menuOpt = 3;
			}
		});
		btnSala.setForeground(Color.YELLOW);
		btnSala.setFocusable(true);
		btnSala.setBounds(305, 332, 246, 23);
		contentPane.add(btnSala);

		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSalir.setFont(fntBold);
				btnIniciarSesion.setFont(fntPlain);
				btnRegistrarse.setFont(fntPlain);
				btnSala.setFont(fntPlain);
				if (menuOpt == 4 && btnSalir.isFocusOwner()) {
					confirmarCierreVentana();
				}
				menuOpt = 4;

			}
		});

		btnSalir.setForeground(Color.YELLOW);
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnSalir.setFocusable(true);
		btnSalir.setBounds(306, 366, 246, 23);
		contentPane.add(btnSalir);

		JLabel fondoGUI = new JLabel(new ImageIcon(this.getClass().getResource("/otras/superBombermanGUI.png")));
		fondoGUI.setLocation(0, 10);
		fondoGUI.setSize(810, 400);
		contentPane.add(fondoGUI);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});

		setContentPane(contentPane);
	}

	private void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar esta ventana", "confirmar Salir",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else

			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
}