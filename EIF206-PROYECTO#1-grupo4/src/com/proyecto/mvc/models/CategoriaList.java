package com.proyecto.mvc.models;

import java.util.ArrayList;

public class CategoriaList {

	private ArrayList<Categoria> list;

	public CategoriaList() {
		list = new ArrayList<>();
	}

	// OPERACIONES

	public void store(Categoria item) {
		list.add(item);
	}

	public void update(Categoria updated, int id) {
		Categoria item = find(id);
		if (item != null) {
			item.setNombre(updated.getNombre());
		}
	}

	public void destroy(int id) {
		Categoria item = find(id);
		if (item != null) {
			list.remove(item);
		}
	}

	// CONSULTAS

	public Categoria find(int id) {
		return list
				.stream()
				.filter(c -> c.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public ArrayList<Categoria> all() {
		return list;
	}

}