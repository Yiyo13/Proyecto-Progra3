package com.tarea2.mvc.controllers;

import javax.swing.SwingUtilities;

import com.tarea2.mvc.models.ListaPedidos;
import com.tarea2.mvc.models.Pedido;
import com.tarea2.mvc.models.Server;
import com.tarea2.mvc.views.ViewServer;

public class ControllerServer {

	
	private ViewServer v;
	private ListaPedidos lp;
	private Server server;
	
	public ControllerServer() {
		v = new ViewServer();
		lp = new ListaPedidos();
		
	}
	
	public void init() {
		v.init();
		server = new Server(this);
		server.start();
		
		
	}
	
	public void nuevoPedido(Pedido pedido) {
		SwingUtilities.invokeLater(() -> {
			lp.agregar(pedido); // se agrega en el hilo de Swing para no modificar la lista mientras la vista la recorre
			v.mostrarPedidos(lp); //pendiente con la vista del server, sirve para que el hilo correcto toque la ventana
			v.mostrarTotales(lp);// de la vista para actualizar

		});
	}
}
