package jjBan.je.core;

import java.util.ArrayList;
import java.util.List;



public class Consulta {

	Ruta miRuta;
	static List<Ruta> rutasConsulta = new ArrayList<Ruta>();

	
	public static List<Ruta> consultaRutasPorActividadyDuracion (List<Ruta> misRutas, TipoActividad tipoActividades, int horas) {
		
			
		for (Ruta rutaActividad : misRutas ) {
			
			if (rutaActividad.getActividad().equals(tipoActividades) &
			(rutaActividad.getDatosTecnicos().getDuracion().getHour() >= (horas))) {
				rutasConsulta.add(rutaActividad);
			}		
		}
		return rutasConsulta;
		}

}
	
