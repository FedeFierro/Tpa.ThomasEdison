package serializable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
	public boolean iniciado;
	public String mensaje;
	public List<JugadorInfo> jugadoresInfo;
	public List<ElementoInfo> elementos;
	
	public TableroInfo(int puntosPartida) {
		this.iniciado=false;
		this.mensaje= "Esperando Conexion de jugadores...";
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
				
		JsonObject resultado = new JsonObject();
		resultado.add("iniciado", new JsonPrimitive(iniciado));
		resultado.add("mensaje", new JsonPrimitive(mensaje));
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
		Gson gson = new Gson();
	    TableroInfo tablero = gson.fromJson(jsonElement, type);
	 JsonObject jo = jsonElement.getAsJsonObject();
	 
	 JsonArray ja =jo.getAsJsonArray("jugadoresInfo");
	 System.out.println(ja);
	    for(JsonElement je : ja) {
	    	tablero.jugadoresInfo.add(new Gson().fromJson(je, JugadorInfo.class));
	    	
		 }
	     ja =jo.getAsJsonArray("elementos");
	     for(JsonElement je : ja) {
		    	tablero.elementos.add(new Gson().fromJson(je, ElementoInfo.class));
		    	
			 } 
		System.out.println(ja);
	    return tablero;
	}
	
	public void copyData(TableroInfo tablero) {
		this.iniciado = tablero.iniciado;
		this.mensaje= tablero.mensaje;
		this.nivel = tablero.nivel;
		this.tiempo =  tablero.tiempo;
		this.imagen =  tablero.imagen;
		this.pausa = tablero.pausa;
		this.puntosPartida =  tablero.puntosPartida;
		this.ganador = tablero.ganador;
		this.finJuego = tablero.finJuego;
		this.sonido = tablero.sonido;
		this.elementos=tablero.elementos;
		this.jugadoresInfo = tablero.jugadoresInfo;
	}
	
}
