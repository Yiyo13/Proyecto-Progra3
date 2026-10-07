package com.tarea2.mvc.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import com.tarea2.mvc.models.ListaPedidos;
import com.tarea2.mvc.models.Pedido;
import com.tarea2.mvc.models.Platillo;

public class ViewServer extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JLabel lblEntregados;
	private JLabel lblNoEntregados;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JPanel panelPedidos;

	public ViewServer() {
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

		JLabel lblNewLabel = new JLabel("LISTA DE PEDIDOS");
		lblNewLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);

		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setBackground(Color.DARK_GRAY);
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 2, 10, 0));

		lblEntregados = new JLabel();
		lblEntregados.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblEntregados.setBackground(Color.LIGHT_GRAY);
		lblEntregados.setOpaque(true);
		panel_1.add(lblEntregados);

		lblNoEntregados = new JLabel();
		lblNoEntregados.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNoEntregados.setBackground(Color.LIGHT_GRAY);
		lblNoEntregados.setOpaque(true);
		panel_1.add(lblNoEntregados);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout(0, 0));
		scrollPane.setViewportView(panel_2);

		panelPedidos = new JPanel();
		panelPedidos.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_2.add(panelPedidos, BorderLayout.NORTH);
		panelPedidos.setLayout(new GridLayout(0, 2, 10, 10));

		mostrarTotales(new ListaPedidos());
	}

	public void init() {
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Restaurante");
	}

	public void mostrarPedidos(ListaPedidos lp) {

		panelPedidos.removeAll();

		for (Pedido pedido : lp.getPedidos()) {
			panelPedidos.add(crearTarjeta(pedido));
		}

		panelPedidos.revalidate();
		panelPedidos.repaint();

	}

	public void mostrarTotales(ListaPedidos lp) {

		lblEntregados.setText("<html>Entregados: " + lp.cantidadEntregados()
				+ "<br>TOTAL: " + formato(lp.totalEntregados()) + "</html>");

		lblNoEntregados.setText("<html>No Entregados: " + lp.cantidadNoEntregados()
				+ "<br>TOTAL: " + formato(lp.totalNoEntregados()) + "</html>");

	}

	private JPanel crearTarjeta(Pedido pedido) {

		JPanel tarjeta = new JPanel();
		tarjeta.setBackground(Color.LIGHT_GRAY);
		tarjeta.setBorder(new EmptyBorder(10, 10, 10, 10));
		tarjeta.setLayout(new BorderLayout(0, 10));

		StringBuilder sb = new StringBuilder();
		sb.append("Pedido ").append(pedido.getIdPedido()).append(": ").append(pedido.getNombreCliente()).append("\n");
		sb.append("Platillos:\n");

		for (Platillo p : pedido.getListaPlatillos().getPlatillos()) {
			sb.append("   - ").append(p).append("\n");
		}

		sb.append("--\n");
		sb.append("TOTAL: ").append(formato(pedido.getTotal())).append("\n");
		sb.append("Estado: ").append(pedido.isEntregado() ? "Entregado" : "No entregado");

		JTextArea txtPedido = new JTextArea(sb.toString());
		txtPedido.setEditable(false);
		txtPedido.setOpaque(false);
		txtPedido.setFont(new JLabel().getFont());
		tarjeta.add(txtPedido, BorderLayout.CENTER);

		if (!pedido.isEntregado()) {

			JPanel panelBoton = new JPanel();
			panelBoton.setOpaque(false);
			panelBoton.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			tarjeta.add(panelBoton, BorderLayout.SOUTH);

			JButton btnEntregar = new JButton("Entregar");
			btnEntregar.setForeground(Color.WHITE);
			btnEntregar.setBackground(Color.BLACK);
			panelBoton.add(btnEntregar);

		}

		return tarjeta;

	}

	private String formato(double monto) {
		return String.format("%.0f", monto);
	}

}