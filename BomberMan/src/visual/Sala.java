package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import cliente.Client;
import database.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
	private Client cliente;
	private DataBase db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sala frame = new Sala("Test",null);
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
	public Sala(String nombre,Client cliente) {

		/**
		 * Create the frame.
		 */
			this.nombre = nombre;
			this.cliente = cliente;
			db = new DataBase();
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

			db.conectar();
			
			List<database.Sala> lista = db.getSalas();
			
			String nombreColumnas[] = {"ID", "Cantidad de Jugadores", "IP", "Puerto", "Estado"};
			DefaultTableModel dtm = new DefaultTableModel(null, nombreColumnas);
			for(database.Sala sala :lista) {
				Object[] o = new Object[5];
				o[0]= sala.getID();
				o[1]=sala.getNombre();
				o[2]=sala.getCantJugadores();
				o[3]=sala.getIP();
				o[4]=sala.getPuerto();
				dtm.addRow(o);
			}
			
			tablePartidas = new JTable(dtm);
			tablePartidas.setBounds(10, 10, 454, 285);
			tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPane.add(tablePartidas);
			actualizarListaDeSalas(false);
			btnUnirse = new JButton("Unirse");
			btnUnirse.setBounds(10, 308, 106, 23);
			btnUnirse.setEnabled(false);
			contentPane.add(btnUnirse);

			btnSalir = new JButton("Salir");
			btnSalir.setBounds(10, 343, 454, 29);
			contentPane.add(btnSalir);
			
			JButton btnRefrescar = new JButton("Refrescar");
			btnRefrescar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarListaDeSalas(false);
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
			//actualizarListaDeSalas(true);
		}

		private void actualizarListaDeSalas(boolean flag) {
			db.conectar();
			
			List<database.Sala> salas = db.getSalas();
			tablePartidas.removeAll();

			System.out.println(salas);
			if (salas != null)
				for (database.Sala sala : salas) {
					System.out.println(sala.getID());
					DefaultTableModel model = (DefaultTableModel) tablePartidas.getModel();
					model.addRow(new Object[] { sala.getNombre() });
				}
		}

		private void addListener() {

			btnCrearSala.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NuevaSala sala = new NuevaSala("Test",null);
					sala.setVisible(true);
//					cliente.agregarSala(sala);
					dispose();
				}
			});

			btnUnirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

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