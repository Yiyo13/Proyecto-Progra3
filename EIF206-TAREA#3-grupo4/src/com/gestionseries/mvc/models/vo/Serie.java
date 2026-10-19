package com.gestionseries.mvc.models.vo;

public class Serie {
	private int id;
	private String titulo;
	private int cantTemporadas;
	private double calificacion;
	private int idGenero;
	private String nombreGenero;
	public Serie(int id, String titulo, int cantTemporadas, double calificacion, int idGenero, 
			String nombreGenero) {
		this.id = id;
		this.titulo = titulo;
		this.cantTemporadas = cantTemporadas;
		this.calificacion = calificacion;
		this.idGenero = idGenero;
		this.nombreGenero = nombreGenero;
	}
	public Serie( String titulo, int cantTemporadas, double calificacion, int idGenero) {
		this.id = 0;
		this.titulo = titulo;
		this.cantTemporadas = cantTemporadas;
		this.calificacion = calificacion;
		this.idGenero = idGenero;
		this.nombreGenero = null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getCantTemporadas() {
		return cantTemporadas;
	}
	public void setCantTemporadas(int cantTemporadas) {
		this.cantTemporadas = cantTemporadas;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getNombreGenero() {
		return nombreGenero;
	}
	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}
	@Override
	public String toString() {
		return "Serie [id=" + id + ", titulo=" + titulo + ", cantTemporadas=" + cantTemporadas + ", calificacion="
				+ calificacion + ", idGenero=" + idGenero + ", nombreGenero=" + nombreGenero + "]";
	}
	
	
	
	
	
	

}
