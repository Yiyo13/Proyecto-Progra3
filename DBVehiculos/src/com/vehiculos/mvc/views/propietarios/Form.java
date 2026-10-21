package com.vehiculos.mvc.views.propietarios;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Form extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField tNombre;
	public JTextField tTelefono;
	public JTextField tEdad;
	public JButton btnRegistrar;
	public JButton btnRegresar;

	/**
	 * Create the panel.
	 */
	public Form() {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 35, 56, 16);
		add(lblNombre);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(27, 67, 56, 16);
		add(lblTelfono);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(27, 99, 56, 16);
		add(lblEdad);
		
		tNombre = new JTextField();
		tNombre.setBounds(120, 32, 116, 22);
		add(tNombre);
		tNombre.setColumns(10);
		
		tTelefono = new JTextField();
		tTelefono.setColumns(10);
		tTelefono.setBounds(120, 64, 116, 22);
		add(tTelefono);
		
		tEdad = new JTextField();
		tEdad.setColumns(10);
		tEdad.setBounds(120, 96, 116, 22);
		add(tEdad);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(27, 148, 97, 25);
		add(btnRegistrar);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(139, 148, 97, 25);
		add(btnRegresar);

	}
}
