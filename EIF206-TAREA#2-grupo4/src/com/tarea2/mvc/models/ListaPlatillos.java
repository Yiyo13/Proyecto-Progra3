package com.tarea2.mvc.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPlatillos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Platillo> platillos;
	
	public ListaPlatillos() {
		platillos = new ArrayList<>();
		
	}
	
	public void agregarPlatillo(Platillo platillo) {
		platillos.add(platillo);
	}
	public void eliminarPlatillo(int indice) {
		platillos.remove(indice);
		
	}
	public Platillo obtener(int indice) {
		return platillos.get(indice);
	}
	public ArrayList<Platillo> getPlatillos(){
		return platillos;
	}
	public double getTotal() {
		double total = 0;
		
		for (Platillo platillo : platillos) {
			total += platillo.getPrecioPlatillo();
		}
		return total;
	}	
	
}
