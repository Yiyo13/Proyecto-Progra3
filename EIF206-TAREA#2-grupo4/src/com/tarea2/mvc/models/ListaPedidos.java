package com.tarea2.mvc.models;

import java.util.ArrayList;

public class ListaPedidos {

	private ArrayList<Pedido> pedidos;
	private int siguienteId;

	public ListaPedidos() {
		pedidos = new ArrayList<>();
		siguienteId = 1;
	}

	// el id lo asigna el servidor, no el cliente
	public void agregar(Pedido pedido) {
		pedido.setIdPedido(siguienteId++);
		pedidos.add(pedido);
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	// CALCULOS (parte superior vista del restaurante)

	public int cantidadEntregados() {

		int count = 0;

		for (Pedido pedido : pedidos) {
			if (pedido.isEntregado()) {
				count++;
			}
		}

		return count;

	}

	public int cantidadNoEntregados() {

		int count = 0;

		for (Pedido pedido : pedidos) {
			if (!pedido.isEntregado()) {
				count++;
			}
		}

		return count;

	}

	public double totalEntregados() {

		double total = 0;

		for (Pedido pedido : pedidos) {
			if (pedido.isEntregado()) {
				total += pedido.getTotal();
			}
		}

		return total;

	}

	public double totalNoEntregados() {

		double total = 0;

		for (Pedido pedido : pedidos) {
			if (!pedido.isEntregado()) {
				total += pedido.getTotal();
			}
		}

		return total;

	}

}
