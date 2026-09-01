package com.proyecto.mvc.views.sistema;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IndexCategorias extends JPanel {
	public JTable table;
	public DefaultTableModel model;
	public JButton btnNuevo;
	public JButton btnEditar;
	public JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public IndexCategorias() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblCategoriaslista = new JLabel("Categorias-Lista");
		lblCategoriaslista.setHorizontalAlignment(SwingConstants.LEFT);
		lblCategoriaslista.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCategoriaslista.setMaximumSize(new Dimension(106,40));
		lblCategoriaslista.setPreferredSize(new Dimension(135,60));
		lblCategoriaslista.setBorder(new EmptyBorder(20, 20, 20, 20));
		lblCategoriaslista.setForeground(Color.WHITE);
		lblCategoriaslista.setBackground(Color.WHITE);
		panel.add(lblCategoriaslista);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		btnNuevo = new JButton("Nuevo");
		panel_1.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		panel_1.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		panel_1.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombre");
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null); //no deja editar la tabla desde la vista
	}
}