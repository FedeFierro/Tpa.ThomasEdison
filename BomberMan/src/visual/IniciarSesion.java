package visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Client;
import database.DataBase;
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


@SuppressWarnings("serial")
public class IniciarSesion extends JFrame {

	static String usuario;
	private String pass;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnIngresar;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JButton btnRegistrarse;
	private Usuario user;
	private DataBase database;


	public IniciarSesion(Usuario usuario) {
		this.user = usuario;
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

	private void iniciarSesion() {
		usuario = txtUsuario.getText();
		pass = String.valueOf(txtContrasena.getPassword());

		if (usuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un usuario", "Error login", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese la contrase\u00F1a", "Error login",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		Usuario usLog = new Usuario();
		
		usLog.setUsuario(usuario);
		usLog.setPassword(pass);
		
		database = new DataBase();
		
		usLog = database.logear(usLog);
		database.dispose();
		if (usLog !=null && usLog.getID() > 0) {
			this.user.copiarUsuario(usLog);
			JOptionPane.showMessageDialog(null, "Inicio de sesion exitoso");
			dispose();

		} else if (usLog==null || usLog.getID() == 0) {
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

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
				// LOGIN BASE DATOS

			}
		});

	}
}
