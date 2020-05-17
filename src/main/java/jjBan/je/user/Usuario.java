package jjBan.je.user;

import java.util.Comparator;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jjBan.je.core.Actividad;
import jjBan.je.exec.MainRutas;

public class Usuario implements Comparator<Usuario> {

	private String id;
	private String nombre;
	private String mail;
	private String login;
	@JsonIgnore
	private String password;

//	@JsonIgnore
	@ManyToOne
	private Actividad actividad;
	
	
	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
		 if (!actividad.getUsuarios().contains(this)) {
	            actividad.getUsuarios().add(this);
		 }
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario() {}
	
	public Usuario(String nombre, String mail, String login, String password) {

		this.id =  MainRutas.generaId("user");
		this.nombre = nombre;
		this.mail = mail;
		this.login = login;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Usuario: " + getNombre() + ", Id: " + getId() + ", mail: "
	+ getMail();
	}

	@Override
	public int compare(Usuario usuario1, Usuario usuario2) {
		return usuario1.getNombre().compareTo(usuario2.getNombre());
	
	}

}
