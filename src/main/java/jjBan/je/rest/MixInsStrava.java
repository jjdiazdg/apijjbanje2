package jjBan.je.rest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class MixInsStrava {
	
	@JsonIgnoreProperties(value = {"id", "nivelDificultad", //"duracion",
			"fecha", "nombre", "actividad",
			"desplazamiento","descripcion","equipacion","archivo" })
	public static interface DatosTecnicos {
	
	@JsonProperty("duracion")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
	@JsonFormat(pattern = "hh:mm:ss")
		abstract LocalTime getDuracion(); 
	
	}
	
	@JsonIgnoreProperties(value = {"kmsRecorridos", "duracion", "nivelDificultad",
	 "actividad", "desplazamiento","equipacion","archivo" })
	public static interface Rutas {
		@JsonFormat(pattern = "dd MMM. yyyy hh:mm:ss")
		abstract LocalDateTime getFecha();
		
	}
	
	@JsonIgnoreType // Ignora un tipo por completo
	public static interface IgnorarTipo {}
	
}
