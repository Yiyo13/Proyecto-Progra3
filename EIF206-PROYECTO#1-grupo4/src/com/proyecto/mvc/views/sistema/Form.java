package com.proyecto.mvc.views.sistema;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;

public class Form extends JPanel {
	public JTextField tNombre;
	public JTextArea taDescripcion;
	public JComboBox cbxCategoria;
	public JButton btnGuardar;
	public JButton btnCancelar;

	/**
	 * Create the panel.
	 */
	public Form() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		JLabel lblTareasregistrar = new JLabel("Tareas-Registrar/Editar");
		lblTareasregistrar.setForeground(Color.WHITE);
		lblTareasregistrar.setBackground(Color.LIGHT_GRAY);
		lblTareasregistrar.setBorder(new EmptyBorder(20, 20, 20, 20));
		lblTareasregistrar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTareasregistrar.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTareasregistrar.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblTareasregistrar);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(40, 40, 40, 40));
		add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(10, 1, 0, 15));

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNombre);

		tNombre = new JTextField();
		panel_1.add(tNombre);
		tNombre.setColumns(10);

		JLabel lblDescripcin = new JLabel("Descripcion");
		panel_1.add(lblDescripcin);

		taDescripcion = new JTextArea();
		taDescripcion.setMinimumSize(new Dimension(20, 20));
		panel_1.add(taDescripcion);

		JLabel lblCategora = new JLabel("Categoria");
		panel_1.add(lblCategora);

		cbxCategoria = new JComboBox();
		cbxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una"}));
		panel_1.add(cbxCategoria);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 10, 10));

		btnGuardar = new JButton("Guardar");
		panel_2.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		panel_2.add(btnCancelar);

	}

}
