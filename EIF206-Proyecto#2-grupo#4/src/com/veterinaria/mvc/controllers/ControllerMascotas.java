package com.veterinaria.mvc.controllers;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.veterinaria.mvc.models.dao.DAOConsulta;
import com.veterinaria.mvc.models.dao.DAOMascota;
import com.veterinaria.mvc.models.dao.DAOPropietario;
import com.veterinaria.mvc.models.vo.Consulta;
import com.veterinaria.mvc.models.vo.Mascota;
import com.veterinaria.mvc.models.vo.Propietario;
import com.veterinaria.mvc.views.mascotas.Form;
import com.veterinaria.mvc.views.mascotas.Index;
import com.veterinaria.mvc.views.mascotas.Show;

public class ControllerMascotas extends Functions implements Controller<Mascota> {

	private ControllerPrincipal cp;
	private DAOMascota dao;
	private DAOPropietario daoPropietario;
	private DAOConsulta daoConsulta;

	public ControllerMascotas(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOMascota();
		daoPropietario = new DAOPropietario();
		daoConsulta = new DAOConsulta();
	}

	@Override
	public void index() {
		Index v = new Index();
		addSearch(v.tBuscar, v.table, 1, 2, 3, 5);
		v.modelo.setDataVector(getData(), getColumns());

		v.btnNuevo.addActionListener(e -> {
			create();
		});
		v.btnVer.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione una mascota de la tabla");
				return;
			}
			show(id);
		});
		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione una mascota de la tabla");
				return;
			}
			edit(id);
		});
		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione una mascota de la tabla");
				return;
			}
			if (confirmar("Desea eliminar la mascota?")) {
				destroy(id);
			}
		});

		cp.vp.setContenido(v, "Lista de Mascotas");
	}

	@Override
	public void create() {
		Form v = new Form();
		cargarPropietarios(v.cbxPropietario);

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Mascota item = leerFormulario(v);
			if (item != null) {
				store(item);
			}
		});

		cp.vp.setContenido(v, "Registrar Mascota");
	}

	@Override
	public void edit(int id) {
		Mascota item = dao.find(id);
		if (item == null) {
			mensaje("La mascota ya no existe");
			index();
			return;
		}

		Form v = new Form();
		cargarPropietarios(v.cbxPropietario);

		v.btnRegistrar.setText("Actualizar");
		seleccionarPropietario(v.cbxPropietario, item.getPropietario());
		v.tNombre.setText(item.getNombre());
		v.tEspecie.setText(item.getEspecie());
		v.tRaza.setText(item.getRaza());
		v.dcNacimiento.setDate(item.getFechaNacimiento());

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Mascota editado = leerFormulario(v);
			if (editado != null) {
				update(editado, id);
			}
		});

		cp.vp.setContenido(v, "Editar Mascota");
	}

	// mascota, propietario e historial
	@Override
	public void show(int id) {
		Mascota item = dao.find(id);
		if (item == null) {
			mensaje("La mascota ya no existe");
			index();
			return;
		}

		Show v = new Show();

		// datos mascota
		v.lNombre.setText(item.getNombre());
		v.lEspecie.setText(item.getEspecie());
		v.lRaza.setText(item.getRaza());
		if (item.getFechaNacimiento() != null) {
			v.lNacimiento.setText(item.getFechaNacimiento().toString());
			v.lEdad.setText(item.getEdad() + " anios");
		}

		// datos propietario
		Propietario p = item.getPropietario();
		v.lPropietario.setText(p.getNombre());
		v.lTelefono.setText(p.getTelefono());
		v.lCorreo.setText(p.getCorreo());
		v.lDireccion.setText(p.getDireccion());

		// historial, cantidad y total
		ArrayList<Consulta> historial = daoConsulta.historialByMascota(id);
		Object[][] data = new Object[historial.size()][4];
		double total = 0;
		int i = 0;
		for (Consulta c : historial) {
			data[i][0] = c.getFecha();
			data[i][1] = c.getMotivo();
			data[i][2] = c.getDiagnostico();
			data[i][3] = c.getCosto();
			total += c.getCosto();
			i++;
		}
		v.modelo.setDataVector(data, new String[] { "Fecha", "Motivo", "Diagnostico", "Costo" });
		v.lCantidad.setText("Cantidad de consultas: " + historial.size());
		v.lTotal.setText("Monto total: " + String.format("%.2f", total));

		v.btnEditar.addActionListener(e -> {
			edit(id);
		});
		v.btnRegresar.addActionListener(e -> {
			index();
		});

		cp.vp.setContenido(v, "Detalle de la Mascota");
	}

	@Override
	public Object[][] getData() {
		ArrayList<Mascota> list = dao.all();
		Object[][] data = new Object[list.size()][getColumns().length];
		int i = 0;
		for (Mascota item : list) {
			data[i][0] = item.getId();
			data[i][1] = item.getNombre();
			data[i][2] = item.getEspecie();
			data[i][3] = item.getRaza();
			data[i][4] = item.getFechaNacimiento();
			data[i][5] = item.getPropietario().getNombre();
			i++;
		}
		return data;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "ID", "Nombre", "Especie", "Raza", "Nacimiento", "Propietario" };
	}

	// dao puede dar false aunque guarde, siempre vuelve index
	@Override
	public void store(Mascota item) {
		if (dao.store(item)) {
			System.out.println("Registro correcto");
		} else {
			System.err.println("Revisar registro");
		}
		index();
	}

	@Override
	public void update(Mascota item, int id) {
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
			mensaje("No se pudo eliminar la mascota");
		}
		index();
	}

	// AUXILIARES

	// item ayuda (id 0) y propietarios
	private void cargarPropietarios(JComboBox<Propietario> combo) {
		combo.removeAllItems();
		combo.addItem(new Propietario(0, "Seleccione un propietario", "", "", ""));
		for (Propietario p : daoPropietario.all()) {
			combo.addItem(p);
		}
		combo.setSelectedIndex(0);
		AutoCompleteDecorator.decorate(combo);
	}

	// selecciona propietario por id
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

	private Mascota leerFormulario(Form v) {
		Propietario p = (Propietario) v.cbxPropietario.getSelectedItem();
		if (p == null || p.getId() == 0) {
			mensaje("Debe seleccionar un propietario");
			return null;
		}

		String nombre = v.tNombre.getText().trim();
		String especie = v.tEspecie.getText().trim();
		String raza = v.tRaza.getText().trim();

		if (nombre.isEmpty()) {
			mensaje("Debe indicar el nombre");
			return null;
		}
		if (especie.isEmpty()) {
			mensaje("Debe indicar la especie");
			return null;
		}
		if (v.dcNacimiento.getDate() == null) {
			mensaje("Debe indicar la fecha de nacimiento");
			return null;
		}

		Date nacimiento = new Date(v.dcNacimiento.getDate().getTime());

		return new Mascota(nombre, especie, raza, nacimiento, p);
	}

	private void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	private boolean confirmar(String texto) {
		int r = JOptionPane.showConfirmDialog(null, texto, "Confirmar", JOptionPane.YES_NO_OPTION);
		return r == JOptionPane.YES_OPTION;
	}

}