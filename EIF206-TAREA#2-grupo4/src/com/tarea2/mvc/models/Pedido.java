package com.tarea2.mvc.models;

import java.io.Serializable;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private String nombreCliente;
	private boolean entregado;
	
	public Pedido(int idPedido, String nombreCliente, boolean entregado) {
		this.idPedido = idPedido;
		this.nombreCliente = nombreCliente;
		this.entregado = entregado;
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
	public boolean isEntregado() {
		return entregado;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", nombreCliente=" + nombreCliente + ", entregado=" + entregado + "]";
	}
	
	
	
	

}
