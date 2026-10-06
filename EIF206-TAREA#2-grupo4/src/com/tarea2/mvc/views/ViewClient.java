package com.tarea2.mvc.views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.tarea2.mvc.models.Platillo;

public class ViewClient extends JFrame {

	private JPanel contentPane;
	public JTextField tHost;
	public JButton btnConectar;
	private JLabel lblHost;
	private JPanel panel_2;
	private JLabel lblCliente;
	public JTextField tCliente;
	public JButton btnEnviar;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblPlatillo;
	private JPanel panel_6;
	public JComboBox<Platillo> cbxPlatillo;
	public JButton btnAgregar;
	public JButton btnQuitar;
	private JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel model;
	public JLabel lblTotal;

	public ViewClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("SOLICITAR PEDIDO");
		lblNewLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblHost = new JLabel("HOST");
		lblHost.setBackground(Color.WHITE);
		panel_1.add(lblHost);
		
		tHost = new JTextField();
		tHost.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tHost.setBackground(Color.WHITE);
		tHost.setOpaque(true);
		tHost.setColumns(15);
		panel_1.add(tHost);
		
		btnConectar = new JButton("CONECTAR");
		btnConectar.setBackground(Color.BLACK);
		btnConectar.setForeground(Color.WHITE);
		panel_1.add(btnConectar);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		lblCliente = new JLabel("Cliente:");
		panel_2.add(lblCliente);
		
		tCliente = new JTextField();
		tCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tCliente.setBackground(Color.LIGHT_GRAY);
		tCliente.setOpaque(true);
		tCliente.setBorder(new EmptyBorder(5, 5, 5, 5));
		tCliente.setColumns(20);
		panel_2.add(tCliente);
		
		btnEnviar = new JButton("Enviar Pedido");
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setBackground(Color.BLACK);
		panel_2.add(btnEnviar);
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 20));
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_4.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblPlatillo = new JLabel("Platillo");
		lblPlatillo.setPreferredSize(new Dimension(37, 25));
		lblPlatillo.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblPlatillo.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblPlatillo, BorderLayout.NORTH);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_5.add(panel_6, BorderLayout.CENTER);
		
		cbxPlatillo = new JComboBox<Platillo>();
		cbxPlatillo.setPreferredSize(new Dimension(600, 22));
		panel_6.add(cbxPlatillo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setBackground(Color.BLACK);
		panel_6.add(btnAgregar);
		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel(new String[] { "ID", "Nombre", "Precio" }, 0);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_7.setLayout(new BorderLayout(0, 0));
		panel_3.add(panel_7, BorderLayout.SOUTH);

		lblTotal = new JLabel("TOTAL: 0");
		panel_7.add(lblTotal, BorderLayout.WEST);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setForeground(Color.WHITE);
		btnQuitar.setBackground(Color.BLACK);
		panel_7.add(btnQuitar, BorderLayout.EAST);
	}
	
	
	public void allowPedido(boolean permitir) {
		tCliente.setEnabled(permitir);
		cbxPlatillo.setEnabled(permitir);
		btnAgregar.setEnabled(permitir);
		btnQuitar.setEnabled(permitir);
		btnEnviar.setEnabled(permitir);
	}

	public void init() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
