package jjBan.je.core;

import java.time.LocalTime;

public class DatosTecnicos {
	
	private LocalTime duracion;
	private int desnivel;
	private Float kmsRecorridos;
	private Float velocidadMedia;
	private Float velocidadMaxima;
	

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public int getDesnivel() {
		return desnivel;
	}

	public void setDesnivel(int desnivel) {
		this.desnivel = desnivel;
	}

	public Float getKmsRecorridos() {
		return kmsRecorridos;	
	}

	public void setKmsRecorridos(Float kmsRecorridos) {
		this.kmsRecorridos = kmsRecorridos;
	}


	public Float getVelocidadMedia() {
		return velocidadMedia;
	}


	public void setVelocidadMedia(Float velocidadMedia) {
		this.velocidadMedia = velocidadMedia;
	}


	public Float getVelocidadMaxima() {
		return velocidadMaxima;
	}


	public void setVelocidadMaxima(Float velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	public DatosTecnicos() {
	}
	
	public DatosTecnicos(int desnivel, LocalTime duracion, 
			Float kmsRecorridos, Float velocidadMedia, Float velocidadMaxima) {
		
		this.duracion = duracion;
		this.desnivel = desnivel;
		this.kmsRecorridos = kmsRecorridos;
		this.velocidadMedia = velocidadMedia;
		this.velocidadMaxima = velocidadMaxima;
	}

	@Override
	public String toString() {
		return "Duracion: " + getDuracion() + ". Desnivel: " + getDesnivel()
				+ ". Kms Recorridos: " + getKmsRecorridos() + ". Velocidad Media: " + getVelocidadMedia() 
	+ ". Velocidad Maxima: "+ getVelocidadMaxima();
	}
	
}
