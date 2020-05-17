package jjBan.je.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import jjBan.je.exec.MainRutas;
import jjBan.je.user.Usuario;

public class Actividad {

	private String id;
	private Date fecha;
	@Enumerated(EnumType.STRING)
	TipoActividad tipoActividad;
	private String nombreActividad;
	List<Usuario> usuarios;

	
	public String getId() {
		return id;
	}
	@OneToMany(targetEntity=Usuario.class)
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public Actividad() {
		
	}
	public Actividad(Date fecha, TipoActividad actividad, String nombreActividad) {
		super();
		this.fecha = fecha;
		this.tipoActividad = actividad;
		this.nombreActividad = nombreActividad;
		this.id =  MainRutas.generaId("activ");
		setUsuarios(new ArrayList<>());
	}

	public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        if (usuario.getActividad()!= this) {
            usuario.setActividad(this);
        }
    }

	@Override
	public String toString() {
		return "Actividad: " + getTipoActividad() 
		+ ". Fecha: " + getFecha() + ". Nombre: " + getNombreActividad() + ". Suscriptores: " 
		+ getUsuarios();
	}

}
