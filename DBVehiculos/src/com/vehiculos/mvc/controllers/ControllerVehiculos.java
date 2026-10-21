package com.vehiculos.mvc.controllers;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.vehiculos.mvc.models.dao.DAOPropietario;
import com.vehiculos.mvc.models.dao.DAOVehiculo;
import com.vehiculos.mvc.models.vo.Propietario;
import com.vehiculos.mvc.models.vo.Vehiculo;
import com.vehiculos.mvc.views.vehiculos.Form;
import com.vehiculos.mvc.views.vehiculos.Index;

public class ControllerVehiculos extends Functions implements Controller<Vehiculo> {

	private ControllerPrincipal cp;
	private DAOVehiculo dao;
	private DAOPropietario daoPropietario;

	public ControllerVehiculos(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOVehiculo();
		daoPropietario = new DAOPropietario();
	}

	@Override
	public void index() {
		Index v = new Index();
		addSearch(v.tBuscar, v.table, 1, 2);
		v.modelo.setDataVector(getData(), getColumns());

		v.btnNuevo.addActionListener(e -> {
			create();
		});
		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione un vehiculo de la tabla");
				return;
			}
			edit(id);
		});
		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione un vehiculo de la tabla");
				return;
			}
			if (JOptionPane.showConfirmDialog(null, "Desea eliminar el vehiculo?", "Confirmar",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				destroy(id);
			}
		});

		cp.vp.setContenido(v, "Lista de Vehiculos");
	}

	@Override
	public void create() {
		Form v = new Form();
		cargarPropietarios(v.cbxPropietario);

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Vehiculo item = leerFormulario(v);
			if (item != null) {
				store(item);
			}
		});

		cp.vp.setContenido(v, "Registrar Vehiculo");
	}

	@Override
	public void edit(int id) {
		Vehiculo item = dao.find(id);
		if (item == null) {
			mensaje("El vehiculo ya no existe");
			index();
			return;
		}

		Form v = new Form();
		cargarPropietarios(v.cbxPropietario);

		v.btnRegistrar.setText("Actualizar");
		v.tPlaca.setText(item.getPlaca());
		seleccionarPropietario(v.cbxPropietario, item.getPropietario());

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Vehiculo editado = leerFormulario(v);
			if (editado != null) {
				update(editado, id);
			}
		});

		cp.vp.setContenido(v, "Editar Vehiculo");
	}

	@Override
	public void show() {

	}

	@Override
	public Object[][] getData() {
		ArrayList<Vehiculo> list = dao.all();
		Object[][] data = new Object[list.size()][getColumns().length];
		int i = 0;
		for (Vehiculo item : list) {
			data[i][0] = item.getId();
			data[i][1] = item.getPlaca();
			data[i][2] = item.getPropietario().getNombre();
			i++;
		}
		return data;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "ID", "Placa", "Propietario" };
	}

	@Override
	public void store(Vehiculo item) {
		if (dao.store(item)) {
			System.out.println("Registro correcto");
			index();
		} else {
			mensaje("No se pudo registrar el vehiculo");
		}
	}

	@Override
	public void update(Vehiculo item, int id) {
		if (dao.update(item, id)) {
			System.out.println("Actualizacion correcta");
			index();
		} else {
			mensaje("No se pudo actualizar el vehiculo");
		}
	}

	@Override
	public void destroy(int id) {
		if (dao.destroy(id)) {
			System.out.println("Eliminado correctamente");
		} else {
			mensaje("No se pudo eliminar el vehiculo");
		}
		index();
	}

	private void cargarPropietarios(JComboBox<Propietario> combo) {
		combo.removeAllItems();
		combo.addItem(new Propietario(0, "Seleccione un propietario", "", 0));
		for (Propietario p : daoPropietario.all()) {
			combo.addItem(p);
		}
		combo.setSelectedIndex(0);
	}

	private void seleccionarPropietario(JComboBox<Propietario> combo, Propietario propietario) {
		if (propietario == null) {
			return;
		}
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).getId() == propietario.getId()) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	private Vehiculo leerFormulario(Form v) {
		String placa = v.tPlaca.getText().trim();
		if (placa.isEmpty()) {
			mensaje("Debe indicar la placa");
			return null;
		}

		Propietario p = (Propietario) v.cbxPropietario.getSelectedItem();
		if (p == null || p.getId() == 0) {
			mensaje("Debe seleccionar un propietario");
			return null;
		}

		return new Vehiculo(0, placa, p.getId(), p);
	}

	private void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

}
