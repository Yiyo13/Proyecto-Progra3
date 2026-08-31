package com.proyecto.mvc.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Color;

public class ViewPrincipal extends JFrame {

	public JPanel contentPane;
	public JButton btnTareasPendientes;
	public JButton btnTareasCompletadas;
	public JButton btnCategoras;
	private JPanel panelContenido;
	

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 10));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setForeground(Color.GRAY);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(3, 1, 0, 10));
		
		btnTareasPendientes = new JButton("Tareas Pendientes");
		panel_1.add(btnTareasPendientes);
		
		btnTareasCompletadas = new JButton("Tareas Completadas");
		btnTareasCompletadas.setPreferredSize(new Dimension(151, 40));
		panel_1.add(btnTareasCompletadas);
		
		btnCategoras = new JButton("Categor\u00EDas");
		panel_1.add(btnCategoras);
		
		panelContenido = new JPanel();
		contentPane.add(panelContenido, BorderLayout.CENTER);
	}
	
	public void init() {
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Sistema Gestor de Tareas");
	}

	public void setContenido(JComponent c, String title) {
		setTitle("Sistema Gestor de Tareas - " + title);
		panelContenido.removeAll();
		panelContenido.add(c, BorderLayout.CENTER);
		panelContenido.repaint();
		panelContenido.revalidate();
	}
	
}
