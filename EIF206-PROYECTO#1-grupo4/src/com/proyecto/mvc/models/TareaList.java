package com.proyecto.mvc.models;

import java.util.ArrayList;

public class TareaList {

	private ArrayList<Tarea> list;

	public TareaList() {
		list = new ArrayList<>();
	}

	// OPERACIONES

	public void store(Tarea item) {
		list.add(item);
	}

	public void update(Tarea updated, int id) {
		Tarea item = find(id);
		if (item != null) {
			item.setNombre(updated.getNombre());
			item.setDescripcionT(updated.getDescripcionT());
			item.setCompletada(updated.isCompletada());
			item.setIdCategoria(updated.getIdCategoria());
		}
	}

	public void destroy(int id) {
		Tarea item = find(id);
		if (item != null) {
			list.remove(item);
		}
	}

	public void marcarCompletada(int id) {
		Tarea item = find(id);
		if (item != null) {
			item.setCompletada(true);
		}
	}

	// CONSULTAS

	public Tarea find(int id) {
		return list
				.stream()
				.filter(v -> v.getId() == id)
				.findFirst()
				.orElse(null);
	}

	public ArrayList<Tarea> all() {
		return list;
	}

	public int countByIdCategoria(int idCategoria) {

		int count = 0;

		for (Tarea item : list) {
			if(item.getIdCategoria() == idCategoria) {
				count++;
			}
		}

		return count;

	}

	public ArrayList<Tarea> pendientesPorCategoria(int idCategoria) {
		ArrayList<Tarea> result = new ArrayList<>();
		for (Tarea item : list) {
			if (item.getIdCategoria() == idCategoria && !item.isCompletada()) {
				result.add(item);
			}
		}
		return result;
	}

	public ArrayList<Tarea> completadasPorCategoria(int idCategoria) {
		ArrayList<Tarea> result = new ArrayList<>();
		for (Tarea item : list) {
			if (item.getIdCategoria() == idCategoria && item.isCompletada()) {
				result.add(item);
			}
		}
		return result;
	}

}