package com.tarea2.mvc.models;

import java.io.Serializable;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idPedido;
	private String nombreCliente;
	private ListaPlatillos listaPlatillos;
	private boolean entregado;

	public Pedido(String nombreCliente, ListaPlatillos listaPlatillos) {
		this.nombreCliente = nombreCliente;
		this.listaPlatillos = listaPlatillos;
		this.entregado = false;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public ListaPlatillos getListaPlatillos() {
		return listaPlatillos;
	}

	public void setListaPlatillos(ListaPlatillos listaPlatillos) {
		this.listaPlatillos = listaPlatillos;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public double getTotal() {
		return listaPlatillos.getTotal();
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", nombreCliente=" + nombreCliente
				+ ", entregado=" + entregado + "]";
	}
}