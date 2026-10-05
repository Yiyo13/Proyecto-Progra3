package com.tarea2.mvc.models;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	
	private static final int PORT = 5000;
	
	private String host;
	private Socket client;
	private ObjectOutputStream out;
	public Client(String host) {
		this.host = host;
	}
	
	// retorna true si logro conectarse (el controller decide que mostrar)
	public boolean conectar() {
		try {
			client = new Socket(host, PORT);
			out = new ObjectOutputStream(client.getOutputStream());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// retorna true si el pedido se envio
	public boolean enviarPedido(Pedido pedido) {
		try {
			out.writeObject(pedido);
			out.flush();
			close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void close() {
		try {
			out.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
