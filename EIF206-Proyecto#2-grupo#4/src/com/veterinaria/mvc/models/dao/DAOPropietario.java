package com.veterinaria.mvc.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.veterinaria.mvc.models.Conexion;
import com.veterinaria.mvc.models.vo.Propietario;

public class DAOPropietario extends Conexion implements DAO<Propietario> {
	public DAOPropietario() {}

	@Override
	public ArrayList<Propietario> all() {
		ArrayList<Propietario> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select * from propietarios order by nombre";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario item = new Propietario(rs.getInt("id_propietario"),
						rs.getString("nombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));
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
						rs.getString("nombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));

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

		String sql = "insert into propietarios(nombre, telefono, correo, direccion) values (?, ?, ?, ?);";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getNombre());
			ps.setString(2, item.getTelefono());
			ps.setString(3, item.getCorreo());
			ps.setString(4, item.getDireccion());
			ps.execute();


			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("nombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));

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

		String sql = "update propietarios set nombre = ?, telefono = ?, correo = ?, direccion = ? where id_propietario = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getNombre());
			ps.setString(2, item.getTelefono());
			ps.setString(3, item.getCorreo());
			ps.setString(4, item.getDireccion());
			ps.setInt(5, id);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("nombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));


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
			ps.setInt(1, id);
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