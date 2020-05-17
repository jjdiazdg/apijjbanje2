package jjBan.je.rest;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class LocalDateDeserializer extends StdDeserializer<LocalDate>{

	public LocalDateDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		try {
			return LocalDate.parse(p.readValueAs(String.class));
		} catch (Exception e) {
			return null;
		}
		
	}
}
