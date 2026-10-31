package com.veterinaria.mvc.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contenido;
	private JPanel menu;
	public JLabel lTitulo;
	public JButton btnInicio;
	public JButton btnPropietarios;
	public JButton btnMascotas;
	public JButton btnConsultas;

	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 620);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel lateral = new JPanel();
		lateral.setBackground(Color.DARK_GRAY);
		lateral.setPreferredSize(new Dimension(150, 10));
		contentPane.add(lateral, BorderLayout.WEST);

		menu = new JPanel();
		menu.setOpaque(false);
		menu.setPreferredSize(new Dimension(130, 160));
		lateral.add(menu);
		menu.setLayout(new GridLayout(0, 1, 10, 10));

		btnInicio = new JButton("Inicio");
		menu.add(btnInicio);

		btnPropietarios = new JButton("Propietarios");
		menu.add(btnPropietarios);

		btnMascotas = new JButton("Mascotas");
		menu.add(btnMascotas);

		btnConsultas = new JButton("Consultas");
		menu.add(btnConsultas);

		JPanel central = new JPanel();
		contentPane.add(central, BorderLayout.CENTER);
		central.setLayout(new BorderLayout(0, 0));

		JPanel arriba = new JPanel();
		arriba.setBorder(new EmptyBorder(10, 10, 10, 10));
		FlowLayout fl_arriba = (FlowLayout) arriba.getLayout();
		fl_arriba.setAlignment(FlowLayout.LEFT);
		arriba.setBackground(new Color(41, 128, 185));
		central.add(arriba, BorderLayout.NORTH);

		lTitulo = new JLabel("Titulo");
		lTitulo.setForeground(Color.WHITE);
		lTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 24));
		arriba.add(lTitulo);

		contenido = new JPanel();
		contenido.setBorder(new EmptyBorder(10, 10, 10, 10));
		central.add(contenido, BorderLayout.CENTER);
		contenido.setLayout(new BorderLayout(20, 20));
	}

	public void init() {
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Clinica Veterinaria");
	}

	public void setContenido(JComponent c, String titulo) {

		lTitulo.setText(titulo);

		contenido.removeAll();
		contenido.add(c, BorderLayout.CENTER);
		contenido.repaint();
		contenido.revalidate();

	}

}
