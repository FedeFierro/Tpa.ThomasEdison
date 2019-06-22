package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JPasswordField;
import cliente.*;
import database.DataBase;
import database.Usuario;

public class Registrarse extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JButton btnCrear;
	private JButton btnCancelar;
	private JPasswordField txtContrasena;
	private JTextField txtMail;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private DataBase db;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse frame = new Registrarse();
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
	public Registrarse() {
		setType(Type.POPUP);
		setTitle("Nuevo Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 278, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(27, 143, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNombreUsuario = new JLabel("Usuario");
		lblNombreUsuario.setBounds(27, 123, 118, 14);
		contentPane.add(lblNombreUsuario);

		JLabel lblContrasena = new JLabel("Contrase�a");
		lblContrasena.setBounds(155, 123, 86, 14);
		contentPane.add(lblContrasena);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(24, 233, 89, 23);
		contentPane.add(btnCrear);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(152, 233, 89, 23);
		contentPane.add(btnCancelar);

		txtContrasena = new JPasswordField();

		txtContrasena.setBounds(155, 143, 86, 20);
		contentPane.add(txtContrasena);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(27, 174, 58, 14);
		contentPane.add(lblMail);

		txtMail = new JTextField();
		txtMail.setBounds(27, 199, 214, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(24, 11, 48, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(24, 36, 214, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(24, 67, 48, 14);
		contentPane.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(24, 92, 214, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});
//		setFocusTraversalPolicy(new FocusTraversalOnArray(
//				new Component[] { newUserTextField, passwordField, crearButton, cancelarButton }));
		setVisible(true);
		addListener();
	}

	private void crearUsuario() {
		String usuario = txtUsuario.getText();
		String pass = String.valueOf(txtContrasena.getPassword());
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String mail = txtMail.getText();

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un Nombre");
			return;
		}
		
		if (apellido.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un Apellido");
			return;
		}
		if (usuario.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
			return;
		}

		if (pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese una contrase\u00F1a");
			return;
		}
	
		if (mail.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un mail");
			return;
		}
	
		db = new DataBase();
		db.conectar();
		Usuario user = new Usuario();
		user.setApellido(apellido);
		user.setNombre(nombre);
		user.setEmail(mail);
		user.setUsuario(usuario);
		user.setPassword(pass);
		db.crearUsuario(user);
		JOptionPane.showMessageDialog(this, "Usuario creado.");
		dispose();
		
		/*AGREGAR BD
		 * if (Client.crearUsuario(nombre, pass)) { JOptionPane.showMessageDialog(null,
		 * "Usuario registrado con exito"); dispose(); }
		 */

	}

	private void addListener() {

		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		txtApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		txtMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		txtContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		txtUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmarCierreVentana();			}
		});

	}
	private void confirmarCierreVentana() {
		int respuesta = JOptionPane.showConfirmDialog(this, "Desea cerrar esta ventana", "confirmar Salir",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			dispose();
		} else

			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	}
}