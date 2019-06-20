package serializable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class TableroInfo implements JsonSerializer<TableroInfo>, JsonDeserializer<TableroInfo>{
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
		elementos = new ArrayList<ElementoInfo>();
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
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<JugadorInfo>>() {}.getType();
		String json = gson.toJson(jugadoresInfo, listType);
		resultado.add("jugadoresInfo", new JsonPrimitive(json));
		
		listType = new TypeToken<ArrayList<ElementoInfo>>() {}.getType();
		json = gson.toJson(elementos, listType);
		resultado.add("elementos", new JsonPrimitive(json));
		
		return resultado;
	}

	@Override
	public TableroInfo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		// TODO Auto-generated method stub
//		Type typeToken = new TypeToken<ArrayList<TableroInfo>>() { }.getType();
	 
	    TableroInfo tablero = new Gson().fromJson(jsonElement, type);
	    return tablero;
	}
	
}
