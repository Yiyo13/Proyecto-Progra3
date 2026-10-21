package com.vehiculos.mvc.models.vo;

public class Vehiculo {
	private int id;
	private String placa;
	private int idPropietario;
	private Propietario propietario;
	
	
	public Vehiculo(int id, String placa, int idPropietario, Propietario propietario) {
		this.id = id;
		this.placa = placa;
		this.idPropietario =  idPropietario;
		this.propietario = propietario;
	}
	public Vehiculo(String placa, Propietario propietario) {
		this.id = 0;
		this.placa = placa;
		this.propietario = null;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	public int getIdPropietario() {
		return idPropietario;
	}
	public void setNombrePropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}
	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", placa=" + placa 
				+ ", propietario=" + propietario + ", idPropietario=" + idPropietario + "]";
	}
	
	
	
	

}
