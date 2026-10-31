package com.veterinaria.mvc.controllers;

public interface Controller<T> {
	//VISTAS
	public void index();
	public void create();
	public void edit(int id);
	public void show(int id);

	public Object[][] getData();
	public String[] getColumns();

	//MODELOS
	public void store(T item);
	public void update(T item, int id);
	public void destroy(int id);

}
