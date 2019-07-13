package database;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable;
import javax.swing.JOptionPane;

public class DataBase implements Disposable {

	private Configuration cfg;
	private SessionFactory factory;
	private Session session;

	public DataBase() {
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		if (factory == null || factory.isClosed()) {
			factory = cfg.buildSessionFactory();
		}
	}

	public void guardarSala(SalaDB sala) {

		try {
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(sala);
			tx.commit();
		} catch (Exception e) {
			mostrarError("Error al crear la sala.");
		} finally {
			session.close();
		}
	}

	public void borrarSala(SalaDB sala) {

		try {
			session= factory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(sala);
			tx.commit();
		} catch (Exception e) {
			mostrarError("Error al eliminar la Sala");
		} finally {
			session.close();
		}
	}

	public boolean crearUsuario(Usuario user) {
		try {
			Usuario existe = existeUsuario(user);
			session = factory.openSession();

			if (existe == null) {
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(user);
				tx.commit();
				return true;
			}else {
				
				mostrarError("El usuario ya existe.");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mostrarError("Error al crear Usuario");
			return false;
		} finally {
			session.close();
		}
	}

	public Usuario logear(Usuario user) {
		
		try {
			session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<Usuario> q = session
					.createQuery("FROM Usuario u WHERE u.Usuario = :nombreUsuario AND u.Password = :password");
			q.setParameter("nombreUsuario", user.getUsuario());
			q.setParameter("password", user.getPassword());
			return q.uniqueResult();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR INICIANDO SESION");
			return null;
		} finally {
			session.close();
		}

	}

	public List<SalaDB> getSalas() {
		try {
			session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<SalaDB> q = session.createQuery("From SalaDB s");
			List<SalaDB> lista = q.getResultList();
			return lista != null ? lista : new ArrayList<SalaDB>();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mostrarError("Error obteniendo Salas.");
			return null;
		} finally {
			session.close();
		}
	}
	public SalaDB getSala(int idSala) {
		try {
		session= factory.openSession();
		@SuppressWarnings("unchecked")
		Query<SalaDB> q = session.createQuery("FROM SalaDB s WHERE s.ID = :idSala");
		q.setParameter("idSala", idSala);
		return q.uniqueResult();
		}catch (Exception e) {
			mostrarError(String.format("Error al obtener la sala: %d",idSala));
			return null;
		}finally {
			session.close();
		}
		
	}

	@Override
	public void dispose() {
		session.close();
		factory.close();
	}

	private Usuario existeUsuario(Usuario user) {
		try {
			session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<Usuario> q = session.createQuery("FROM Usuario u WHERE u.Usuario = :nombreUsuario ");
			q.setParameter("nombreUsuario", user.getUsuario());
			return q.uniqueResult();
		} catch (Exception e) {
			System.err.println("error en existe usuario");
			return null;
		} finally {
			session.close();
		}

	}

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
