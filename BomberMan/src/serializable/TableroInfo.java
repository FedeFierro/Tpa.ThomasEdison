package serializable;

import java.util.ArrayList;
import java.util.List;

public class TableroInfo {
	public int nivel;
	public String tiempo;
	public String imagen;
	public boolean pausa;
	public int puntosPartida;
	public String ganador;
	public boolean finJuego;
	public List<JugadorInfo> jugadoresInfo;
	public List<ElementoInfo> elementos;
	
	public TableroInfo() {
		this.nivel=0;
		this.tiempo="--";
		this.ganador="";
		puntosPartida=2;
		pausa=true;
		finJuego = false;
		jugadoresInfo = new ArrayList<JugadorInfo>();
	}
	
}
