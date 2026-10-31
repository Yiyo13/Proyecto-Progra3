package com.veterinaria.mvc.controllers;

import java.awt.event.ItemEvent;
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
import com.veterinaria.mvc.views.consultas.Form;
import com.veterinaria.mvc.views.consultas.Index;
import com.veterinaria.mvc.views.consultas.Show;

public class ControllerConsultas extends Functions implements Controller<Consulta> {

	private ControllerPrincipal cp;
	private DAOConsulta dao;
	private DAOPropietario daoPropietario;
	private DAOMascota daoMascota;

	public ControllerConsultas(ControllerPrincipal cp) {
		this.cp = cp;
		dao = new DAOConsulta();
		daoPropietario = new DAOPropietario();
		daoMascota = new DAOMascota();
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
				mensaje("Seleccione una consulta de la tabla");
				return;
			}
			show(id);
		});
		v.btnEditar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione una consulta de la tabla");
				return;
			}
			edit(id);
		});
		v.btnEliminar.addActionListener(e -> {
			int id = getSelectedID(v.table);
			if (id == -1) {
				mensaje("Seleccione una consulta de la tabla");
				return;
			}
			if (confirmar("Desea eliminar la consulta?")) {
				destroy(id);
			}
		});

		cp.vp.setContenido(v, "Lista de Consultas");
	}

	@Override
	public void create() {
		Form v = new Form();
		prepararCombos(v);

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Consulta item = leerFormulario(v);
			if (item != null) {
				store(item);
			}
		});

		cp.vp.setContenido(v, "Registrar Consulta");
	}

	@Override
	public void edit(int id) {
		Consulta item = dao.find(id);
		if (item == null) {
			mensaje("La consulta ya no existe");
			index();
			return;
		}

		Form v = new Form();
		prepararCombos(v);

		v.btnRegistrar.setText("Actualizar");
		// primero propietario (recarga mascotas) y luego mascota
		seleccionarPropietario(v.cbxPropietario, item.getMascota().getPropietario().getId());
		seleccionarMascota(v.cbxMascota, item.getMascota().getId());
		v.dcFecha.setDate(item.getFecha());
		v.tMotivo.setText(item.getMotivo());
		v.tDiagnostico.setText(item.getDiagnostico());
		v.tCosto.setText(item.getCosto() + "");

		v.btnRegresar.addActionListener(e -> {
			index();
		});
		v.btnRegistrar.addActionListener(e -> {
			Consulta editado = leerFormulario(v);
			if (editado != null) {
				update(editado, id);
			}
		});

		cp.vp.setContenido(v, "Editar Consulta");
	}

	// consulta con mascota y propietario
	@Override
	public void show(int id) {
		Consulta item = dao.find(id);
		if (item == null) {
			mensaje("La consulta ya no existe");
			index();
			return;
		}

		Show v = new Show();

		v.lFecha.setText(item.getFecha().toString());
		v.lMotivo.setText(item.getMotivo());
		v.lDiagnostico.setText(item.getDiagnostico());
		v.lCosto.setText(String.format("%.2f", item.getCosto()));

		Mascota m = item.getMascota();
		v.lMascota.setText(m.getNombre());
		v.lEspecie.setText(m.getEspecie());
		v.lRaza.setText(m.getRaza());

		Propietario p = m.getPropietario();
		v.lPropietario.setText(p.getNombre());
		v.lTelefono.setText(p.getTelefono());
		v.lCorreo.setText(p.getCorreo());

		v.btnEditar.addActionListener(e -> {
			edit(id);
		});
		v.btnRegresar.addActionListener(e -> {
			index();
		});

		cp.vp.setContenido(v, "Detalle de la Consulta");
	}

	@Override
	public Object[][] getData() {
		ArrayList<Consulta> list = dao.all();
		Object[][] data = new Object[list.size()][getColumns().length];
		int i = 0;
		for (Consulta item : list) {
			data[i][0] = item.getId();
			data[i][1] = item.getFecha();
			data[i][2] = item.getMascota().getNombre();
			data[i][3] = item.getMascota().getPropietario().getNombre();
			data[i][4] = item.getMotivo();
			data[i][5] = item.getCosto();
			i++;
		}
		return data;
	}

	@Override
	public String[] getColumns() {
		return new String[] { "ID", "Fecha", "Mascota", "Propietario", "Motivo", "Costo" };
	}

	// dao puede dar false aunque guarde, siempre vuelve index
	@Override
	public void store(Consulta item) {
		if (dao.store(item)) {
			System.out.println("Registro correcto");
		} else {
			System.err.println("Revisar registro");
		}
		index();
	}

	@Override
	public void update(Consulta item, int id) {
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
			mensaje("No se pudo eliminar la consulta");
		}
		index();
	}

	// AUXILIARES

	// carga combos, cambia propietario y recarga mascotas
	private void prepararCombos(Form v) {
		cargarPropietarios(v.cbxPropietario);
		cargarMascotas(v.cbxMascota, 0);

		v.cbxPropietario.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Propietario p = (Propietario) e.getItem();
				cargarMascotas(v.cbxMascota, p.getId());
			}
		});

		AutoCompleteDecorator.decorate(v.cbxPropietario);
		AutoCompleteDecorator.decorate(v.cbxMascota);
	}

	// item ayuda (id 0) y propietarios
	private void cargarPropietarios(JComboBox<Propietario> combo) {
		combo.removeAllItems();
		combo.addItem(new Propietario(0, "Seleccione un propietario", "", "", ""));
		for (Propietario p : daoPropietario.all()) {
			combo.addItem(p);
		}
		combo.setSelectedIndex(0);
	}

	// item ayuda (id 0) y mascotas del propietario
	private void cargarMascotas(JComboBox<Mascota> combo, int idPropietario) {
		combo.removeAllItems();
		combo.addItem(new Mascota(0, "Seleccione una mascota", "", "", null, null));
		if (idPropietario > 0) {
			for (Mascota m : daoMascota.allByPropietario(idPropietario)) {
				combo.addItem(m);
			}
		}
		combo.setSelectedIndex(0);
	}

	private void seleccionarPropietario(JComboBox<Propietario> combo, int idPropietario) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).getId() == idPropietario) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	private void seleccionarMascota(JComboBox<Mascota> combo, int idMascota) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).getId() == idMascota) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	private Consulta leerFormulario(Form v) {
		Mascota m = (Mascota) v.cbxMascota.getSelectedItem();
		if (m == null || m.getId() == 0) {
			mensaje("Debe seleccionar el propietario y la mascota");
			return null;
		}

		if (v.dcFecha.getDate() == null) {
			mensaje("Debe indicar la fecha");
			return null;
		}
		Date fecha = new Date(v.dcFecha.getDate().getTime());

		String motivo = v.tMotivo.getText().trim();
		String diagnostico = v.tDiagnostico.getText().trim();

		if (motivo.isEmpty()) {
			mensaje("Debe indicar el motivo");
			return null;
		}

		double costo;
		try {
			costo = Double.parseDouble(v.tCosto.getText().trim().replace(',', '.'));
		} catch (NumberFormatException ex) {
			mensaje("El costo debe ser un numero");
			return null;
		}
		if (costo < 0) {
			mensaje("El costo no puede ser negativo");
			return null;
		}

		return new Consulta(0, fecha, motivo, diagnostico, costo, m);
	}

	private void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

	private boolean confirmar(String texto) {
		int r = JOptionPane.showConfirmDialog(null, texto, "Confirmar", JOptionPane.YES_NO_OPTION);
		return r == JOptionPane.YES_OPTION;
	}

}