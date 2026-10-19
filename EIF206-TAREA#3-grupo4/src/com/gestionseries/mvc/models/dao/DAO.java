package com.gestionseries.mvc.models.dao;

import java.util.ArrayList;

public interface DAO<T> {
	//obtener datos
	public ArrayList<T> all();
	public T find(int id);
	
	
	//ejecutar operaciones
	public boolean store(T item);
	public boolean update(T item, int id);
	public boolean destroy(int id);
	
	
	

}
