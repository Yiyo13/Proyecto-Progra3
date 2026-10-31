package com.veterinaria.mvc.models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.veterinaria.mvc.models.Conexion;
import com.veterinaria.mvc.models.vo.Consulta;
import com.veterinaria.mvc.models.vo.Mascota;
import com.veterinaria.mvc.models.vo.Propietario;

public class DAOConsulta extends Conexion implements DAO<Consulta> {
	public DAOConsulta() {}

	@Override
	public ArrayList<Consulta> all() {
		ArrayList<Consulta> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select c.*, m.nombre as mnombre, m.especie, m.raza, m.fecha_nacimiento, p.id_propietario, p.nombre as pnombre, p.telefono, p.correo, p.direccion from consultas c inner join mascotas m using(id_mascota) inner join propietarios p using(id_propietario) order by c.fecha desc, c.id_consulta desc;";

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
				Mascota mascota = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("mnombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
				Consulta item = new Consulta(rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						mascota);
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
	public Consulta find(int id) {
		Consulta item = null;

		Connection con = conectar();
		String sql = "select c.*, m.nombre as mnombre, m.especie, m.raza, m.fecha_nacimiento, p.id_propietario, p.nombre as pnombre, p.telefono, p.correo, p.direccion from consultas c inner join mascotas m using(id_mascota) inner join propietarios p using(id_propietario) where c.id_consulta = ?;";

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
				Mascota mascota = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("mnombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
				item = new Consulta(
						rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						mascota);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return item;
	}

	// REPORTE 1: devuelve {cantidad, total} de las consultas entre dos fechas
	public double[] resumenFechas(Date inicio, Date fin) {
		double[] resumen = {0, 0};

		Connection con = conectar();
		String sql = "select count(*) as cantidad, coalesce(sum(costo), 0) as total from consultas where fecha between ? and ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, inicio);
			ps.setDate(2, fin);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resumen[0] = rs.getInt("cantidad");
				resumen[1] = rs.getDouble("total");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return resumen;
	}

	// REPORTE 2: las últimas 3 consultas registradas
	public ArrayList<Consulta> ultimas() {
		ArrayList<Consulta> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select c.*, m.nombre as mnombre, m.especie, m.raza, m.fecha_nacimiento, p.id_propietario, p.nombre as pnombre, p.telefono, p.correo, p.direccion from consultas c inner join mascotas m using(id_mascota) inner join propietarios p using(id_propietario) order by c.fecha desc, c.id_consulta desc limit 3;";

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
				Mascota mascota = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("mnombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
				Consulta item = new Consulta(rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						mascota);
				data.add(item);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			desconectar(con);
		}
		return data;
	}

	// REPORTE 3: historial de consultas de una mascota
	public ArrayList<Consulta> historialByMascota(int idMascota) {
		ArrayList<Consulta> data = new ArrayList<>();

		Connection con = conectar();

		String sql = "select c.*, m.nombre as mnombre, m.especie, m.raza, m.fecha_nacimiento, p.id_propietario, p.nombre as pnombre, p.telefono, p.correo, p.direccion from consultas c inner join mascotas m using(id_mascota) inner join propietarios p using(id_propietario) where c.id_mascota = ? order by c.fecha desc, c.id_consulta desc;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idMascota);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Propietario propietario = new Propietario(
						rs.getInt("id_propietario"),
						rs.getString("pnombre"),
						rs.getString("telefono"),
						rs.getString("correo"),
						rs.getString("direccion"));
				Mascota mascota = new Mascota(
						rs.getInt("id_mascota"),
						rs.getString("mnombre"),
						rs.getString("especie"),
						rs.getString("raza"),
						rs.getDate("fecha_nacimiento"),
						propietario);
				Consulta item = new Consulta(rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						mascota);
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
	public boolean store(Consulta item) {
		Connection con = conectar();

		String sql = "insert into consultas(id_mascota, fecha, motivo, diagnostico, costo) values (?, ?, ?, ?, ?);";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getMascota().getId());
			ps.setDate(2, item.getFecha());
			ps.setString(3, item.getMotivo());
			ps.setString(4, item.getDiagnostico());
			ps.setDouble(5, item.getCosto());
			ps.execute();


			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				item = new Consulta(
						rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						item.getMascota());

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
	public boolean update(Consulta item, int id) {
		Connection con = conectar();

		String sql = "update consultas set id_mascota = ?, fecha = ?, motivo = ?, diagnostico = ?, costo = ? where id_consulta = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getMascota().getId());
			ps.setDate(2, item.getFecha());
			ps.setString(3, item.getMotivo());
			ps.setString(4, item.getDiagnostico());
			ps.setDouble(5, item.getCosto());
			ps.setInt(6, id);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Consulta(
						rs.getInt("id_consulta"),
						rs.getDate("fecha"),
						rs.getString("motivo"),
						rs.getString("diagnostico"),
						rs.getDouble("costo"),
						item.getMascota());


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

		String sql = "delete from consultas where id_consulta = ?;";

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