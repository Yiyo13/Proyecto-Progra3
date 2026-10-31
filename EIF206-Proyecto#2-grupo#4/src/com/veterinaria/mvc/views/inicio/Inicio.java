package com.veterinaria.mvc.views.inicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class Inicio extends JPanel {

	private static final long serialVersionUID = 1L;
	public JDateChooser dcInicio;
	public JDateChooser dcFin;
	public JButton btnCalcular;
	public JLabel lCantidad;
	public JLabel lTotal;
	public JTable table;
	public DefaultTableModel modelo;

	public Inicio() {
		setLayout(new BorderLayout(10, 10));

		JPanel reporte1 = new JPanel();
		reporte1.setBorder(new TitledBorder("Consultas por rango de fechas"));
		reporte1.setPreferredSize(new Dimension(10, 120));
		reporte1.setLayout(null);
		add(reporte1, BorderLayout.NORTH);

		JLabel lblInicio = new JLabel("Fecha inicio");
		lblInicio.setBounds(20, 30, 90, 22);
		reporte1.add(lblInicio);

		dcInicio = new JDateChooser();
		dcInicio.setDateFormatString("yyyy-MM-dd");
		dcInicio.setBounds(110, 30, 140, 22);
		reporte1.add(dcInicio);

		JLabel lblFin = new JLabel("Fecha fin");
		lblFin.setBounds(270, 30, 70, 22);
		reporte1.add(lblFin);

		dcFin = new JDateChooser();
		dcFin.setDateFormatString("yyyy-MM-dd");
		dcFin.setBounds(340, 30, 140, 22);
		reporte1.add(dcFin);

		btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(500, 30, 100, 24);
		reporte1.add(btnCalcular);

		lCantidad = new JLabel("Cantidad de consultas: -");
		lCantidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		lCantidad.setBounds(20, 75, 250, 22);
		reporte1.add(lCantidad);

		lTotal = new JLabel("Monto total: -");
		lTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lTotal.setBounds(280, 75, 300, 22);
		reporte1.add(lTotal);

		JPanel reporte2 = new JPanel();
		reporte2.setBorder(new TitledBorder("Ultimas consultas (clic para editar)"));
		reporte2.setLayout(new BorderLayout(0, 0));
		add(reporte2, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		reporte2.add(scrollPane, BorderLayout.CENTER);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
	}
}
