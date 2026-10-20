package com.gestionseries.mvc.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.gestionseries.mvc.models.dao.DAOGenero;
import com.gestionseries.mvc.models.dao.DAOSerie;
import com.gestionseries.mvc.models.vo.Genero;
import com.gestionseries.mvc.models.vo.Serie;
import com.gestionseries.mvc.views.series.Card;
import com.gestionseries.mvc.views.series.Index;

public class ControllerSeries {

	private ControllerPrincipal cp;
	private DAOSerie dao;
	private DAOGenero daoGenero;
	private Index v;

	// id de la serie que se esta editando (-1 = serie nueva)
	private int idEditando;

	public ControllerSeries(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOSerie();
		daoGenero = new DAOGenero();
		idEditando = -1;
	}

	// VISTAS
	public void index() {
		v = new Index();

		cargarGeneros();

		v.btnGuardar.addActionListener(e -> {
			guardar();
		});
		v.btnNuevo.addActionListener(e -> {
			create();
		});

		cp.vp.setContenido(v, "Sistema Gestor de Series");

		recargar();
	}

	// Deja el formulario listo para registrar una serie nueva
	public void create() {
		idEditando = -1;
		v.tTitulo.setText("");
		v.tTemporadas.setText("");
		v.tCalificacion.setText("");
		v.cbxGenero.setSelectedIndex(0);
		v.tTitulo.requestFocus();
	}

	// Carga la serie en el formulario para editarla
	public void edit(int id) {
		Serie item = dao.find(id);

		if (item == null) {
			mensaje("La serie ya no existe");
			recargar();
			return;
		}

		idEditando = id;
		v.tTitulo.setText(item.getTitulo());
		v.tTemporadas.setText(item.getCantTemporadas() + "");
		v.tCalificacion.setText(item.getCalificacion() + "");
		seleccionarGenero(item.getIdGenero());
	}

	// OPERACIONES
	public void store(Serie item) {
		if (dao.store(item)) {
			create();
			recargar();
		} else {
			mensaje("No se pudo registrar la serie");
		}
	}

	public void update(Serie item, int id) {
		if (dao.update(item, id)) {
			create();
			recargar();
		} else {
			mensaje("No se pudo actualizar la serie");
		}
	}

	public void destroy(int id) {
		if (!confirmar("Desea eliminar la serie?")) {
			return;
		}

		if (dao.destroy(id)) {
			// si se estaba editando la serie eliminada, se limpia el formulario
			if (id == idEditando) {
				create();
			}
			recargar();
		} else {
			mensaje("No se pudo eliminar la serie");
		}
	}

	// AUXILIARES

	// Valida el formulario y decide entre registrar o actualizar
	private void guardar() {

		String titulo = v.tTitulo.getText().trim();
		if (titulo.isEmpty()) {
			mensaje("Debe indicar el titulo");
			return;
		}
		if (titulo.length() > 150) {
			mensaje("El titulo no puede pasar de 150 caracteres");
			return;
		}

		int temporadas;
		try {
			temporadas = Integer.parseInt(v.tTemporadas.getText().trim());
		} catch (NumberFormatException ex) {
			mensaje("Las temporadas deben ser un numero entero");
			return;
		}
		if (temporadas < 1) {
			mensaje("Las temporadas deben ser al menos 1");
			return;
		}

		double calificacion;
		try {
			calificacion = Double.parseDouble(v.tCalificacion.getText().trim().replace(',', '.'));
		} catch (NumberFormatException ex) {
			mensaje("La calificacion debe ser un numero");
			return;
		}
		if (calificacion < 0 || calificacion > 10) {
			mensaje("La calificacion debe estar entre 0 y 10");
			return;
		}

		Genero genero = (Genero) v.cbxGenero.getSelectedItem();
		if (genero == null || genero.getId() == 0) {
			mensaje("Debe seleccionar un genero");
			return;
		}

		Serie item = new Serie(titulo, temporadas, calificacion, genero.getId());

		if (idEditando == -1) {
			store(item);
		} else {
			update(item, idEditando);
		}
	}

	// Llena el combo: primer item de ayuda (id 0) y luego los generos de la BD
	private void cargarGeneros() {
		v.cbxGenero.removeAllItems();
		v.cbxGenero.addItem(new Genero(0, "Seleccione un genero"));
		for (Genero g : daoGenero.all()) {
			v.cbxGenero.addItem(g);
		}
		v.cbxGenero.setSelectedIndex(0);
	}

	// Selecciona en el combo el genero que tiene ese id (la FK de la serie)
	private void seleccionarGenero(int idGenero) {
		for (int i = 0; i < v.cbxGenero.getItemCount(); i++) {
			if (v.cbxGenero.getItemAt(i).getId() == idGenero) {
				v.cbxGenero.setSelectedIndex(i);
				return;
			}
		}
		v.cbxGenero.setSelectedIndex(0);
	}

	// Vuelve a pintar todas las tarjetas y el panel de estado
	private void recargar() {

		ArrayList<Serie> lista = dao.all();

		v.panelSeries.removeAll();

		for (Serie s : lista) {
			Card c = new Card();
			c.lblTitulo.setText(s.getTitulo());
			c.lblGenero.setText("Genero: " + s.getNombreGenero());
			c.lblTemporadas.setText("Temporadas: " + s.getCantTemporadas());
			c.lblCalificacion.setText("Calificacion: " + s.getCalificacion());

			int id = s.getId();
			c.btnEditar.addActionListener(e -> {
				edit(id);
			});
			c.btnEliminar.addActionListener(e -> {
				destroy(id);
			});

			v.panelSeries.add(c);
		}

		v.panelSeries.revalidate();
		v.panelSeries.repaint();

		actualizarEstado(lista);
	}

	// Panel de estado: promedio de calificaciones y cantidad de series
	private void actualizarEstado(ArrayList<Serie> lista) {

		double suma = 0;
		for (Serie s : lista) {
			suma += s.getCalificacion();
		}

		double promedio = 0;
		if (!lista.isEmpty()) {
			promedio = suma / lista.size();
		}

		v.lblPromedio.setText(String.format("%.2f", promedio));
		v.lblCantidad.setText(lista.size() + "");
	}

	protected void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	protected boolean confirmar(String texto) {
		int r = JOptionPane.showConfirmDialog(null, texto, "Confirmar", JOptionPane.YES_NO_OPTION);
		return r == JOptionPane.YES_OPTION;
	}

}