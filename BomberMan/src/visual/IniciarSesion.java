package visual;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Client;
import database.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Window.Type;

public class IniciarSesion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String usuario;
	private String pass;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnIngresar;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JButton btnRegistrarse;
	private Client cliente;
	public static boolean conectado = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion(conectado);
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
	public IniciarSesion(boolean conectado) {
		setTitle("Iniciar Sesion");
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 383, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 14, 55, 14);
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(128, 11, 178, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(10, 112, 110, 23);
		contentPane.add(btnIngresar);

		lblContrasena = new JLabel("Contrase�a");
		lblContrasena.setBounds(10, 61, 73, 14);
		contentPane.add(lblContrasena);

		txtContrasena = new JPasswordField();
		txtContrasena.setToolTipText("");
		txtContrasena.setColumns(10);
		txtContrasena.setBounds(128, 58, 178, 20);
		contentPane.add(txtContrasena);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(130, 112, 110, 23);
		contentPane.add(btnRegistrarse);

		JButton btnCancelar = new JButton("Cancelar");
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarCierreVentana();
			}
		});
		btnCancelar.setBounds(250, 112, 110, 23);
		contentPane.add(btnCancelar);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});

		addListener();
	}

	private void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar esta ventana", "confirmar Salir",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			dispose();
		} else

			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}

	private void iniciarSesion(boolean conectado) {
		usuario = txtUsuario.getText();
		pass = String.valueOf(txtContrasena.getPassword());
		Menu conect = new Menu();
		conect.conectado = this.isConectado();
		
		
		if (usuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un usuario", "Error login", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese la contrase\u00F1a", "Error login",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

//		int state = cliente.loguear(new Usuario(usuario,null,null,null,pass)); 
		int state = 1;
		if (state == 1) {
//			new Menu(usuario, cliente);
			JOptionPane.showMessageDialog(null, "TE LOGUEASTE OK BRO, AHORA MODIFICAME");
			conectado = true;
			IniciarSesion.setConectado(true);
			conect.conectado = true;
			
			dispose();
			// LOGIN BD

		} else if (state == 0) {
			JOptionPane.showMessageDialog(null, "Nombre de usuario o contrase\u00F1a incorrectos", "Error login",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con el servidor", "Error login",
					JOptionPane.WARNING_MESSAGE);
		}

		txtContrasena.setText("");
		txtUsuario.setText("");
	}

	private void addListener() {
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registrarse().setVisible(true);
			}
		});

		txtContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion(conectado);
			}
		});

		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion(conectado);
			}
		});

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion(conectado);
				// LOGIN BASE DATOS

			}
		});

	}

	public static boolean isConectado() {
		return conectado;
	}

	public static void setConectado(boolean conectado) {
		IniciarSesion.conectado = conectado;
	}
}
