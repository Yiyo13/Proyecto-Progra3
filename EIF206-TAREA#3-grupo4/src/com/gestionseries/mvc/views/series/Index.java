package com.gestionseries.mvc.views.series;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.gestionseries.mvc.models.vo.Genero;

public class Index extends JPanel {

	public JTextField tTitulo;
	public JTextField tTemporadas;
	public JTextField tCalificacion;
	public JComboBox<Genero> cbxGenero;
	public JButton btnGuardar;
	public JButton btnNuevo;
	public JPanel panelSeries;
	public JLabel lblPromedio;
	public JLabel lblCantidad;

	/**
	 * Create the panel.
	 */
	public Index() {

		// PROPIEDADES PRINCIPALES
		setLayout(new BorderLayout(0, 0));

		// PANELES
		JPanel panelArriba = new JPanel();
		panelArriba.setBorder(new EmptyBorder(10, 15, 5, 15));
		add(panelArriba, BorderLayout.NORTH);
		panelArriba.setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		panelArriba.add(panelFormulario, BorderLayout.CENTER);
		panelFormulario.setLayout(new GridLayout(4, 2, 15, 3));

		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.LEFT);
		panelArriba.add(panelBotones, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane, BorderLayout.CENTER);

		JPanel contenedor = new JPanel();
		scrollPane.setViewportView(contenedor);
		contenedor.setLayout(new BorderLayout(0, 0));

		panelSeries = new JPanel();
		panelSeries.setBorder(new EmptyBorder(10, 10, 10, 10));
		contenedor.add(panelSeries, BorderLayout.NORTH);
		panelSeries.setLayout(new GridLayout(0, 3, 10, 10));

		JPanel panelEstado = new JPanel();
		panelEstado.setBackground(new Color(192, 192, 192));
		panelEstado.setPreferredSize(new Dimension(10, 70));
		add(panelEstado, BorderLayout.SOUTH);
		panelEstado.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panelPromedio = new JPanel();
		panelPromedio.setOpaque(false);
		panelEstado.add(panelPromedio);
		panelPromedio.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelCantidad = new JPanel();
		panelCantidad.setOpaque(false);
		panelEstado.add(panelCantidad);
		panelCantidad.setLayout(new GridLayout(2, 1, 0, 0));

		// COMPONENTES: panelFormulario
		JLabel lblTitulo = new JLabel("Titulo");
		panelFormulario.add(lblTitulo);

		JLabel lblGenero = new JLabel("Genero");
		panelFormulario.add(lblGenero);

		tTitulo = new JTextField();
		panelFormulario.add(tTitulo);
		tTitulo.setColumns(10);

		cbxGenero = new JComboBox<Genero>();
		panelFormulario.add(cbxGenero);

		JLabel lblTemporadas = new JLabel("Temporadas");
		panelFormulario.add(lblTemporadas);

		JLabel lblCalificacion = new JLabel("Calificacion (0 - 10)");
		panelFormulario.add(lblCalificacion);

		tTemporadas = new JTextField();
		panelFormulario.add(tTemporadas);
		tTemporadas.setColumns(10);

		tCalificacion = new JTextField();
		panelFormulario.add(tCalificacion);
		tCalificacion.setColumns(10);

		// COMPONENTES: panelBotones
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setBackground(Color.WHITE);
		panelBotones.add(btnGuardar);

		btnNuevo = new JButton("Nuevo");
		panelBotones.add(btnNuevo);

		// COMPONENTES: panelPromedio
		JLabel lblTextoPromedio = new JLabel("Promedio de calificaciones:");
		lblTextoPromedio.setHorizontalAlignment(SwingConstants.CENTER);
		panelPromedio.add(lblTextoPromedio);

		lblPromedio = new JLabel("0.00");
		lblPromedio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromedio.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelPromedio.add(lblPromedio);

		// COMPONENTES: panelCantidad
		JLabel lblTextoCantidad = new JLabel("Series registradas:");
		lblTextoCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		panelCantidad.add(lblTextoCantidad);

		lblCantidad = new JLabel("0");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelCantidad.add(lblCantidad);
	}

}