package com.tarea2.mvc.models;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.tarea2.mvc.controllers.ControllerServer;

public class Server extends Thread {

	private static final int PORT = 5000;

	private ServerSocket server;
	private ArrayList<ClientHandler> clientes;
	private ControllerServer cs;

	public Server(ControllerServer cs) {


		this.cs = cs;
		this.clientes = new ArrayList<>();
	}

	public void run() {
		try {
			server = new ServerSocket(PORT);
			System.out.println("Iniciando servidor");

			while(true) {
				Socket client = server.accept();
				ClientHandler ch = new ClientHandler(client);
				synchronized(clientes) {
					clientes.add(ch);
				}
				ch.start();
			}

		} catch (Exception e) {
			System.out.println("Servidor detenido.");
			e.printStackTrace();
		}


	}

	public class ClientHandler extends Thread{
		private Socket client;
		private ObjectInputStream in;

		public ClientHandler(Socket client) {
			this.client = client;
		}

		public void run() {
			try {
				in = new ObjectInputStream(client.getInputStream());
				Pedido pedido = (Pedido) in.readObject();
				cs.nuevoPedido(pedido);

				close();

				synchronized(clientes) {
					clientes.remove(this);
				}




			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		public void close() {
			try {
				in.close();
				client.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
