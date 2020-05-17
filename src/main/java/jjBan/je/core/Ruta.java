package jjBan.je.core;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import jjBan.je.exec.MainRutas;

public class Ruta implements Localizable, Comparable<Ruta>{

	@Enumerated(EnumType.STRING)
	private  TipoActividad actividad;
	@Enumerated(EnumType.STRING)
	private NivelDificultad nivelDificultad;
	private Date fecha;
	private String descripcion;
	private String nombre;
	private DatosTecnicos datosTecnicos;
	private String id;
	private String provincia;
	private String poblacion;

	public TipoActividad getActividad() {
		return actividad;
	}
	
	public void setActividad(TipoActividad actividad) {
		this.actividad = actividad;
	}

	public NivelDificultad getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(NivelDificultad nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DatosTecnicos getDatosTecnicos() {
		return datosTecnicos;
	}

	public void setDatosTecnicos(DatosTecnicos datosTecnicos) {
		this.datosTecnicos = datosTecnicos;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ruta() {
		
	}

	public Ruta(String provincia, String poblacion, TipoActividad actividad, NivelDificultad nivelDificultad, DatosTecnicos datosTecnicos, 
			Date fecha,	String descripcion, String nombre) {
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.actividad = actividad;
		this.nivelDificultad = nivelDificultad;
		this.datosTecnicos = datosTecnicos;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.id = MainRutas.generaId("ruta");
	}

	@Override
	public int compareTo(Ruta ruta) {
	
		return Float.compare(this.getDatosTecnicos().getKmsRecorridos(), 
				ruta.getDatosTecnicos().getKmsRecorridos());
		}
	
	@Override
	public String toString() {
		return " Ruta: " + getId() +". Actividad: " + getActividad() 
		+ ". Nivel de Dificultad: " + getNivelDificultad() + ". Poblacion: " + getPoblacion()
		+ ". " + getDatosTecnicos() + ". Provincia: " + getProvincia()+ ". Fecha: " + getFecha()
		+". Descripcion: " + getDescripcion() + ". Nombre: " + getNombre();
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

}
