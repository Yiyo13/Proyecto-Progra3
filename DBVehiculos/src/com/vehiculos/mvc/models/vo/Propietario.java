package com.vehiculos.mvc.models.vo;

public class Propietario {
	private int id;
	private String nombre, telefono;
	private int edad;
	
	public Propietario(int id, String nombre, String telefono, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}
	public Propietario( String nombre, String telefono, int edad) {
		this.id = 0;
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	@Override
	public String toString() {
		return "Propietario [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", edad=" + edad + "]";
	}
	
	
	

}
