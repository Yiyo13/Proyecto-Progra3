package com.gestionseries.mvc.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel contenido;
	public JLabel lTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {

		// PROPIEDADES PRINCIPALES
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// PANELES
		JPanel arriba = new JPanel();
		arriba.setBackground(new Color(40, 40, 40));
		arriba.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(arriba, BorderLayout.NORTH);

		contenido = new JPanel();
		contentPane.add(contenido, BorderLayout.CENTER);
		contenido.setLayout(new BorderLayout(0, 0));

		// COMPONENTES: arriba
		lTitulo = new JLabel("Sistema Gestor de Series");
		lTitulo.setForeground(Color.WHITE);
		lTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		arriba.add(lTitulo);
	}

	public void init() {
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Sistema Gestor de Series");
	}

	public void setContenido(JComponent c, String titulo) {

		lTitulo.setText(titulo);

		contenido.removeAll();
		contenido.add(c, BorderLayout.CENTER);
		contenido.repaint();
		contenido.revalidate();

	}

}