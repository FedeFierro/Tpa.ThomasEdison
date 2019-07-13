package visual;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.SalaDB;

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
	public JLabel lblNombreSala;
	private JTextField txtNombreSala;
	private JTextField txtPuntos;
	private JTextField txtPuerto;
	private JTextField txtPuertoEspectador;
	private JCheckBox chkPrivada;
	private JTextField txtClave;
	private final int COL_LABEL = 10;
	private final int WIDTH_DATA = 166;
	private final int COL_DATA=180;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaSala frame = new NuevaSala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NuevaSala() {
		setTitle("Nueva Sala");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		lblNombreSala = new JLabel("Nombre Sala");
		lblNombreSala.setBounds(COL_LABEL, 42, 94, 14);
		contentPane.add(lblNombreSala);
		
		txtNombreSala = new JTextField();
		txtNombreSala.setBounds(COL_DATA, 39, WIDTH_DATA, 20);
		contentPane.add(txtNombreSala);
		txtNombreSala.setColumns(10);
		
		JLabel lblCantidadJugadores = new JLabel("Cantidad jugadores");
		lblCantidadJugadores.setBounds(COL_LABEL, 73, 109, 14);
		contentPane.add(lblCantidadJugadores);

		cmbCantidadJugadores = new JComboBox<String>();
		cmbCantidadJugadores.setModel(new DefaultComboBoxModel(new String[] { "2 BOMBERMAN", "3 BOMBERMAN", "4 BOMBERMAN" }));
		cmbCantidadJugadores.setSelectedIndex(0);
		cmbCantidadJugadores.setBounds(COL_DATA, 70, WIDTH_DATA, 20);
		contentPane.add(cmbCantidadJugadores);

		JLabel lblTiempo = new JLabel("Tiempo (segundos)");
		lblTiempo.setBounds(COL_LABEL, 104, 150, 14);
		contentPane.add(lblTiempo);
		
		cmbTiempo = new JComboBox<String>();
		cmbTiempo.setBounds(COL_DATA, 101, WIDTH_DATA, 20);
		
		cmbTiempo.setModel(
				new DefaultComboBoxModel(new String[] { "40", "60", "80", "100", "120", "140", "160", "180", "200" }));
		cmbTiempo.setSelectedIndex(0);
		
		contentPane.add(cmbTiempo);

		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setBounds(COL_LABEL, 135, 48, 14);
		contentPane.add(lblPuntos);
		
		
		txtPuntos = new JTextField();
		txtPuntos.setBounds(COL_DATA, 132, WIDTH_DATA, 20);
		contentPane.add(txtPuntos);
		txtPuntos.setColumns(10);

		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(COL_LABEL, 166, 48, 14);
		contentPane.add(lblPuerto);

		txtPuerto = new JTextField();
		txtPuerto.setBounds(COL_DATA, 163, WIDTH_DATA, 20);
		contentPane.add(txtPuerto);
		txtPuerto.setColumns(10);

		JLabel lblPuertoEspectador = new JLabel("Puerto Espectador");
		lblPuertoEspectador.setBounds(COL_LABEL,197,150,14);
		contentPane.add(lblPuertoEspectador);

		txtPuertoEspectador = new JTextField();
		txtPuertoEspectador.setBounds(COL_DATA, 194, WIDTH_DATA, 20);
		contentPane.add(txtPuertoEspectador);
		
		txtPuertoEspectador.setColumns(10);
		
		JLabel lblPrivada = new JLabel("Privada");
		lblPrivada.setBounds(COL_LABEL,228,48,14);
		contentPane.add(lblPrivada);
		
		chkPrivada = new JCheckBox();
		chkPrivada.setBounds(70,225, 23, 23);
		contentPane.add(chkPrivada);
		
		JLabel lblClave=new JLabel("Clave");
		lblClave.setBounds(120, 228, 48, 14);
		contentPane.add(lblClave);
		
		txtClave= new JTextField();
		txtClave.setBounds(180,225,WIDTH_DATA,20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		btnIniciar = new JButton("Crear");
		btnIniciar.setBounds(110, 260, 127, 23);
		contentPane.add(btnIniciar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(270, 260, 127, 23);
		contentPane.add(btnCancelar);

		
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
		String puntos = txtPuntos.getText().trim();
		String tiempo = cmbTiempo.getSelectedItem().toString();
		String puertoEspectador = txtPuertoEspectador.getText().trim();
		boolean privada = chkPrivada.isSelected();
		String clave = txtClave.getText().trim();
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
		int specPort =  puertoEspectador!=""? Integer.parseInt(puertoEspectador):0;
		if(privada && clave.isEmpty()){
			JOptionPane.showMessageDialog(null, "Para una sala privada debe crear una clave.");
			return;
		}		
		SalaDB salaDB = new SalaDB(nombre, cantJugadores, null,port,points,specPort,privada,clave,time);
				
		ServerFrame  sframe = new ServerFrame(salaDB);
		sframe.setVisible(true);
		dispose();
	}

	private void addListener() {
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
