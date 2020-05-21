package jjBan.je.core;

public class Suscripcion {

	String idActividad;
	String idUsuario;

	public String getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Suscripcion() {

	}

	public Suscripcion(String idActividad, String idUsuario) {
		super();
		this.idActividad = idActividad;
		this.idUsuario =  idUsuario;
	}


	@Override
	public String toString() {
		return "Suscripciones: Actividad: " + idActividad + "Usuario: " + idUsuario ;
	}
		
}
