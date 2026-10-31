package com.veterinaria.mvc.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.veterinaria.mvc.models.dao.DAOPropietario;
import com.veterinaria.mvc.models.vo.Propietario;
import com.veterinaria.mvc.views.propietarios.Form;
import com.veterinaria.mvc.views.propietarios.Index;
import com.veterinaria.mvc.views.propietarios.Show;

public class ControllerPropietarios extends Functions implements Controller<Propietario> {

	private ControllerPrincipal cp;
	private DAOPropietario dao;

	public ControllerPropietarios(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOPropietario();
	}

	@Override
	public void index() {
		Index v = new Index();
		addSearch(v.tBuscar, v.table, 1, 2, 3, 4);
		v.modelo.setDataVector(getData(), getColumns());

		v.btnNuevo.addActionListener(e -> {
			create();
		});
		v.btnVer.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione un propietario de la tabla");
				return;
			}
			show(id);
		});
		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione un propietario de la tabla");
				return;
			}
			edit(id);
		});
		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione un propietario de la tabla");
				return;
			}
			if (confirmar("Desea eliminar el propietario?")) {
				destroy(id);
			}
		});

		cp.vp.setContenido(v, "Lista de Propietarios");
	}

	@Override
	public void create() {
		Form v = new Form();

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Propietario item = leerFormulario(v);
			if (item != null) {
				store(item);
			}
		});

		cp.vp.setContenido(v, "Registrar Propietario");
	}

	@Override
	public void edit(int id) {
		Propietario item = dao.find(id);
		if (item == null) {
			mensaje("El propietario ya no existe");
			index();
			return;
		}

		Form v = new Form();
		v.btnRegistrar.setText("Actualizar");
		v.tNombre.setText(item.getNombre());
		v.tTelefono.setText(item.getTelefono());
		v.tCorreo.setText(item.getCorreo());
		v.tDireccion.setText(item.getDireccion());

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Propietario editado = leerFormulario(v);
			if (editado != null) {
				update(editado, id);
			}
		});

		cp.vp.setContenido(v, "Editar Propietario");
	}

	@Override
	public void show(int id) {
		Propietario item = dao.find(id);
		if (item == null) {
			mensaje("El propietario ya no existe");
			index();
			return;
		}

		Show v = new Show();
		v.lNombre.setText(item.getNombre());
		v.lTelefono.setText(item.getTelefono());
		v.lCorreo.setText(item.getCorreo());
		v.lDireccion.setText(item.getDireccion());

		v.btnEditar.addActionListener(e -> {
			edit(id);
		});
		v.btnRegresar.addActionListener(e -> {
			index();
		});

		cp.vp.setContenido(v, "Detalle del Propietario");
	}

	@Override
	public Object[][] getData() {
		ArrayList<Propietario> list = dao.all();
		Object[][] data = new Object[list.size()][getColumns().length];
		int i = 0;
		for (Propietario item : list) {
			data[i][0] = item.getId();
			data[i][1] = item.getNombre();
			data[i][2] = item.getTelefono();
			data[i][3] = item.getCorreo();
			data[i][4] = item.getDireccion();
			i++;
		}
		return data;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "ID", "Nombre", "Telefono", "Correo", "Direccion" };
	}

	// dao puede dar false aunque guarde, siempre vuelve index
	@Override
	public void store(Propietario item) {
		if (dao.store(item)) {
			System.out.println("Registro correcto");
		} else {
			System.err.println("Revisar registro");
		}
		index();
	}

	@Override
	public void update(Propietario item, int id) {
		if (dao.update(item, id)) {
			System.out.println("Actualizacion correcta");
		} else {
			System.err.println("Revisar actualizacion");
		}
		index();
	}

	@Override
	public void destroy(int id) {
		if (dao.destroy(id)) {
			System.out.println("Eliminado correctamente");
		} else {
			mensaje("No se pudo eliminar el propietario");
		}
		index();
	}

	// AUXILIARES

	private Propietario leerFormulario(Form v) {
		String nombre = v.tNombre.getText().trim();
		String telefono = v.tTelefono.getText().trim();
		String correo = v.tCorreo.getText().trim();
		String direccion = v.tDireccion.getText().trim();

		if (nombre.isEmpty()) {
			mensaje("Debe indicar el nombre");
			return null;
		}
		if (telefono.isEmpty()) {
			mensaje("Debe indicar el telefono");
			return null;
		}

		return new Propietario(nombre, telefono, correo, direccion);
	}

	private void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	private boolean confirmar(String texto) {
		int r = JOptionPane.showConfirmDialog(null, texto, "Confirmar", JOptionPane.YES_NO_OPTION);
		return r == JOptionPane.YES_OPTION;
	}

}
