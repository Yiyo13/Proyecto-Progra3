package com.proyecto.mvc.controllers;

import com.proyecto.mvc.models.Categoria;
import com.proyecto.mvc.models.CategoriaList;
import com.proyecto.mvc.models.Tarea;
import com.proyecto.mvc.models.TareaList;
import com.proyecto.mvc.views.ViewPrincipal;

public class ControllerPrincipal {

	private CategoriaList categoriaList;
	private TareaList tareaList;
	private ViewPrincipal vp;

	private ControllerTareas controllerTareas;
	private ControllerCategorias controllerCategorias;

	public ControllerPrincipal() {
		categoriaList = new CategoriaList();
		tareaList = new TareaList();
		vp = new ViewPrincipal();
	}

	public void init() {

		loadData();

		controllerTareas = new ControllerTareas(tareaList, categoriaList, vp);
		controllerCategorias = new ControllerCategorias(categoriaList, tareaList, vp);

		vp.btnTareasPendientes.addActionListener(e -> controllerTareas.indexPendientes());
		vp.btnTareasCompletadas.addActionListener(e -> controllerTareas.indexCompletadas());
		vp.btnCategoras.addActionListener(e -> controllerCategorias.index());

		vp.init();

		controllerTareas.indexPendientes();
	}

	private void loadData() {

		// Categorias primero, porque las tareas necesitan un idCategoria que ya exista
		Categoria trabajo = new Categoria("Trabajo");
		Categoria personal = new Categoria("Personal");
		Categoria universidad = new Categoria("Universidad");
		Categoria pesca = new Categoria("Pesca");

		categoriaList.store(trabajo);
		categoriaList.store(personal);
		categoriaList.store(universidad);
		categoriaList.store(pesca);

		// 10 tareas por categoria, entre 2 y 6 completadas por categoria
		cargarTareasDePrueba(trabajo, 4);
		cargarTareasDePrueba(personal, 6);
		cargarTareasDePrueba(universidad, 2);
		cargarTareasDePrueba(pesca, 8);

	}

	private void cargarTareasDePrueba(Categoria categoria, int cantidadCompletadas) {

		for (int i = 1; i <= 10; i++) {
			boolean completada = (i <= cantidadCompletadas);
			tareaList.store(new Tarea(
					"Tarea " + categoria.getNombre() + " " + i,
					"Descripcion de la tarea " + i,
					completada,
					categoria.getId()));
		}

	}

}