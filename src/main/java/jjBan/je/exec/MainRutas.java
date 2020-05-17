
package jjBan.je.exec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jjBan.je.core.Actividad;
import jjBan.je.core.Consulta;
import jjBan.je.core.DatosTecnicos;
import jjBan.je.core.Localizable;
import jjBan.je.core.NivelDificultad;
import jjBan.je.core.Ruta;
import jjBan.je.core.Suscripcion;
import jjBan.je.core.TipoActividad;
import jjBan.je.repositorios.UsuariosDAO;
import jjBan.je.user.Usuario;

public class MainRutas {

	private static final String SEPARADOR = ",";

	public static void main(String[] args) {

//		CREO  DATOS TECNICOS
		DatosTecnicos misDatosTecnicos = new DatosTecnicos(2, LocalTime.of(2, 20, 20), 65.0f, 23.2f, 25.5f);
		
//		CREO RUTAS
		TipoActividad senderismo = TipoActividad.SENDERISMO;
		NivelDificultad bajo =NivelDificultad.FACIL;
		
		Ruta miRuta = new Ruta("Toledo", "Urda", senderismo, bajo, misDatosTecnicos, 
				new Date(), "Vaya ruta","globerada");
		Ruta mi2Ruta = new Ruta("Madrid", "Hoyo Manzanares", TipoActividad.BICI_CARRETERA, 
				NivelDificultad.DIFICIL, misDatosTecnicos,
				new Date(), "Vaya ruta", "globerada");
		Ruta mi3Ruta = new Ruta("Ciudad Real", "Almagro", TipoActividad.MOUNTAIN_BIKE,
				NivelDificultad.FACIL, misDatosTecnicos,
				new Date(), "Vaya ruta", "globerada");
		
//		CREO USUARIOS		
		Usuario usuario1 = new Usuario("Pepe", "pepe@hotmail.com", generaId("login"), generaPass());
		Usuario admin1 = new Usuario("Luis", "luis@hotmail.com", generaId("login"), generaPass());
		Usuario usuario2 = new Usuario("Rosa", "rosa@hotmail.com", generaId("login"), generaPass());
		
//		CREO LISTA DE USUARIOS
		
		List<Usuario> misUser = new ArrayList<>();
		misUser.add(usuario2);
		misUser.add(admin1);
		misUser.add(usuario1);

//		ORDENO LA LISTA DE USUARIOS POR NOMBRE Y LAS IMPRIMO
		Collections.sort(misUser, usuario1);
		misUser.forEach(System.out::println);
		
//		CREO LISTA DE RUTAS
		List<Ruta> misRutas = new ArrayList<>();
		misRutas.add(miRuta);
		misRutas.add(mi2Ruta);
		misRutas.add(mi3Ruta);
		
		misRutas.forEach(System.err::println);
		
//		CREO LAS ACTIVIDADES
		Actividad misActividad1 = new Actividad(new Date(),TipoActividad.SENDERISMO, "El Pozo");
		misActividad1.setUsuarios(misUser);
		Actividad misActividad2 = new Actividad(new Date(),TipoActividad.MOUNTAIN_BIKE, "La Huerta");
		Actividad misActividad3 = new Actividad(new Date(),TipoActividad.SENDERISMO, "Siete leguas");
		
//		CREO LISTA DE ACTIVIDADES
		List<Actividad> misActividades = new ArrayList<>();
		misActividades.add(misActividad1);
		misActividades.add(misActividad2);
		misActividades.add(misActividad3);
		misActividades.forEach(System.out::println);

//		AGREGO LAS RUTAS DEL FICHERO A MI LISTA DE RUTAS.
		misRutas.addAll(importarCSVStrava("./data/activities3.csv"));
		
//		ORDENO LA LISTA DE RUTAS POR KMS RECORRIDOS Y LAS IMPRIMO
		Collections.sort(misRutas);
//		misRutas.forEach(System.out::println);
		System.out.println();
		
//		CONSULTO LAS RUTAS DE MOUNTANBIKE DE MÁS DE 2 HORAS DE DURACION Y LAS IMPRIMO
		Consulta.consultaRutasPorActividadyDuracion(misRutas, TipoActividad.MOUNTAIN_BIKE, 2).forEach(System.out::println);
	 
	}

	public static String generaId(String uso) {
		int NUMERO = 1000;
		return uso + ThreadLocalRandom.current().nextInt(NUMERO, NUMERO * 20);
	}
	
	public static String generaPass() {
		 SecureRandom random = new SecureRandom();
		 return new BigInteger(50, random).toString(32);
		 
	}
	
	public static List<Ruta> importarCSVStrava(String pathString) {
		
		List<Ruta> rutasDeStrava = new ArrayList<>();
		
		BufferedReader bufferLectura = null;
		Ruta rutaStrava;

		try {
			// ABRIR EL .CSV EN BUFFER DE LECTURA
			bufferLectura = new BufferedReader(new FileReader(pathString));

			// LEER UNA LINEA DEL ARCHIVO
			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			while (linea != null) {
				
				// SEPARAR LA LINEA LEIDA CON EL SEPARADOR DEFINIDO PREVIAMENTE
				String[] datosStrava = linea.split(SEPARADOR);

				rutaStrava = new Ruta(
						"undefined", "undefined", TipoActividad.MOUNTAIN_BIKE, NivelDificultad.FACIL,  
						new DatosTecnicos(200, conversorSegundosFomato(Integer.parseInt(datosStrava[5])), 
								Float.parseFloat(datosStrava[6]), 20.0f, 20.f), conversorFecha(datosStrava[1]), 
								datosStrava[4], datosStrava[2]);
				
				rutasDeStrava.add(rutaStrava);
				linea = bufferLectura.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// CIERRO EL BUFFER DE LECTURA
			if (bufferLectura != null) {
				try {
					bufferLectura.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return rutasDeStrava;
	}

	
//	CONVERSOR DE STRING DEL CSV A FECHA
	
	public static Date conversorFecha(String fecha) {

		DateFormat format = new SimpleDateFormat("dd MMMM. yyyy HH:mm:ss");
		Date date = new Date();
		try {
			date = format.parse(fecha);//17 oct. 2019 14:06:48
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		return date;
	}

	
//	CONVERSOR DE SEGUNDOS A LOCALTIME
	public static LocalTime conversorSegundosFomato(int segundos) {
		return LocalTime.ofSecondOfDay(segundos);
	}
	
}
