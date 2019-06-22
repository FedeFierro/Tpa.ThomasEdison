package database;
import java.io.File;
import java.net.InetAddress;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.Entity;
public class DataBase {

	private String nombreBD;
	private String config;
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;
	private Sala sala;
	
	
	public DataBase() {
	}
	
	public void desconectar() {
		session.close();
		factory.close();
	}
	public void conectar() {
		
		cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();
		session = factory.openSession();
		
	}
	
	public String getNombreBD() {
		return nombreBD;
	}

	public void setNombreBD(String nombreBD) {
		this.nombreBD = nombreBD;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public void guardarSala(Sala sala) {
		
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(sala);
		tx.commit();
//		session.close();
//		factory.close();
	}
	
	
	public void borrarSala(Sala sala) {
		
		Transaction tx = session.beginTransaction();
		session.delete(sala);
		tx.commit();
//		session.close();
//		factory.close();
	}
	
	public void crearUsuario(Usuario user) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
	}
	
	public Usuario logear(Usuario user) {
		Usuario usuario = null;
		
		try{
			Query q = session.createQuery("FROM Ususario u WHERE u.username = :nombreUsuario AND u. password = :password");
			q.setParameter("nombreUsuario", user.getUsuario());
			q.setParameter("password", user.getPassword());
			usuario = (Usuario)q.uniqueResult();
		}
		 catch (HibernateException he)
        {
            
        }
        finally
        {
            
        }
				
		
		
		return usuario;
	}
	public List<Sala> getSalas(){
		Query q = session.createQuery("Select s from Sala s");
		@SuppressWarnings("unchecked")
		List<Sala> lista = q.getResultList();
		return lista!=null? lista: new ArrayList<Sala>();
		
	}
}
