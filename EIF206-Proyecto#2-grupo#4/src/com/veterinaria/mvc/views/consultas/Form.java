package com.veterinaria.mvc.views.consultas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.veterinaria.mvc.models.vo.Mascota;
import com.veterinaria.mvc.models.vo.Propietario;

public class Form extends JPanel {

	private static final long serialVersionUID = 1L;
	public JComboBox<Propietario> cbxPropietario;
	public JComboBox<Mascota> cbxMascota;
	public JDateChooser dcFecha;
	public JTextField tMotivo;
	public JTextField tDiagnostico;
	public JTextField tCosto;
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

		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setBounds(27, 70, 100, 16);
		add(lblMascota);

		cbxMascota = new JComboBox<Mascota>();
		cbxMascota.setBounds(140, 67, 250, 22);
		add(cbxMascota);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(27, 105, 100, 16);
		add(lblFecha);

		dcFecha = new JDateChooser();
		dcFecha.setDateFormatString("yyyy-MM-dd");
		dcFecha.setBounds(140, 102, 160, 22);
		add(dcFecha);

		JLabel lblMotivo = new JLabel("Motivo");
		lblMotivo.setBounds(27, 140, 100, 16);
		add(lblMotivo);

		tMotivo = new JTextField();
		tMotivo.setBounds(140, 137, 250, 22);
		add(tMotivo);

		JLabel lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setBounds(27, 175, 100, 16);
		add(lblDiagnostico);

		tDiagnostico = new JTextField();
		tDiagnostico.setBounds(140, 172, 250, 22);
		add(tDiagnostico);

		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(27, 210, 100, 16);
		add(lblCosto);

		tCosto = new JTextField();
		tCosto.setBounds(140, 207, 160, 22);
		add(tCosto);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(27, 260, 110, 25);
		add(btnRegistrar);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(150, 260, 110, 25);
		add(btnRegresar);
	}
}
