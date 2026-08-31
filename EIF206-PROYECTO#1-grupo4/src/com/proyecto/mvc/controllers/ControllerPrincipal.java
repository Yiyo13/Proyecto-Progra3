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
	// TODO: cuando exista ControllerCategorias, agregar:
	// private ControllerCategorias controllerCategorias;

	public ControllerPrincipal() {
		categoriaList = new CategoriaList();
		tareaList = new TareaList();
		vp = new ViewPrincipal();
	}

	public void init() {

		loadData();

		controllerTareas = new ControllerTareas(tareaList, categoriaList, vp);
		// TODO: cuando exista ControllerCategorias, agregar:
		// controllerCategorias = new ControllerCategorias(categoriaList, vp);

		vp.btnTareasPendientes.addActionListener(e -> controllerTareas.indexPendientes());
		vp.btnTareasCompletadas.addActionListener(e -> controllerTareas.indexCompletadas());

		// TODO: cuando exista ControllerCategorias, agregar:
		// vp.btnCategoras.addActionListener(e -> controllerCategorias.index());

		vp.init();

		controllerTareas.indexPendientes();
	}

	private void loadData() {

		// Categorias primero, porque las tareas necesitan un idCategoria que ya exista
		Categoria trabajo = new Categoria("Trabajo");
		Categoria personal = new Categoria("Personal");
		Categoria universidad = new Categoria("Universidad");

		categoriaList.store(trabajo);
		categoriaList.store(personal);
		categoriaList.store(universidad);

		// 10 tareas por categoria (algunas ya completadas), minimo 30 en total
		for (int i = 1; i <= 10; i++) {
			boolean completada = (i % 3 == 0);
			tareaList.store(new Tarea("Tarea Trabajo " + i, "Descripcion de la tarea " + i, completada, trabajo.getId()));
		}

		for (int i = 1; i <= 10; i++) {
			boolean completada = (i % 4 == 0);
			tareaList.store(new Tarea("Tarea Personal " + i, "Descripcion de la tarea " + i, completada, personal.getId()));
		}

		for (int i = 1; i <= 10; i++) {
			boolean completada = (i % 5 == 0);
			tareaList.store(new Tarea("Tarea Universidad " + i, "Descripcion de la tarea " + i, completada, universidad.getId()));
		}

	}

}