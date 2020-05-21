package jjBan.je.jjbanjebiciapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import jjBan.je.core.Actividad;
import jjBan.je.core.DatosTecnicos;
import jjBan.je.core.NivelDificultad;
import jjBan.je.core.Ruta;
import jjBan.je.core.TipoActividad;
import jjBan.je.repositorios.ActividadDAO;
import jjBan.je.repositorios.RutasDAO;
import jjBan.je.repositorios.UsuariosDAO;
import jjBan.je.rest.MixIns;
import jjBan.je.user.Usuario;

@PropertySource({"classpath:config/rest.properties", "classpath:config/jackson.properties"})
@SpringBootApplication
@ImportResource({ "classpath:config/jpa-config.xml" })
public class JjbanjebiciapiApplication {

	private static final Logger log = LoggerFactory.getLogger(JjbanjebiciapiApplication.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(JjbanjebiciapiApplication.class, args);

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Ruta.class, MixIns.Rutas.class);
		mapper.addMixIn(DatosTecnicos.class, MixIns.DatosTecnicos.class);
		mapper.addMixIn(Actividad.class, MixIns.Actividad.class);

		RutasDAO rutasDAO = context.getBean(RutasDAO.class);
		SerialJSON(mapper, rutasDAO, "./data/activities7.json");

		rutasDAO.save(addRutaConDatosTecnicos("Ciudad Real", "Granatula", TipoActividad.SENDERISMO,
				NivelDificultad.FACIL, addDatosTecnicos(), new Date(), "Globerada", "Ciudad RealLiteral - Granatula"));

		// datosTecnicosDAO.findAll().stream().map(DatosTecnicos::toString).forEach(log::trace);
		rutasDAO.findByNombreContainingIgnoreCase("Zur").stream().map(Ruta::toString).forEach(log::info);

		ActividadDAO actividadDAO = context.getBean(ActividadDAO.class);
		UsuariosDAO usuariosDAO = context.getBean(UsuariosDAO.class);
		
		Actividad actividad1 = addActividad(TipoActividad.MOUNTAIN_BIKE, "La Fuente");
		Usuario usuario1 = anadirUsuario();
		
		usuariosDAO.save(usuario1);
		actividadDAO.save(actividad1);
		usuariosDAO.save(addSusucripcion(actividad1, usuario1));
		actividadDAO.findAll().stream().map(Actividad::toString).forEach(log::trace);
		
		

//		   context.close();
		
	}

	static void SerialJSON(ObjectMapper mapper, RutasDAO rutasDAO, String fichero// , DatosTecnicosDAO datosTecnicosDAO
	) {

		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			Ruta ruta; // = new Ruta();
			DatosTecnicos datosTecnicos;// = ruta.getDatosTecnicos();
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("{") && linea.endsWith("}")) {
					ruta = (mapper.readValue(linea, Ruta.class));
					datosTecnicos = (mapper.readValue(linea, DatosTecnicos.class));
					System.out.println(datosTecnicos);
					ruta.setDatosTecnicos(datosTecnicos);
					rutasDAO.save(ruta);

				}
			}

//			System.err.println(datosTecnicos);
			// datosTecnicosDAO.saveAll(datosTecnicos);

		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	static Usuario anadirUsuario() {

		return new Usuario("Luis", "luis@hotmail.com", "admin", generaPass());
	}

	static String generaPass() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(50, random).toString(32);

	}

	static DatosTecnicos addDatosTecnicos() {
		return new DatosTecnicos(200, LocalTime.of(2, 20, 20), 65.0f, 23.2f, 25.5f);

	}

	static Usuario addSusucripcion(Actividad actividad, Usuario usuario) {
				 usuario.setActividad(actividad);
				 return usuario;
						
	}
	static Actividad addActividad(TipoActividad actividad, String nombre) {
		return new Actividad(new Date(), actividad, nombre);

	}

	static Ruta addRuta() {
		return new Ruta();

	}

	static Ruta addRutaConDatosTecnicos(String provincia, String poblacion, TipoActividad actividad,
			NivelDificultad nivelDificultad, DatosTecnicos datosTecnicos, Date fecha, String descripcion,
			String nombre) {
		return new Ruta(provincia, poblacion, actividad, nivelDificultad, datosTecnicos, fecha, descripcion, nombre);
	}
}
