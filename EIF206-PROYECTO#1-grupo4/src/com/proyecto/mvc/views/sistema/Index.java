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
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(300, 22));
		panel_1.add(comboBox);
		
		JButton btnCargar = new JButton("Cargar");
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
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setDefaultEditor(Object.class, null); //traje esta linea de la tarea, no deja que editen la tabla desde la vista

	}

}
