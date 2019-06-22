package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Client;
import javax.swing.JTextField;

public class NuevaSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JButton btnCancelar;
	public JComboBox<?> cmbCantidadJugadores;
	public JComboBox<?> cmbTiempo;
	private String nombreJugador;
	private Client cliente;
	public JLabel lblNombreSala;
	private JTextField txtNombreSala;
	private JTextField txtPuntos;
	private JTextField txtPuerto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaSala frame = new NuevaSala("Test", null);
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
	public NuevaSala(String nombreJugador, Client cliente) {
		setTitle("Nueva Sala");
		this.nombreJugador = nombreJugador;
		this.cliente = cliente;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		contentPane.setLayout(null);

		JLabel lblCantidadJugadores = new JLabel("Cantidad jugadores");
		lblCantidadJugadores.setBounds(10, 73, 109, 14);
		contentPane.add(lblCantidadJugadores);

		cmbCantidadJugadores = new JComboBox();
		cmbCantidadJugadores.setModel(new DefaultComboBoxModel(new String[] { "2 BOMBERMAN", "3 BOMBERMAN", "4 BOMBERMAN" }));
		cmbCantidadJugadores.setSelectedIndex(0);
		
		cmbCantidadJugadores.setBounds(129, 70, 166, 20);
		contentPane.add(cmbCantidadJugadores);

		btnIniciar = new JButton("Crear");
		btnIniciar.setBounds(268, 217, 127, 23);
		contentPane.add(btnIniciar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(106, 217, 127, 23);
		contentPane.add(btnCancelar);

		lblNombreSala = new JLabel("Nombre Sala");
		lblNombreSala.setBounds(10, 42, 94, 14);
		contentPane.add(lblNombreSala);

		txtNombreSala = new JTextField();
		txtNombreSala.setBounds(129, 39, 166, 20);
		contentPane.add(txtNombreSala);
		txtNombreSala.setColumns(10);

		cmbTiempo = new JComboBox();
		cmbTiempo.setBounds(129, 101, 166, 20);
		
		cmbTiempo.setModel(
				new DefaultComboBoxModel(new String[] { "40", "60", "80", "100", "120", "140", "160", "180", "200" }));
		cmbTiempo.setSelectedIndex(0);
		
		contentPane.add(cmbTiempo);

		JLabel lblTiempo = new JLabel("Tiempo (segundos)");
		lblTiempo.setBounds(10, 104, 109, 14);
		contentPane.add(lblTiempo);

		txtPuntos = new JTextField();
		txtPuntos.setBounds(129, 132, 166, 20);
		contentPane.add(txtPuntos);
		txtPuntos.setColumns(10);

		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBounds(10, 135, 48, 14);
		contentPane.add(lblPuntos);

		txtPuerto = new JTextField();
		txtPuerto.setBounds(129, 163, 166, 20);
		contentPane.add(txtPuerto);
		txtPuerto.setColumns(10);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 166, 48, 14);
		contentPane.add(lblPuerto);

		setContentPane(contentPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});
		setVisible(true);
		addListener();
	}

	public String getNombre() {
		return nombreJugador;
	}

	private void crear() {
		String nombre = txtNombreSala.getText();
		String puerto = txtPuerto.getText();
		int cantJugadores = cmbCantidadJugadores.getSelectedIndex()+2;
		String puntos = txtPuntos.getText();
		String tiempo = cmbTiempo.getSelectedItem().toString();
		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un Nombre");
			return;
		}
		
		int port = Integer.parseInt(puerto);
		if (puerto.isEmpty() || port ==0 ) {
			JOptionPane.showMessageDialog(null, "Ingrese numero de puerto");
			return;
		}
		int points = Integer.parseInt(puntos);
		if (puntos.isEmpty()|| points < 1 ) {
			JOptionPane.showMessageDialog(null, "Ingrese la cantidad de puntos para ganar el juego.");
			return;
		}

		if (cantJugadores < 2||cantJugadores>4) {
			JOptionPane.showMessageDialog(null, "la cantidad de Jugadores debe ser entre 2 y 4.");
			return;
		}
	
		int time = Integer.parseInt(tiempo); 
		if (time < 40) {
			JOptionPane.showMessageDialog(null, "Debe ingresar un valor para el tiempo de cada nivel.");
			return;
		}

		
		ServerFrame  sframe = new ServerFrame(nombre,port, time,points,cantJugadores);
		sframe.setVisible(true);
		dispose();
	}

	private void addListener() {
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//		 VERIFICAR SI UNA VEZ CONECTADO y VUELVO NO SE PIERDE CONEX
				crear();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarCierreVentana();
			}
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
