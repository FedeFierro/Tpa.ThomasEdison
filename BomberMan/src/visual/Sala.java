package visual;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import database.*;
import helper.Helper;

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
	private JButton btnUnirseEspectador;
	JButton btnRefrescar;
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

		tablePartidas = new JTable(getEncabezadoTabla());
		tablePartidas.setBounds(10, 10, 454, 285);
		tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 10, 454, 285);
		scrollPanel.add(tablePartidas);
		contentPane.add(scrollPanel);
		actualizarListaDeSalas();
		
		btnCrearSala = new JButton("Nueva Sala ");
		btnCrearSala.setBounds(370, 308, 106, 23);
		contentPane.add(btnCrearSala);
		
		btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(10, 308, 106, 23);
		contentPane.add(btnUnirse);

		btnUnirseEspectador = new JButton("Espectador");
		btnUnirseEspectador.setBounds(130, 308, 106, 23);
		contentPane.add(btnUnirseEspectador);
		
		btnRefrescar = new JButton("Refrescar");
		
		btnRefrescar.setBounds(250, 308, 106, 23);
		contentPane.add(btnRefrescar);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 343, 454, 29);
		
		contentPane.add(btnSalir);

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
				o[3] = (Object)Helper.getEstadoSala(sala.getEstado());
				model.addRow(o);
			}
		}
		tablePartidas.setModel(model);
	}

	private DefaultTableModel getEncabezadoTabla() {
		String nombreColumnas[] = { "ID","Nombre", "Cantidad de Jugadores", "Estado" };
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
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarListaDeSalas();
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
								if(salaJuego.getEstado() ==2) {
									JOptionPane.showMessageDialog(null, "El juego esta Iniciado.");
									return;
								}
								if(salaJuego.getEstado() ==3) {
									JOptionPane.showMessageDialog(null, "El juego esta Finalizado.");
									return;
								}
								JuegoFrame juego = new JuegoFrame(salaJuego.getIP(), salaJuego.getPuerto(), nombre,false);
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
		btnUnirseEspectador.addActionListener(new ActionListener() {
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
								if(salaJuego.getEstado() ==3) {
									JOptionPane.showMessageDialog(null, "El juego esta Finalizado.");
									return;
								}
								if(salaJuego.getPuertoEspectador()==0) {
									JOptionPane.showMessageDialog(null, "El la sala no acepta espectadores.");
									return;
								}
								JuegoFrame juego = new JuegoFrame(salaJuego.getIP(), salaJuego.getPuertoEspectador(), nombre, true);
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