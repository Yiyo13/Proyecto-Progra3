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
	
	public void conectar() {
		try {
			client = new Socket(host, PORT);
			out = new ObjectOutputStream(client.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void enviarPedido(Pedido pedido) {
		try {
			out.writeObject(pedido);
			out.flush();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
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
