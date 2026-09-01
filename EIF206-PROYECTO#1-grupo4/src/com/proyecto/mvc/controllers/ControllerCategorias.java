package com.proyecto.mvc.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.proyecto.mvc.models.Categoria;
import com.proyecto.mvc.models.CategoriaList;
import com.proyecto.mvc.models.Tarea;
import com.proyecto.mvc.models.TareaList;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.sistema.FormCategoria;
import com.proyecto.mvc.views.sistema.IndexCategorias;

public class ControllerCategorias extends Functions {

	private CategoriaList categoriaList;
	private TareaList tareaList;
	private ViewPrincipal vp;

	public ControllerCategorias(CategoriaList categoriaList, TareaList tareaList, ViewPrincipal vp) {
		this.categoriaList = categoriaList;
		this.tareaList = tareaList;
		this.vp = vp;
	}

	// VISTAS

	public void index() {

		IndexCategorias v = new IndexCategorias();
		cargarTabla(v);

		v.btnNuevo.addActionListener(e -> create());

		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id > 0) {
				edit(id);
			}
		});

		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id > 0) {

				ArrayList<Tarea> pendientes = tareaList.pendientesPorCategoria(id);
				if (!pendientes.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"No se puede eliminar. La categoria tiene tareas pendientes.");
					return;
				}

				int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la categoria?");
				if (option == JOptionPane.YES_OPTION) {
					categoriaList.destroy(id);
					index();
				}
			}
		});

		vp.setContenido(v, "Categorias - Lista");
	}

	public void create() {

		FormCategoria v = new FormCategoria();

		v.btnGuardar.addActionListener(e -> {

			String nombre = v.tNombre.getText();

			if (nombre == null || nombre.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe indicar el nombre.");
				return;
			}

			Categoria item = new Categoria(nombre);
			categoriaList.store(item);

			index();
		});

		v.btnCancelar.addActionListener(e -> index());

		vp.setContenido(v, "Categorias - Registrar");
	}

	public void edit(int id) {

		Categoria item = categoriaList.find(id);
		if (item == null) return;

		FormCategoria v = new FormCategoria();
		v.tNombre.setText(item.getNombre());

		v.btnGuardar.addActionListener(e -> {

			String nombre = v.tNombre.getText();

			if (nombre == null || nombre.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe indicar el nombre.");
				return;
			}

			categoriaList.update(new Categoria(nombre), id);

			index();
		});

		v.btnCancelar.addActionListener(e -> index());

		vp.setContenido(v, "Categorias - Editar");
	}

	// AUXILIARES

	private void cargarTabla(IndexCategorias v) {
		ArrayList<Categoria> categorias = categoriaList.all();
		Object[][] data = new Object[categorias.size()][2];
		for (int i = 0; i < categorias.size(); i++) {
			Categoria c = categorias.get(i);
			data[i][0] = c.getId();
			data[i][1] = c.getNombre();
		}
		v.model.setDataVector(data, new String[] { "ID", "Nombre" });
	}

}