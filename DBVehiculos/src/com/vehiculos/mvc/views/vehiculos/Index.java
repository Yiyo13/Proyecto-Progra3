package com.vehiculos.mvc.views.vehiculos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Index extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton btnNuevo;
	public JButton btnEditar;
	public JButton btnEliminar;
	public JTextField tBuscar;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel modelo;

	public Index() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.SOUTH);

		btnNuevo = new JButton("Nuevo");
		panel.add(btnNuevo);

		btnEditar = new JButton("Editar");
		panel.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblBuscar = new JLabel("Buscar");
		panel_1.add(lblBuscar);

		tBuscar = new JTextField();
		panel_1.add(tBuscar);
		tBuscar.setColumns(10);

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
	}
}
