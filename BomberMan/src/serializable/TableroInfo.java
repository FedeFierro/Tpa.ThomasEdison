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
	public String sonido;
	public List<JugadorInfo> jugadoresInfo;
	public List<ElementoInfo> elementos;
	
	public TableroInfo(int puntosPartida) {
		this.nivel=0;
		this.tiempo="--";
		this.ganador="";
		this.puntosPartida=puntosPartida;
		pausa=true;
		finJuego = false;
		jugadoresInfo = new ArrayList<JugadorInfo>();
	}
	
}
