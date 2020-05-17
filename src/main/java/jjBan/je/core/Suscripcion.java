package jjBan.je.core;

import java.util.List;

import jjBan.je.exec.MainRutas;
import jjBan.je.user.Usuario;


public class Suscripcion {

	Actividad actividad;
	private String id;
//	List<Usuario> usuarios;

	public String getId() {
		return id;
	}
	
	

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	

//	public List<Usuario> getUsuarios() {
//		return usuarios;
//	}
//
//
//
//	public void setUsuarios(List<Usuario> usuarios) {
//		this.usuarios = usuarios;
//	}


	public Suscripcion() {

	}

//	public Suscripcion(Actividad actividad, List<Usuario> usuarios) {
//		super();
//		this.actividad = actividad;
//		this.id =  MainRutas.generaId("sus");
//		this.usuarios = usuarios;
//	}

	public Suscripcion(Actividad actividad) {
		super();
		this.actividad = actividad;
		this.id =  MainRutas.generaId("sus");
	}


	@Override
	public String toString() {
		return "Suscripcion: " + ", " + actividad;
	}
		
}
