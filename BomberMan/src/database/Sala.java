package database;

public class Sala {
	
	private String Nombre;
	private int Tope;
	private String IP;
	private int Puerto;
	private int userConectados;
	private int Estado;

	
	public Sala(String n, int t, String i, int p, int u, int e) {
		this.Nombre = n;
		this.Tope = t;
		this.IP = i;
		this.Puerto = p;
		this.userConectados = u;
		this.Estado = e;
	}
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getTope() {
		return Tope;
	}

	public void setTope(int tope) {
		Tope = tope;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPuerto() {
		return Puerto;
	}

	public void setPuerto(int puerto) {
		Puerto = puerto;
	}

	public int getUserConectados() {
		return userConectados;
	}

	public void setUserConectados(int userConectados) {
		this.userConectados = userConectados;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}

	
	
}
