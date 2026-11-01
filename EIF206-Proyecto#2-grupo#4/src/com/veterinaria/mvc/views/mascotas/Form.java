package com.veterinaria.mvc.views.mascotas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.veterinaria.mvc.models.vo.Propietario;

public class Form extends JPanel {

	private static final long serialVersionUID = 1L;
	public JComboBox<Propietario> cbxPropietario;
	public JTextField tNombre;
	public JComboBox<String> cbxEspecie;
	public JTextField tRaza;
	public JDateChooser dcNacimiento;
	public JButton btnRegistrar;
	public JButton btnRegresar;

	public Form() {
		setLayout(null);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(27, 35, 100, 16);
		add(lblPropietario);

		cbxPropietario = new JComboBox<Propietario>();
		cbxPropietario.setBounds(140, 32, 250, 22);
		add(cbxPropietario);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 70, 100, 16);
		add(lblNombre);

		tNombre = new JTextField();
		tNombre.setBounds(140, 67, 250, 22);
		add(tNombre);

		JLabel lblEspecie = new JLabel("Especie");
		lblEspecie.setBounds(27, 105, 100, 16);
		add(lblEspecie);

		cbxEspecie = new JComboBox<String>();
		cbxEspecie.setBounds(140, 102, 250, 22);
		add(cbxEspecie);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(27, 140, 100, 16);
		add(lblRaza);

		tRaza = new JTextField();
		tRaza.setBounds(140, 137, 250, 22);
		add(tRaza);

		JLabel lblNacimiento = new JLabel("Nacimiento");
		lblNacimiento.setBounds(27, 175, 110, 16);
		add(lblNacimiento);

		dcNacimiento = new JDateChooser();
		dcNacimiento.setDateFormatString("dd/MM/yyyy");
		dcNacimiento.setBounds(140, 172, 160, 22);
		add(dcNacimiento);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(27, 225, 110, 25);
		add(btnRegistrar);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(150, 225, 110, 25);
		add(btnRegresar);
	}
}