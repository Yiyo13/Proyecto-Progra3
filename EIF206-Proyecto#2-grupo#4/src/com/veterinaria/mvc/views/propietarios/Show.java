package com.veterinaria.mvc.views.propietarios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Show extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel lNombre;
	public JLabel lTelefono;
	public JLabel lCorreo;
	public JLabel lDireccion;
	public JButton btnEditar;
	public JButton btnRegresar;

	public Show() {
		setLayout(new BorderLayout(10, 10));

		JPanel datos = new JPanel();
		datos.setBorder(new TitledBorder("Datos del propietario"));
		datos.setLayout(new GridLayout(0, 2, 10, 10));
		add(datos, BorderLayout.NORTH);

		datos.add(new JLabel("Nombre:"));
		lNombre = new JLabel("-");
		datos.add(lNombre);

		datos.add(new JLabel("Telefono:"));
		lTelefono = new JLabel("-");
		datos.add(lTelefono);

		datos.add(new JLabel("Correo:"));
		lCorreo = new JLabel("-");
		datos.add(lCorreo);

		datos.add(new JLabel("Direccion:"));
		lDireccion = new JLabel("-");
		datos.add(lDireccion);

		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(botones, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		botones.add(btnEditar);

		btnRegresar = new JButton("Regresar");
		botones.add(btnRegresar);
	}
}
