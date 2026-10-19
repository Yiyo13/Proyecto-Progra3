package com.gestionseries.mvc.views.series;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Card extends JPanel {

	public JLabel lblTitulo;
	public JLabel lblGenero;
	public JLabel lblTemporadas;
	public JLabel lblCalificacion;
	public JButton btnEditar;
	public JButton btnEliminar;

	/**
	 * Create the panel.
	 */
	public Card() {

		// PROPIEDADES PRINCIPALES
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setPreferredSize(new Dimension(230, 130));
		setLayout(new BorderLayout(0, 0));

		// PANELES
		JPanel panelDatos = new JPanel();
		panelDatos.setOpaque(false);
		panelDatos.setBorder(new EmptyBorder(8, 10, 4, 10));
		add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new GridLayout(4, 1, 0, 2));

		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setBorder(new EmptyBorder(4, 10, 8, 10));
		add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(1, 2, 8, 0));

		// COMPONENTES: panelDatos
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lblTitulo);

		lblGenero = new JLabel("Genero: ");
		panelDatos.add(lblGenero);

		lblTemporadas = new JLabel("Temporadas: ");
		panelDatos.add(lblTemporadas);

		lblCalificacion = new JLabel("Calificacion: ");
		panelDatos.add(lblCalificacion);

		// COMPONENTES: panelBotones
		btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setBackground(Color.WHITE);
		panelBotones.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setBackground(Color.WHITE);
		panelBotones.add(btnEliminar);
	}

}