package com.veterinaria.mvc.models.vo;

import java.sql.Date;

public class Mascota {
	private int id;
	private String nombre;
	private String especie;
	private String raza;
	private Date fechaNacimiento;
	private Propietario propietario;

	
	public Mascota(int id, String nombre, String especie, String raza, Date fechaNacimiento,
			Propietario propietario) {
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.fechaNacimiento = fechaNacimiento;
		this.propietario = propietario;
	}

	
	public Mascota(String nombre, String especie, String raza, Date fechaNacimiento, Propietario propietario) {
		this.id = 0;
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.fechaNacimiento = fechaNacimiento;
		this.propietario = propietario;
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

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	//este metodo calcula la edad en base a la fecha actual
	public int getEdad() {
		return java.time.Period.between(fechaNacimiento.toLocalDate(), java.time.LocalDate.now()).getYears();
	}

	
	@Override
	public String toString() {
		return nombre;
	}
}