package com.veterinaria.mvc.views.mascotas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Show extends JPanel {

	private static final long serialVersionUID = 1L;
	// mascota
	public JLabel lNombre;
	public JLabel lEspecie;
	public JLabel lRaza;
	public JLabel lNacimiento;
	public JLabel lEdad;
	// propietario
	public JLabel lPropietario;
	public JLabel lTelefono;
	public JLabel lCorreo;
	public JLabel lDireccion;
	// historial
	public JTable table;
	public DefaultTableModel modelo;
	public JLabel lCantidad;
	public JLabel lTotal;
	public JButton btnEditar;
	public JButton btnRegresar;

	public Show() {
		setLayout(new BorderLayout(10, 10));

		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(1, 2, 10, 10));
		add(arriba, BorderLayout.NORTH);

		// datos mascota
		JPanel mascota = new JPanel();
		mascota.setBorder(new TitledBorder("Mascota"));
		mascota.setLayout(new GridLayout(0, 2, 5, 5));
		arriba.add(mascota);

		mascota.add(new JLabel("Nombre:"));
		lNombre = new JLabel("-");
		mascota.add(lNombre);

		mascota.add(new JLabel("Especie:"));
		lEspecie = new JLabel("-");
		mascota.add(lEspecie);

		mascota.add(new JLabel("Raza:"));
		lRaza = new JLabel("-");
		mascota.add(lRaza);

		mascota.add(new JLabel("Nacimiento:"));
		lNacimiento = new JLabel("-");
		mascota.add(lNacimiento);

		mascota.add(new JLabel("Edad:"));
		lEdad = new JLabel("-");
		mascota.add(lEdad);

		// datos propietario
		JPanel propietario = new JPanel();
		propietario.setBorder(new TitledBorder("Propietario"));
		propietario.setLayout(new GridLayout(0, 2, 5, 5));
		arriba.add(propietario);

		propietario.add(new JLabel("Nombre:"));
		lPropietario = new JLabel("-");
		propietario.add(lPropietario);

		propietario.add(new JLabel("Telefono:"));
		lTelefono = new JLabel("-");
		propietario.add(lTelefono);

		propietario.add(new JLabel("Correo:"));
		lCorreo = new JLabel("-");
		propietario.add(lCorreo);

		propietario.add(new JLabel("Direccion:"));
		lDireccion = new JLabel("-");
		propietario.add(lDireccion);

		// historial
		JPanel historial = new JPanel();
		historial.setBorder(new TitledBorder("Historial de consultas"));
		historial.setLayout(new BorderLayout(5, 5));
		add(historial, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		historial.add(scrollPane, BorderLayout.CENTER);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JPanel totales = new JPanel();
		totales.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		historial.add(totales, BorderLayout.SOUTH);

		lCantidad = new JLabel("Cantidad de consultas: 0");
		lCantidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		totales.add(lCantidad);

		lTotal = new JLabel("Monto total: 0.00");
		lTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		totales.add(lTotal);

		// botones
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(botones, BorderLayout.SOUTH);

		btnEditar = new JButton("Editar");
		botones.add(btnEditar);

		btnRegresar = new JButton("Regresar");
		botones.add(btnRegresar);
	}
}
