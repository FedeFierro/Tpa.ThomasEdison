package database;

public class SalaDB {
	private int ID;
	private String Nombre;
	private int cantJugadores;
	private String IP;
	private int Puerto;
	private int Estado;
	private int PuertoEspectador;
	private boolean Privada;
	private String Clave;
	private int Puntos;
	private int Tiempo;

	
	public SalaDB() {}
	
	public SalaDB(String nombre, int jugadores, String ip, int puerto,int puntos,  int pEspectador, 
			boolean privada, String clave,int tiempo) {
		this.Nombre = nombre;
		this.cantJugadores = jugadores;
		this.IP = ip;
		this.Puerto = puerto;
		this.Estado = 1;
		this.PuertoEspectador = pEspectador;
		this.Privada = privada;
		this.Clave=clave;
		this.Puntos = puntos;
		this.Tiempo=tiempo;
	}
	public SalaDB(int id, String nombre, int jugadores, String ip, int puerto,int puntos,  int pEspectador, 
			boolean privada, String clave,int tiempo) {
		this.ID=id;
		this.Nombre = nombre;
		this.cantJugadores = jugadores;
		this.IP = ip;
		this.Puerto = puerto;
		this.Estado = 1;
		this.PuertoEspectador = pEspectador;
		this.Privada = privada;
		this.Clave=clave;
		this.Puntos = puntos;
		this.Tiempo=tiempo;
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getCantJugadores() {
		return cantJugadores;
	}

	public void setCantJugadores(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int Estado) {
		this.Estado = Estado;
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
	public void setPuerto(int p) {
		this.Puerto =p;
	}
	
	public int getPuertoEspectador() {
		return this.PuertoEspectador;
	}
	public void setPuertoEspectador(int pEspectador) {
		this.PuertoEspectador = pEspectador;
	}
	public Boolean getPrivada() {
		return this.Privada;
	}
	public void setPrivada(boolean b) {
		this.Privada=b;
	}
	public String getClave() {
		return this.Clave;
	}
	public void setClave(String c) {
		this.Clave=c;
	}
	public int getPuntos() {
		return this.Puntos;
	}
	public void setPuntos(int puntos) {
		this.Puntos=puntos;
	}
	public int getTiempo() {
		return this.Tiempo;
	}
	public void setTiempo(int tiempo) {
		this.Tiempo=tiempo;
	}

	
	
	
}
