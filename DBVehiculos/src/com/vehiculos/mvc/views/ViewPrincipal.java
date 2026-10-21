package com.vehiculos.mvc.views;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;

public class ViewPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contenido;
	public JLabel lTitulo;
	public JButton btnVehiculos;
	public JButton btnPropietarios;
	private JPanel menu;

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 522);
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
		menu.setPreferredSize(new Dimension(130, 100));
		lateral.add(menu);
		menu.setLayout(new GridLayout(0, 1, 10, 10));

		btnPropietarios = new JButton("Propietarios");
		btnPropietarios.setOpaque(false);
		menu.add(btnPropietarios);

		btnVehiculos = new JButton("Veh\u00EDculos");
		btnVehiculos.setOpaque(false);
		menu.add(btnVehiculos);

		JPanel central = new JPanel();
		contentPane.add(central, BorderLayout.CENTER);
		central.setLayout(new BorderLayout(0, 0));

		JPanel arriba = new JPanel();
		arriba.setBorder(new EmptyBorder(10, 10, 10, 10));
		FlowLayout fl_arriba = (FlowLayout) arriba.getLayout();
		fl_arriba.setAlignment(FlowLayout.LEFT);
		arriba.setBackground(SystemColor.activeCaption);
		central.add(arriba, BorderLayout.NORTH);

		lTitulo = new JLabel("Titulo");
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
		this.setTitle("Conexión Java - MySQL");
	}

	public void setContenido(JComponent c, String titulo) {

		lTitulo.setText(titulo);

		contenido.removeAll();
		contenido.add(c, BorderLayout.CENTER);
		contenido.repaint();
		contenido.revalidate();

	}

}
