package database;

import java.io.File;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.Entity;

public class Iniciar {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Configuration cfg = new Configuration();

		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();

		Usuario user = new Usuario();
		user.setID(5);
		user.setUsuario("Aonatan");
		user.setEmail("salguerojonatan@gmail.com");
		user.setPassword("asd");
		user.setNombre("Jonatna");
		user.setApellido("Salguero");

//		Transaction tx = session.beginTransaction();
//		try {
			
//			tx.commit();
			
			session.save(user);
			
			Query q = session.createQuery("Select p from Usuario p");
			List<Usuario> usuarios = q.getResultList();
			for(Usuario i : usuarios)
				System.out.println(i);
		
//		} catch (HibernateException e) {
//			if (tx != null)
//				tx.rollback();
//			e.printStackTrace();
//		} finally {
			session.close();
			factory.close();
//		}

	}

}
