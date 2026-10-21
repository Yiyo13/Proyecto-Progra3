package com.vehiculos.mvc.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.vehiculos.mvc.models.Conexion;
import com.vehiculos.mvc.models.vo.Propietario;
import com.vehiculos.mvc.models.vo.Vehiculo;

public class DAOVehiculo extends Conexion implements DAO<Vehiculo>{

	@Override
	public ArrayList<Vehiculo> all() {
		ArrayList<Vehiculo> data = new ArrayList<>();


		Connection con = conectar();

		String sql = "select * from vehiculos v inner join propietarios p on v.id_propietario = p.id_propietario;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario p = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);

				Vehiculo item = new Vehiculo(
						rs.getInt("id_vehiculo"),
						rs.getString("placa"),
						rs.getInt("id_propietario"),
						p
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
	public Vehiculo find(int id) {
		Vehiculo item = null;

		Connection con = conectar();

		String sql = "select * from vehiculos where id_vehiculo = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Propietario p = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);

				Vehiculo s = new Vehiculo(
						rs.getInt("id_vehiculo"),
						rs.getString("placa"),
						rs.getInt("id_propietario"),
						p
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
	public boolean store(Vehiculo item) {
		Connection con = conectar();

		String sql = "INSERT INTO vehiculos (id_vehiculo, placa, id_propietario) VALUES (?, ?, ?); "; 
				
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getId());
			ps.setString(2,item.getPlaca());
			ps.setObject(3,item.getPropietario());
			
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Propietario p = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);

				Vehiculo s = new Vehiculo(
						rs.getInt("id_vehiculo"),
						rs.getString("placa"),
						rs.getInt("id_propietario"),
						p
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
	public boolean update(Vehiculo item, int id) {
		Connection con = conectar();

		String sql = "UPDATE vehiculos SET id_propietario = ? WHERE (id_vehiculo = ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, item.getPlaca());
			ps.setObject(3,item.getPropietario());
		
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Propietario p = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("Nombre"),
						rs.getString("telefono"),
						rs.getInt("edad")
						);

				Vehiculo s = new Vehiculo(
						rs.getInt("id_vehiculo"),
						rs.getString("placa"),
						rs.getInt("id_propietario"),
						p
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

		String sql = "delete from vehiculos where id_vehiculo = ?;";
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



}
