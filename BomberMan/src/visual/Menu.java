package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

@SuppressWarnings("serial")
public class Menu extends JFrame  {

	private JPanel contentPane;
	protected boolean conectado = false;
	private int menuOpt = 1;
	Client cliente;
	private Usuario usuario = null;
	/* Buttons */
	JButton btnIniciarSesion;
	JButton btnRegistrarse;
	JButton btnSala;
	JButton btnSalir;
	JButton[] arrayBtns;
	JLabel fondoGUI;
	Font fntPlain;
	Font fntBold;

	/**
	 * Create the frame.
	 */
	public Menu() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 436);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				moverMenu(arg0);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});
		usuario = new Usuario(0, "test", "testnom", "testape", "testMail", "123");

		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(810, 400));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		setLocationRelativeTo(null);

		fntPlain = new Font("Tahoma", Font.PLAIN, 21);
		fntBold = new Font("Tahoma", Font.BOLD, 21);

		arrayBtns = new JButton[4];
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFocusable(false);
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setFocusTraversalPolicyProvider(true);
		btnIniciarSesion.setBorderPainted(false);
		btnIniciarSesion.setContentAreaFilled(false);
		btnIniciarSesion.setForeground(Color.YELLOW);
		btnIniciarSesion.setBounds(305, 264, 246, 23);
		btnIniciarSesion.setFont(fntBold);
		btnIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuOpt = 1;
				setBtnFocus();
				executeAction();
			}
		});
		contentPane.add(btnIniciarSesion);
		arrayBtns[0] = btnIniciarSesion;
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFocusable(false);
		btnRegistrarse.setFocusPainted(false);
		btnRegistrarse.setFocusTraversalPolicyProvider(true);
		btnRegistrarse.setBorderPainted(false);
		btnRegistrarse.setContentAreaFilled(false);
		btnRegistrarse.setFont(fntPlain);
		btnRegistrarse.setForeground(Color.YELLOW);
		btnRegistrarse.setBounds(305, 298, 246, 23);
		btnRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuOpt = 2;
				setBtnFocus();
				executeAction();
			}
		});
		arrayBtns[1] = btnRegistrarse;
		contentPane.add(btnRegistrarse);

		btnSala = new JButton("Salas");
		btnSala.setFocusable(false);
		btnSala.setFocusPainted(false);
		btnSala.setFocusTraversalPolicyProvider(true);
		btnSala.setBorderPainted(false);
		btnSala.setContentAreaFilled(false);
		btnSala.setFont(fntPlain);
		btnSala.setForeground(Color.YELLOW);
		btnSala.setBounds(305, 332, 246, 23);
		btnSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuOpt = 3;
				setBtnFocus();
				executeAction();
			}
		});

		arrayBtns[2] = btnSala;
		contentPane.add(btnSala);

		btnSalir = new JButton("Salir");
		btnSalir.setFocusable(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setFocusTraversalPolicyProvider(true);
		btnSalir.setBorderPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setForeground(Color.YELLOW);
		btnSalir.setFont(fntPlain);
		btnSalir.setBounds(306, 366, 246, 23);
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuOpt = 4;
				setBtnFocus();
				executeAction();
			}
		});
		arrayBtns[3] = btnSalir;
		contentPane.add(btnSalir);
		fondoGUI = new JLabel(new ImageIcon(this.getClass().getResource("/otras/superBombermanGUI.png")));
		fondoGUI.setLocation(0, 10);
		fondoGUI.setSize(810, 400);
		contentPane.add(fondoGUI);

		setContentPane(contentPane);
	}

	public void moverMenu(KeyEvent evento) {
		int keyCode = evento.getKeyCode();
		int indexFocus = 0;
		for (int i = 0; i < arrayBtns.length; i++) {
			if (arrayBtns[i].getFont() == fntBold) {
				indexFocus = i;
			}
		}

		if (keyCode == KeyEvent.VK_ENTER) {
			menuOpt = indexFocus + 1;
			executeAction();

		}
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
			int newIndex = keyCode == KeyEvent.VK_UP ? indexFocus - 1 : indexFocus + 1;
			newIndex = newIndex < 0 ? 3 : newIndex > 3 ? 0 : newIndex;
			menuOpt=newIndex+1;
			setBtnFocus();
		}
	}

	private void setBtnFocus() {
		int index = menuOpt - 1;
		for (int i = 0; i < arrayBtns.length; i++) {
			arrayBtns[i].setFont(i == index ? fntBold : fntPlain);
		}
	}

	private void executeAction() {
		switch (menuOpt) {
		case 1:
			JFrame iniciar = new IniciarSesion(this.usuario);
			iniciar.setVisible(true);
			break;
		case 2:
			JFrame registrar = new Registrarse();
			registrar.setVisible(true);
			break;
		case 3:
			if (usuario == null || usuario.getID() == 0) {
				JOptionPane.showMessageDialog(null, "PRIMERO DEBE INICIAR SESION");
				return;
			}
			if (usuario.getID() > 0) {
				JFrame sala = new Sala(this.usuario.getNombre());
				sala.isVisible();
			}

			break;
		case 4:
			confirmarCierreVentana();
			break;

		default:
			break;
		}

	}

	private void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar esta ventana", "confirmar Salir",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else

			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	public static void main(String[] args) {
		Menu frame = new Menu();
		frame.setVisible(true);
	}


}