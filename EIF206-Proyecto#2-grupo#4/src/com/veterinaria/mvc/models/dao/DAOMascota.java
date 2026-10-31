package com.veterinaria.mvc.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.veterinaria.mvc.models.Conexion;
import com.veterinaria.mvc.models.vo.Mascota;
import com.veterinaria.mvc.models.vo.Propietario;

public class DAOMascota extends Conexion implements DAO<Mascota> {
	public DAOMascota() {}

	@Override
	public ArrayList<Mascota> all() {
		ArrayList<Mascota> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select m.*, p.nombre as pnombre, p.telefono, p.correo, p.direccion from mascotas m inner join propietarios p using(id_propietario) order by m.nombre;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario propietario = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("pnombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));
				Mascota item = new Mascota(rs.getInt("id_mascota"),
						rs.getString("nombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
				data.add(item);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return data;
	}

	// Para el combo de mascotas en Consultas: solo las del propietario elegido
	public ArrayList<Mascota> allByPropietario(int idPropietario) {
		ArrayList<Mascota> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select m.*, p.nombre as pnombre, p.telefono, p.correo, p.direccion from mascotas m inner join propietarios p using(id_propietario) where m.id_propietario = ? order by m.nombre;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idPropietario);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario propietario = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("pnombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));
				Mascota item = new Mascota(rs.getInt("id_mascota"),
						rs.getString("nombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
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
	public Mascota find(int id) {
		Mascota item = null;

		Connection con = conectar();
		String sql = "select m.*, p.nombre as pnombre, p.telefono, p.correo, p.direccion from mascotas m inner join propietarios p using(id_propietario) where m.id_mascota = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Propietario propietario = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("pnombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));
				item = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("nombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return item;
	}

	@Override
	public boolean store(Mascota item) {
		Connection con = conectar();

		String sql = "insert into mascotas(id_propietario, nombre, especie, raza, fecha_nacimiento) values (?, ?, ?, ?, ?);";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getPropietario().getId());
			ps.setString(2, item.getNombre());
			ps.setString(3, item.getEspecie());
			ps.setString(4, item.getRaza());
			ps.setDate(5, item.getFechaNacimiento());
			ps.execute();


			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				item = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("nombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						item.getPropietario());

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
	public boolean update(Mascota item, int id) {
		Connection con = conectar();

		String sql = "update mascotas set id_propietario = ?, nombre = ?, especie = ?, raza = ?, fecha_nacimiento = ? where id_mascota = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getPropietario().getId());
			ps.setString(2, item.getNombre());
			ps.setString(3, item.getEspecie());
			ps.setString(4, item.getRaza());
			ps.setDate(5, item.getFechaNacimiento());
			ps.setInt(6, id);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("nombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						item.getPropietario());


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

		String sql = "delete from mascotas where id_mascota = ?;";

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