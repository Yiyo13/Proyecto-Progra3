package com.tarea2.mvc.models;

import java.io.Serializable;

public class Platilo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static int contador = 1;
	private int idPlatilo;
	private String nombrePlatillo;
	private double precioPlatillo;
	
	
	
	public Platilo(int idPlatilo, String nombrePlatillo, double precioPlatillo) {
		this.idPlatilo = contador++;
		this.nombrePlatillo = nombrePlatillo;
		this.precioPlatillo = precioPlatillo;
	}
	public static int getContador() {
		return contador;
	}
	public static void setContador(int contador) {
		Platilo.contador = contador;
	}
	public int getIdPlatilo() {
		return idPlatilo;
	}
	public void setIdPlatilo(int idPlatilo) {
		this.idPlatilo = idPlatilo;
	}
	public String getNombrePlatillo() {
		return nombrePlatillo;
	}
	public void setNombrePlatillo(String nombrePlatillo) {
		this.nombrePlatillo = nombrePlatillo;
	}
	public double getPrecioPlatillo() {
		return precioPlatillo;
	}
	public void setPrecioPlatillo(double precioPlatillo) {
		this.precioPlatillo = precioPlatillo;
	}
	@Override
	public String toString() {
		return "Platilo idPlatilo=" + idPlatilo + ", nombrePlatillo=" + nombrePlatillo + ", precioPlatillo="
				+ precioPlatillo ;
	}
	
	
	

}
