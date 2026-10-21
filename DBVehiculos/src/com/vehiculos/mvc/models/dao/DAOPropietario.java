package com.vehiculos.mvc.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vehiculos.mvc.models.Conexion;
import com.vehiculos.mvc.models.vo.Propietario;

public class DAOPropietario extends Conexion implements DAO<Propietario> {
	public DAOPropietario() {

	}

	@Override
	public ArrayList<Propietario> all() {

		ArrayList<Propietario> data = new ArrayList<>();


		Connection con = conectar();

		String sql = "select * from propietarios;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);
				data.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return data;
	}

	@Override
	public Propietario find(int id) {
		Propietario item = null;

		Connection con = conectar();

		String sql = "select * from propietarios where id_propietario = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return item;
	}

	@Override
	public boolean store(Propietario item) {
		Connection con = conectar();

		String sql = "insert into propietarios(nombre, telefono, edad) values (?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getNombre());
			ps.setString(2, item.getTelefono());
			ps.setInt(3,item.getEdad());
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);
			}


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			desconectar(con);
		}
		return true;
	}

	@Override
	public boolean update(Propietario item, int id) {
		Connection con = conectar();

		String sql = "update propietarios set nombre = ?, telefono = ?,edad = ?, where id_propietario = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getNombre());
			ps.setString(2, item.getTelefono());
			ps.setInt(3,item.getEdad());
			ps.setInt(4, id);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);
			}


		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		}finally {
			desconectar(con);
		}
		return true;
	}

	@Override
	public boolean destroy(int id) {
			Connection con = conectar();

		String sql = "delete from propietarios where id_propietario = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.execute();


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			desconectar(con);
		}
		return true;
	}

}
