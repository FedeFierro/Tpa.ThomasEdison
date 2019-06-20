package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Iniciar {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Usuario usuario = new Usuario();

		Configuration cfg = new Configuration();

		cfg.configure("database/hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		usuario.setApellido("Salguero");
		usuario.setEmail("salguerojonatan@hotmail.com");
		usuario.setNombre("Jonatan");
		usuario.setPassword("asd");
		usuario.setUsuario("salguerojonatan");
		
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(usuario);
		} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		e.printStackTrace();
	} finally {
		session.close();
		factory.close();
	}
		

	}
}
