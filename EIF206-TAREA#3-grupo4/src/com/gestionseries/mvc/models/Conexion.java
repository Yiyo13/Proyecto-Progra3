package com.gestionseries.mvc.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	private String host, user, pass,db, url;
	
	public Conexion() {
		host = "localhost";
		user = "root";
		pass = "";
		db ="db_gestion_series";
		url = "jdbc:mysql://" + host + "/" + db;
	}
	
	public Connection conectar() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Connection");
			conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Conexion exitosa");
			
			
		} catch (Exception e) {
			System.err.println("Conexion fallida"+ e.getMessage());
		}
		
		return conn;
		
	}
	
	public void desconectar(Connection conn) {
		try {
			conn.close();
			System.out.println("Desconectado...");
		} catch (Exception e) {
			
		}
	}
}
