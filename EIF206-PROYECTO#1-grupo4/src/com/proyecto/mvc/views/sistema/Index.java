package com.proyecto.mvc.views.sistema;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Index extends JPanel {
	public JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel model;
	public JComboBox cbxCategoria;
	public JButton btnCargar;
	public JButton btnCompletar;
	public JButton btnGuardar;
	public JButton btnEditar;
	public JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public Index() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblTareasPendientes = new JLabel("Tareas Pendientes");
		lblTareasPendientes.setMaximumSize(new Dimension(106, 40));
		lblTareasPendientes.setPreferredSize(new Dimension(135, 60));
		lblTareasPendientes.setForeground(Color.WHITE);
		lblTareasPendientes.setBackground(Color.WHITE);
		panel.add(lblTareasPendientes);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCategora.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(lblCategora);

		cbxCategoria = new JComboBox();
		cbxCategoria.setPreferredSize(new Dimension(300, 22));
		panel_1.add(cbxCategoria);

		btnCargar = new JButton("Cargar");
		panel_1.add(btnCargar);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);

		JLabel lblCategora_1 = new JLabel("Categor\u00EDa");
		lblCategora_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategora_1.setHorizontalTextPosition(SwingConstants.LEFT);
		panel_3.add(lblCategora_1);

		scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");

		model.addRow(new Object[]{1, "Tarea de prueba", "Trabajo"});
		model.addRow(new Object[]{2, "Otra tarea", "Personal"});//prueba para ver si la ventana funciona, recordar eliminar
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setHgap(15);
		panel_2.add(panel_4, BorderLayout.SOUTH);

		btnGuardar = new JButton("Guardar");
		panel_4.add(btnGuardar);

		btnEditar = new JButton("Editar");
		panel_4.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		panel_4.add(btnEliminar);

		btnCompletar = new JButton("Completada");
		panel_4.add(btnCompletar);
		table.setDefaultEditor(Object.class, null); //traje esta linea de la tarea, no deja que editen la tabla desde la vista

	}

}
