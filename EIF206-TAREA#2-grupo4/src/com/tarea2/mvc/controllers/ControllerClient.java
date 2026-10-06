package com.tarea2.mvc.controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.tarea2.mvc.models.Client;
import com.tarea2.mvc.models.ListaPlatillos;
import com.tarea2.mvc.models.Pedido;
import com.tarea2.mvc.models.Platillo;
import com.tarea2.mvc.views.ViewClient;

public class ControllerClient {

	private ViewClient v;
	private Client client;

	// platillos disponibles (los del JComboBox)
	private ListaPlatillos menu;
	// platillos que el cliente va agregando a su pedido
	private ListaPlatillos pedido;

	public ControllerClient() {
		v = new ViewClient();
		menu = new ListaPlatillos();
		pedido = new ListaPlatillos();
	}

	public void init() {

		loadData();
		cargarCombo();
		cargarTabla();

		//v.allowPedido(false); //comentada para probar la tabla

		v.btnConectar.addActionListener(e -> {

			String host = v.tHost.getText().trim();

			if (host.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe indicar el host.");
				return;
			}

			client = new Client(host);

			if (client.conectar()) {
				v.allowPedido(true);
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor.");
			}

		});

		v.btnAgregar.addActionListener(e -> {

			Platillo item = (Platillo) v.cbxPlatillo.getSelectedItem();

			if (item != null) {
				pedido.agregarPlatillo(item);
				cargarTabla();
			}

		});

		v.btnQuitar.addActionListener(e -> {

			int fila = v.table.getSelectedRow();

			if (fila >= 0) {
				pedido.eliminarPlatillo(fila);
				cargarTabla();
			}

		});

		v.btnEnviar.addActionListener(e -> {

			String nombre = v.tCliente.getText().trim();

			if (nombre.isEmpty() || pedido.getPlatillos().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe indicar el cliente y agregar al menos un platillo.");
				return;
			}

			if (client.enviarPedido(new Pedido(nombre, pedido))) {
				// despues de enviar el pedido no se hace nada mas en la vista
				v.btnEnviar.setEnabled(false);
				v.btnAgregar.setEnabled(false);
				v.btnQuitar.setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo enviar el pedido.");
			}

		});

		v.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (client != null) {
					client.close();
				}
			}
		});

		v.init();

	}

	// AUXILIARES

	private void loadData() {

		// el primer parametro (id) no se usa: Platillo asigna su propio id con el contador
		menu.agregarPlatillo(new Platillo(0, "Hamburguesa", 2500));
		menu.agregarPlatillo(new Platillo(0, "Tacos", 1500));
		menu.agregarPlatillo(new Platillo(0, "Refresco", 1000));
		menu.agregarPlatillo(new Platillo(0, "Papas fritas", 1200));
		menu.agregarPlatillo(new Platillo(0, "Pizza", 3500));

	}

	private void cargarCombo() {

		v.cbxPlatillo.removeAllItems();

		for (Platillo p : menu.getPlatillos()) {
			v.cbxPlatillo.addItem(p);
		}

	}

	private void cargarTabla() {

		ArrayList<Platillo> platillos = pedido.getPlatillos();
		Object[][] data = new Object[platillos.size()][3];

		for (int i = 0; i < platillos.size(); i++) {
			Platillo p = platillos.get(i);
			data[i][0] = p.getIdPlatilo();
			data[i][1] = p.getNombrePlatillo();
			data[i][2] = p.getPrecioPlatillo();
		}

		v.model.setDataVector(data, new String[] { "ID", "Nombre", "Precio" });
		v.lblTotal.setText("TOTAL: " + String.format("%.0f", pedido.getTotal()));

	}

}
