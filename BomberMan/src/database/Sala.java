package database;

import java.net.InetAddress;

public class Sala {
	private int ID;
	private String Nombre;
	private int cantJugadores;
	private int Tope;
	private String IP;
	private int Puerto;

	
	public Sala() {}
	
	public Sala(int i, String N, int c, int t, String ip, int p) {
		this.ID = i;
		this.Nombre = N;
		this.cantJugadores = c;
		this.Tope = t;
		this.IP = ip;
		this.Puerto = p;
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

	@Override
	public String toString() {
		return "Sala [ID=" + ID + ", Nombre=" + Nombre + ", cantJugadores=" + cantJugadores + ", Tope=" + Tope + ", IP="
				+ IP + ", Puerto=" + Puerto + "]";
	}
	
}
