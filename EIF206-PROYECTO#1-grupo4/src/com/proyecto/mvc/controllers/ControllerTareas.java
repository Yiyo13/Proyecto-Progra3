package com.proyecto.mvc.controllers;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.proyecto.mvc.models.Categoria;
import com.proyecto.mvc.models.CategoriaList;
import com.proyecto.mvc.models.Tarea;
import com.proyecto.mvc.models.TareaList;
import com.proyecto.mvc.views.ViewPrincipal;
import com.proyecto.mvc.views.sistema.Form;
import com.proyecto.mvc.views.sistema.Index;

public class ControllerTareas extends Functions {

	private TareaList tareaList;
	private CategoriaList categoriaList;
	private ViewPrincipal vp;

	public ControllerTareas(TareaList tareaList, CategoriaList categoriaList, ViewPrincipal vp) {
		this.tareaList = tareaList;
		this.categoriaList = categoriaList;
		this.vp = vp;
	}

	// VISTAS

	public void indexPendientes() {

		Index v = new Index();
		cargarComboCategorias(v.cbxCategoria);

		v.btnCargar.addActionListener(e -> {
			Categoria cat = (Categoria) v.cbxCategoria.getSelectedItem();
			if (cat != null) {
				cargarTabla(v, tareaList.pendientesPorCategoria(cat.getId()));
			}
		});

		v.btnGuardar.addActionListener(e -> create());

		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id > 0) {
				edit(id);
			}
		});

		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id > 0) {
				int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la tarea?");
				if (option == JOptionPane.YES_OPTION) {
					tareaList.destroy(id);
					indexPendientes();
				}
			}
		});

		v.btnCompletar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id > 0) {
				tareaList.marcarCompletada(id);
				indexPendientes();
			}
		});

		vp.setContenido(v, "Tareas - Pendientes");
	}

	public void indexCompletadas() {

		Index v = new Index();
		cargarComboCategorias(v.cbxCategoria);

		v.btnCargar.addActionListener(e -> {
			Categoria cat = (Categoria) v.cbxCategoria.getSelectedItem();
			if (cat != null) {
				cargarTabla(v, tareaList.completadasPorCategoria(cat.getId()));
			}
		});

		v.btnGuardar.setVisible(false);
		v.btnEditar.setVisible(false);
		v.btnEliminar.setVisible(false);
		v.btnCompletar.setVisible(false);

		vp.setContenido(v, "Tareas - Completadas");
	}

	public void create() {

		Form v = new Form();
		cargarComboCategorias(v.cbxCategoria);

		v.btnGuardar.addActionListener(e -> {

			String nombre = v.tNombre.getText();
			String descripcion = v.taDescripcion.getText();
			Categoria cat = (Categoria) v.cbxCategoria.getSelectedItem();

			if (nombre == null || nombre.trim().isEmpty() || cat == null) {
				JOptionPane.showMessageDialog(null, "Debe indicar nombre y categoria.");
				return;
			}

			Tarea item = new Tarea(nombre, descripcion, false, cat.getId());
			tareaList.store(item);

			indexPendientes();
		});

		v.btnCancelar.addActionListener(e -> indexPendientes());

		vp.setContenido(v, "Tareas - Registrar");
	}

	public void edit(int id) {

		Tarea item = tareaList.find(id);
		if (item == null) return;

		Form v = new Form();
		cargarComboCategorias(v.cbxCategoria);

		v.tNombre.setText(item.getNombre());
		v.taDescripcion.setText(item.getDescripcionT());
		seleccionarCategoriaEnCombo(v.cbxCategoria, item.getIdCategoria());

		v.btnGuardar.addActionListener(e -> {

			String nombre = v.tNombre.getText();
			String descripcion = v.taDescripcion.getText();
			Categoria cat = (Categoria) v.cbxCategoria.getSelectedItem();

			if (nombre == null || nombre.trim().isEmpty() || cat == null) {
				JOptionPane.showMessageDialog(null, "Debe indicar nombre y categoria.");
				return;
			}

			Tarea updated = new Tarea(nombre, descripcion, item.isCompletada(), cat.getId());
			tareaList.update(updated, id);

			indexPendientes();
		});

		v.btnCancelar.addActionListener(e -> indexPendientes());

		vp.setContenido(v, "Tareas - Editar");
	}

	//AUXILIARES

	private void cargarComboCategorias(JComboBox<Categoria> combo) {
		combo.removeAllItems();
		for (Categoria c : categoriaList.all()) {
			combo.addItem(c);
		}
	}

	private void seleccionarCategoriaEnCombo(JComboBox<Categoria> combo, int idCategoria) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).getId() == idCategoria) {
				combo.setSelectedIndex(i);
				break;
			}
		}
	}

	private void cargarTabla(Index v, ArrayList<Tarea> tareas) {
		Object[][] data = new Object[tareas.size()][3];
		for (int i = 0; i < tareas.size(); i++) {
			Tarea t = tareas.get(i);
			data[i][0] = t.getId();
			data[i][1] = t.getNombre();
			data[i][2] = t.getDescripcionT();
		}
		v.model.setDataVector(data, new String[] { "ID", "Nombre", "Descripcion" });
	}

}