package com.veterinaria.mvc.models.vo;

import java.sql.Date;

public class Consulta {
	private int id;
	private Date fecha;
	private String motivo;
	private String diagnostico;
	private double costo;
	private Mascota mascota;

	public Consulta(int id, Date fecha, String motivo, String diagnostico, double costo, Mascota mascota) {
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.costo = costo;
		this.mascota = mascota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", fecha=" + fecha + ", motivo=" + motivo + ", diagnostico=" + diagnostico
				+ ", costo=" + costo + ", mascota=" + mascota + "]";
	}
}