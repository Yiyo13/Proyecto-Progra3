package com.proyecto.mvc.views.sistema;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;

public class FormCategoria extends JPanel {
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public FormCategoria() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);
		
		JLabel lblCategoriasregistrareditar = new JLabel("Categorias-Registrar/Editar");
		lblCategoriasregistrareditar.setForeground(Color.WHITE);
		lblCategoriasregistrareditar.setBackground(Color.LIGHT_GRAY);
		lblCategoriasregistrareditar.setBorder(new EmptyBorder(30, 30, 30, 30));
		lblCategoriasregistrareditar.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategoriasregistrareditar.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCategoriasregistrareditar.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCategoriasregistrareditar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_1.setPreferredSize(new Dimension(300, 100));
		add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(5, 1, 10, 40));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel_1.add(lblNombre);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 10, 10));
		
		JButton btnGuardar = new JButton("Guardar");
		panel_2.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panel_2.add(btnCancelar);
	}
}