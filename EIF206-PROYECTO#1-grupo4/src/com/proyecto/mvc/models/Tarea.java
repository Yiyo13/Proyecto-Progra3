package com.proyecto.mvc.models;

public class Tarea {
	private static int contador = 1;
	
	private int id;
	private String nombre;
	private String descripcionT;
	private boolean completada;
	private int idCategoria;
	
	public Tarea(String nombre, String descripcionT, boolean completada, int idCategoria) {
		
		this.id = contador++;
		this.nombre = nombre;
		this.descripcionT = descripcionT;
		this.completada = completada;
		this.idCategoria = idCategoria;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Tarea.contador = contador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcionT() {
		return descripcionT;
	}

	public void setDescripcionT(String descripcionT) {
		this.descripcionT = descripcionT;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "Tarea id=" + id + ", nombre=" + nombre + ", descripcionT=" + descripcionT + ", completada="
				+ completada + ", idCategoria=" + idCategoria + "]";
	}
	
	
	
	

}
