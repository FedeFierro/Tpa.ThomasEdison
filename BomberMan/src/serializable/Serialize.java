package serializable;

import java.util.ArrayList;
import java.util.List;

public class Serialize {
	public int nivel;
	public String tiempo;
	public List<JugadorInfo> jugadoresInfo;
	public List<ElementSerializable> elementos;
	
	public Serialize() {
		this.nivel=1;
		this.tiempo= "0050";
		
		jugadoresInfo = new ArrayList<JugadorInfo>();
		elementos = new ArrayList<ElementSerializable>();
	}
	
}
