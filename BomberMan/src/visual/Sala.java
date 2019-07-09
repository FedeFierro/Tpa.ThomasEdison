package visual;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import cliente.Client;
import database.*;
import net.bytebuddy.asm.Advice.This;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Sala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePartidas;
	private JButton btnCrearSala;
	private JButton btnSalir;
	private JButton btnUnirse;
	private String nombre;
	private DataBase db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sala frame = new Sala("Test");
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
	public Sala(String nombre) {

		/**
		 * Create the frame.
		 */
		this.nombre = nombre;
		setTitle("Listado de Salas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		btnCrearSala = new JButton("Nueva Sala ");
		btnCrearSala.setBounds(358, 308, 106, 23);
		contentPane.add(btnCrearSala);

		tablePartidas = new JTable(getEncabezadoTabla());
		tablePartidas.setBounds(10, 10, 454, 285);
		tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 10, 454, 285);
		scrollPanel.add(tablePartidas);
		contentPane.add(scrollPanel);
		actualizarListaDeSalas();
		btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(10, 308, 106, 23);
		// btnUnirse.setEnabled(false);
		contentPane.add(btnUnirse);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 343, 454, 29);
		contentPane.add(btnSalir);

		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarListaDeSalas();
			}
		});
		btnRefrescar.setBounds(192, 308, 96, 23);
		contentPane.add(btnRefrescar);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});

		addListener();
		// actualizarListaDeSalas(true);
	}

	private void actualizarListaDeSalas() {

		db = new DataBase();
		List<database.Sala> salas = db.getSalas();
		DefaultTableModel model = getEncabezadoTabla();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}

		if (salas != null) {
			for (database.Sala sala : salas) {
				Object[] o = new Object[5];
				o[0] = sala.getID();
				o[1] = sala.getNombre();
				o[2] = sala.getCantJugadores();
				o[3] = sala.getIP();
				o[4] = sala.getPuerto();
				model.addRow(o);
			}
		}
		tablePartidas.setModel(model);
	}

	private DefaultTableModel getEncabezadoTabla() {
		String nombreColumnas[] = { "ID", "Cantidad de Jugadores", "IP", "Puerto", "Estado" };
		return new DefaultTableModel(null, nombreColumnas);
	}

	private void addListener() {

		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaSala sala = new NuevaSala();
				sala.setVisible(true);
				dispose();
			}
		});

		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tablePartidas.getSelectedRow();
				if (row >= 0) {
					Object sala = tablePartidas.getValueAt(row, 0);
					if (sala != null) {
						try {
							int idSala = (int) sala;
							db = new DataBase();
							database.Sala salaJuego = db.getSala(idSala);
							if (salaJuego != null) {
								JuegoFrame juego = new JuegoFrame(salaJuego.getIP(), salaJuego.getPuerto(), nombre);
								juego.setVisible(true);
							}

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe Seleccionar una sala");
				}

			}
		});

		btnSalir.addActionListener(new ActionListener() {
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