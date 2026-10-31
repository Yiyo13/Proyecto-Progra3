package com.veterinaria.mvc.views.consultas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Show extends JPanel {

	private static final long serialVersionUID = 1L;
	// consulta
	public JLabel lFecha;
	public JLabel lMotivo;
	public JLabel lDiagnostico;
	public JLabel lCosto;
	// mascota
	public JLabel lMascota;
	public JLabel lEspecie;
	public JLabel lRaza;
	// propietario
	public JLabel lPropietario;
	public JLabel lTelefono;
	public JLabel lCorreo;
	public JButton btnEditar;
	public JButton btnRegresar;

	public Show() {
		setLayout(new BorderLayout(10, 10));

		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(0, 1, 10, 10));
		add(centro, BorderLayout.NORTH);

		// datos consulta
		JPanel consulta = new JPanel();
		consulta.setBorder(new TitledBorder("Consulta"));
		consulta.setLayout(new GridLayout(0, 2, 5, 5));
		centro.add(consulta);

		consulta.add(new JLabel("Fecha:"));
		lFecha = new JLabel("-");
		consulta.add(lFecha);

		consulta.add(new JLabel("Motivo:"));
		lMotivo = new JLabel("-");
		consulta.add(lMotivo);

		consulta.add(new JLabel("Diagnostico:"));
		lDiagnostico = new JLabel("-");
		consulta.add(lDiagnostico);

		consulta.add(new JLabel("Costo:"));
		lCosto = new JLabel("-");
		consulta.add(lCosto);

		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(1, 2, 10, 10));
		centro.add(abajo);

		// datos mascota
		JPanel mascota = new JPanel();
		mascota.setBorder(new TitledBorder("Mascota"));
		mascota.setLayout(new GridLayout(0, 2, 5, 5));
		abajo.add(mascota);

		mascota.add(new JLabel("Nombre:"));
		lMascota = new JLabel("-");
		mascota.add(lMascota);

		mascota.add(new JLabel("Especie:"));
		lEspecie = new JLabel("-");
		mascota.add(lEspecie);

		mascota.add(new JLabel("Raza:"));
		lRaza = new JLabel("-");
		mascota.add(lRaza);

		// datos propietario
		JPanel propietario = new JPanel();
		propietario.setBorder(new TitledBorder("Propietario"));
		propietario.setLayout(new GridLayout(0, 2, 5, 5));
		abajo.add(propietario);

		propietario.add(new JLabel("Nombre:"));
		lPropietario = new JLabel("-");
		propietario.add(lPropietario);

		propietario.add(new JLabel("Telefono:"));
		lTelefono = new JLabel("-");
		propietario.add(lTelefono);

		propietario.add(new JLabel("Correo:"));
		lCorreo = new JLabel("-");
		propietario.add(lCorreo);

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
