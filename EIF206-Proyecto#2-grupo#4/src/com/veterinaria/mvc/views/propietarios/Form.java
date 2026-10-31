package com.veterinaria.mvc.views.propietarios;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Form extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField tNombre;
	public JTextField tTelefono;
	public JTextField tCorreo;
	public JTextField tDireccion;
	public JButton btnRegistrar;
	public JButton btnRegresar;

	public Form() {
		setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 35, 90, 16);
		add(lblNombre);

		tNombre = new JTextField();
		tNombre.setBounds(130, 32, 250, 22);
		add(tNombre);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(27, 70, 90, 16);
		add(lblTelefono);

		tTelefono = new JTextField();
		tTelefono.setBounds(130, 67, 250, 22);
		add(tTelefono);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(27, 105, 90, 16);
		add(lblCorreo);

		tCorreo = new JTextField();
		tCorreo.setBounds(130, 102, 250, 22);
		add(tCorreo);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(27, 140, 90, 16);
		add(lblDireccion);

		tDireccion = new JTextField();
		tDireccion.setBounds(130, 137, 250, 22);
		add(tDireccion);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(27, 190, 110, 25);
		add(btnRegistrar);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(150, 190, 110, 25);
		add(btnRegresar);
	}
}
