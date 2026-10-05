package com.tarea2.mvc.controllers;

import javax.swing.SwingUtilities;

import com.tarea2.mvc.models.Pedido;
import com.tarea2.mvc.models.Server;

public class ControllerServer {

	
	private ViewServer v;
	private ListaPedidos lp;
	private Server server;
	
	public ControllerServer() {
		v = new ViewServer();
		lp = new ListaPedidos();
		
	}
	
	public void init() {
		server = new Server(this);
		server.start();
		
		
	}
	
	public void nuevoPedido(Pedido pedido) {
		lp.agregar(pedido);
		
		SwingUtilities.invokeLater(() -> {
			v.mostrarPedidos(lista); //pendiente con la vista del server, sirve para que el hilo correcto toque la ventana
			v.mostrarTotales(lista);// de la vista para actualizar
			
		});
	}
}
