package database;

public class Sala {
	private int ID;
	private String Nombre;
	private int cantJugadores;
	private String IP;
	private int Puerto;
	private int Estado;
	private int PuertoEspectador;

	
	public Sala() {}
	
	public Sala(int i, String N, int c, String ip, int p, int pEspectador) {
		this.ID = i;
		this.Nombre = N;
		this.cantJugadores = c;
		this.IP = ip;
		this.Puerto = p;
		this.Estado = 1;
		this.PuertoEspectador = pEspectador;
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

	
	
	
}
