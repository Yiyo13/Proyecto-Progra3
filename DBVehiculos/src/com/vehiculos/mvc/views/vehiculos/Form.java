package com.vehiculos.mvc.views.vehiculos;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.vehiculos.mvc.models.vo.Propietario;

public class Form extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField tPlaca;
	public JComboBox<Propietario> cbxPropietario;
	public JButton btnRegistrar;
	public JButton btnRegresar;

	public Form() {
		setLayout(null);

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(27, 35, 80, 16);
		add(lblPlaca);

		JLabel lblPropietario = new JLabel("Propietario");
		lblPropietario.setBounds(27, 67, 80, 16);
		add(lblPropietario);

		tPlaca = new JTextField();
		tPlaca.setBounds(120, 32, 160, 22);
		add(tPlaca);
		tPlaca.setColumns(10);

		cbxPropietario = new JComboBox<Propietario>();
		cbxPropietario.setBounds(120, 64, 160, 22);
		add(cbxPropietario);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(27, 110, 97, 25);
		add(btnRegistrar);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(139, 110, 97, 25);
		add(btnRegresar);
	}
}
