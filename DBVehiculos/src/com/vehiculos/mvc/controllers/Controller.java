package com.vehiculos.mvc.controllers;

public interface Controller<T> {
	//VISTAS 
	public void index();
	public void create();
	public void edit(int id);
	public void show();
	
	public Object[][] getData();
	public String[] getColumns();
	
	//modelos
	
	public void store(T item);
	public void update(T item, int id);
	public void destroy(int id);
	

}
