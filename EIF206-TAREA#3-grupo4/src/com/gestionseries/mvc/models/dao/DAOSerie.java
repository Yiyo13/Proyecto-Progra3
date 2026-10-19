package com.gestionseries.mvc.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gestionseries.mvc.models.Conexion;
import com.gestionseries.mvc.models.vo.Serie;

public class DAOSerie extends Conexion implements DAO<Serie> {

	public DAOSerie() {

	}

	@Override
	public ArrayList<Serie> all() {

		ArrayList<Serie> data = new ArrayList<>();

		Connection con = conectar();

		// JOIN con generos para completar nombreGenero
		String sql = "select s.id_serie, s.titulo, s.temporadas, s.calificacion, s.id_genero, "
				+ "g.nombre as nombre_genero "
				+ "from series s inner join generos g on s.id_genero = g.id_genero "
				+ "order by s.id_serie;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				data.add(crear(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			desconectar(con);
		}
		return data;
	}

	@Override
	public Serie find(int id) {

		Serie item = null;

		Connection con = conectar();

		String sql = "select s.id_serie, s.titulo, s.temporadas, s.calificacion, s.id_genero, "
				+ "g.nombre as nombre_genero "
				+ "from series s inner join generos g on s.id_genero = g.id_genero "
				+ "where s.id_serie = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				item = crear(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			desconectar(con);
		}
		return item;
	}

	@Override
	public boolean store(Serie item) {

		Connection con = conectar();

		String sql = "insert into series(titulo, temporadas, calificacion, id_genero) values (?,?,?,?);";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getTitulo());
			ps.setInt(2, item.getCantTemporadas());
			ps.setDouble(3, item.getCalificacion());
			ps.setInt(4, item.getIdGenero());
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar(con);
		}
		return true;
	}

	@Override
	public boolean update(Serie item, int id) {

		Connection con = conectar();

		String sql = "update series set titulo = ?, temporadas = ?, calificacion = ?, id_genero = ? "
				+ "where id_serie = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getTitulo());
			ps.setInt(2, item.getCantTemporadas());
			ps.setDouble(3, item.getCalificacion());
			ps.setInt(4, item.getIdGenero());
			ps.setInt(5, id);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar(con);
		}
		return true;
	}

	@Override
	public boolean destroy(int id) {

		Connection con = conectar();

		String sql = "delete from series where id_serie = ?;";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			desconectar(con);
		}
		return true;
	}

	// AUXILIARES
	private Serie crear(ResultSet rs) throws Exception {
		return new Serie(
				rs.getInt("id_serie"),
				rs.getString("titulo"),
				rs.getInt("temporadas"),
				rs.getDouble("calificacion"),
				rs.getInt("id_genero"),
				rs.getString("nombre_genero"));
	}

}