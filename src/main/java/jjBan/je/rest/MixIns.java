package jjBan.je.rest;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public abstract class MixIns {
	
	@JsonIgnoreProperties(value = {"id", "nivelDificultad", "provincia",
			"fecha", "nombre", "actividad", "poblacion","descripcion" })
	public static interface DatosTecnicos {
	
	@JsonProperty("duracion")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
	@JsonFormat(pattern = "hh:mm:ss")
		abstract LocalTime getDuracion(); 
	
	}
	
	@JsonIgnoreProperties(value = {"kmsRecorridos", "duracion", "velocidadMedia",
	 "velocidadMaxima", "desnivel" })
	public static interface Rutas {
		
		@JsonProperty("fecha")
		@JsonFormat(pattern = "dd MMM. yyyy hh:mm:ss")
		abstract LocalDateTime getFecha();
		
	}
	
public static interface Actividad {
		
		@JsonProperty("fecha")
		@JsonDeserialize(using = LocalTimeDeserializer.class)
	    @JsonSerialize(using = LocalTimeSerializer.class)
		@JsonFormat(pattern = "hh:mm:ss")
		abstract LocalDateTime getFecha();
		
	}
	
	@JsonIgnoreType // Ignora un tipo por completo
	public static interface IgnorarTipo {}
	
}
