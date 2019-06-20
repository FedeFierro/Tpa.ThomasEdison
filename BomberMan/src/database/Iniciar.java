package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Iniciar {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		
		
		
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
}
