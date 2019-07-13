package visual;

import java.awt.Color;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TabableView;

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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaTitulo;
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

	public Sala(String nombre) {

		this.nombre = nombre;
		setTitle("Listado de Salas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		tablaTitulo = new JTable(getEncabezadoTabla());
		tablaTitulo.setBounds(10, 10, 580, 20);
		tablaTitulo.setSelectionBackground(Color.WHITE);
		tablaTitulo.setSelectionForeground(Color.BLACK);
		setHeaders();
		tablaTitulo.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		contentPane.add(tablaTitulo);
		tablePartidas = new JTable(getEncabezadoTabla());
		tablePartidas.setBounds(0, 20, 580, 270);
		tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePartidas.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));

		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 10, 580, 285);
		scrollPanel.add(tablePartidas);
		contentPane.add(scrollPanel);
		
		actualizarListaDeSalas();
		
		btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(65, 308, 106, 23);
		contentPane.add(btnUnirse);

		btnCrearSala = new JButton("Nueva Sala ");
		btnCrearSala.setBounds(425, 308, 106, 23);
		contentPane.add(btnCrearSala);

		btnUnirseEspectador = new JButton("Espectador");
		btnUnirseEspectador.setBounds(185, 308, 106, 23);
		contentPane.add(btnUnirseEspectador);

		btnRefrescar = new JButton("Refrescar");

		btnRefrescar.setBounds(315, 308, 106, 23);
		contentPane.add(btnRefrescar);
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				confirmarCierreVentana();
			}
		});

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(65, 343, 454, 29);

		contentPane.add(btnSalir);

		addListener();
		// actualizarListaDeSalas(true);
	}

	private void setHeaders() {
		DefaultTableModel model = getEncabezadoTabla();
		Object[] enc = new Object[7];
		enc[0] = "ID";
		enc[1] = "NOMBRE";
		enc[2] = "JUG.";
		enc[3] = "PUNTOS";
		enc[4] = "TIEMPO";
		enc[5] = "ESTADO";
		enc[6] = "PRIVADA";
		model.addRow(enc);
		tablaTitulo.setModel(model);

	}

	private void actualizarListaDeSalas() {

		int row = tablePartidas.getSelectedRow();
		db = new DataBase();
		List<SalaDB> salas = db.getSalas();

		DefaultTableModel model = getEncabezadoTabla();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}

		if (salas != null) {
			for (SalaDB sala : salas) {
				Object[] o = new Object[7];
				o[0] = sala.getID();
				o[1] = sala.getNombre();
				o[2] = sala.getCantJugadores();
				o[3] = sala.getPuntos();
				o[4] = sala.getTiempo();
				o[5] = (Object) Helper.getEstadoSala(sala.getEstado());
				o[6] = sala.getPrivada() ? "Privada" : "Publica";
				model.addRow(o);
			}
		}
		tablePartidas.setModel(model);
		if (row >= 0) {
			System.out.println(row);
			tablePartidas.setRowSelectionInterval(row, row);
		}
	}

	private DefaultTableModel getEncabezadoTabla() {
		String nombreColumnas[] = { "ID", "Nombre", "Jugadores", "Puntos", "Tiempo", "Estado", "Privada" };
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
							SalaDB salaJuego = db.getSala(idSala);
							if (salaJuego != null) {
								if (salaJuego.getEstado() == 2) {
									JOptionPane.showMessageDialog(null, "El juego esta Iniciado.");
									return;
								}
								if (salaJuego.getEstado() == 3) {
									JOptionPane.showMessageDialog(null, "El juego esta Finalizado.");
									return;
								}
								if(!salaJuego.getPrivada()) {
									abrirJuego(salaJuego, nombre);
								}
								else {
									if(esClaveValida(salaJuego)) {
										abrirJuego(salaJuego, nombre);
									}
								}
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
							database.SalaDB salaJuego = db.getSala(idSala);
							if (salaJuego != null) {
								if (salaJuego.getEstado() == 3) {
									JOptionPane.showMessageDialog(null, "El juego esta Finalizado.");
									return;
								}
								if (salaJuego.getPuertoEspectador() == 0) {
									JOptionPane.showMessageDialog(null, "El la sala no acepta espectadores.");
									return;
								}
								
								if(!salaJuego.getPrivada()) {
									abrirComoEspectador(salaJuego);
								}
								else {
									if(esClaveValida(salaJuego)) {
										abrirComoEspectador(salaJuego);
									}
								}
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
	private void abrirJuego(SalaDB sala,String nombre) {
		JuegoFrame juego = new JuegoFrame(sala.getIP(), sala.getPuerto(), nombre,false);
			juego.setVisible(true);
	}
	private void abrirComoEspectador(SalaDB sala) {
		JuegoFrame juego = new JuegoFrame(sala.getIP(), sala.getPuertoEspectador(), "",true);
		juego.setVisible(true);
	}
	private boolean esClaveValida(SalaDB sala) {
		String clave = JOptionPane.showInputDialog("Ingrese Clave");
		if(clave==null) {
			return false;
		}
		if(!clave.equals(sala.getClave())) {
			JOptionPane.showMessageDialog(this, "Clave Incorrecta");
			return false;
		}
		return true;
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