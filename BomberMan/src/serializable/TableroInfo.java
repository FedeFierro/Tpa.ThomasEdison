package serializable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TableroInfo implements JsonSerializer<TableroInfo>{
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

	@Override
	public JsonElement serialize(TableroInfo tableroInfo, Type type, JsonSerializationContext conext) {
		// TODO Auto-generated method stub
		JsonObject resultado = new JsonObject();
		resultado.add("nivel", new JsonPrimitive(nivel));
		resultado.add("tiempo", new JsonPrimitive(tiempo));
		resultado.add("imagen", new JsonPrimitive(imagen));
		resultado.add("pausa", new JsonPrimitive(pausa));
		resultado.add("puntosPartida", new JsonPrimitive(puntosPartida));
		resultado.add("ganador", new JsonPrimitive(ganador));
		resultado.add("finJuego", new JsonPrimitive(finJuego));
		resultado.add("sonido", new JsonPrimitive(sonido));
		return resultado;
	}	
	
}
