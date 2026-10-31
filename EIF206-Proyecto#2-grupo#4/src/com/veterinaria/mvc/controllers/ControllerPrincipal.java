package com.veterinaria.mvc.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.veterinaria.mvc.models.dao.DAOConsulta;
import com.veterinaria.mvc.models.vo.Consulta;
import com.veterinaria.mvc.views.ViewPrincipal;
import com.veterinaria.mvc.views.inicio.Inicio;

public class ControllerPrincipal extends Functions {

	public ViewPrincipal vp;
	public ControllerPropietarios propietarios;
	public ControllerMascotas mascotas;
	public ControllerConsultas consultas;
	private DAOConsulta daoConsulta;

	public ControllerPrincipal() {
		vp = new ViewPrincipal();
		propietarios = new ControllerPropietarios(this);
		mascotas = new ControllerMascotas(this);
		consultas = new ControllerConsultas(this);
		daoConsulta = new DAOConsulta();
	}

	public void init() {
		vp.btnInicio.addActionListener(e -> {
			inicio();
		});
		vp.btnPropietarios.addActionListener(e -> {
			propietarios.index();
		});
		vp.btnMascotas.addActionListener(e -> {
			mascotas.index();
		});
		vp.btnConsultas.addActionListener(e -> {
			consultas.index();
		});

		vp.init();

		// vista inicial
		inicio();
	}

	// inicio, se arma cada vez (datos frescos)
	public void inicio() {
		Inicio v = new Inicio();

		v.btnCalcular.addActionListener(e -> {
			if (v.dcInicio.getDate() == null || v.dcFin.getDate() == null) {
				mensaje("Debe seleccionar la fecha de inicio y la fecha final");
				return;
			}

			Date inicio = new Date(v.dcInicio.getDate().getTime());
			Date fin = new Date(v.dcFin.getDate().getTime());

			if (inicio.after(fin)) {
				mensaje("La fecha de inicio no puede ser mayor a la fecha final");
				return;
			}

			double[] resumen = daoConsulta.resumenFechas(inicio, fin);
			v.lCantidad.setText("Cantidad de consultas: " + (int) resumen[0]);
			v.lTotal.setText("Monto total: " + String.format("%.2f", resumen[1]));
		});

		v.modelo.setDataVector(getUltimas(), new String[] { "ID", "Fecha", "Mascota", "Propietario", "Motivo", "Costo" });

		// clic consulta abre edit
		v.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = getSelectedID(v.table);
				if (id != -1) {
					consultas.edit(id);
				}
			}
		});

		vp.setContenido(v, "Inicio");
	}

	private Object[][] getUltimas() {
		ArrayList<Consulta> list = daoConsulta.ultimas();
		Object[][] data = new Object[list.size()][6];
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

	private void mensaje(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

}
