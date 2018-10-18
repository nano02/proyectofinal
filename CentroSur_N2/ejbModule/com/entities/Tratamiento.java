package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TRATAMIENTOS database table.
 * 
 */
@Entity
@Table(name="TRATAMIENTOS")
@NamedQuery(name="Tratamiento.findAll", query="SELECT t FROM Tratamiento t")
public class Tratamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TRATAMIENTO")
	private long idTratamiento;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

	private String observaciones;

	//bi-directional many-to-one association to Enfermedade
	@ManyToOne
	@JoinColumn(name="ID_ENFERMEDAD")
	private Enfermedad enfermedad;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public Tratamiento() {
	}

	public long getIdTratamiento() {
		return this.idTratamiento;
	}

	public void setIdTratamiento(long idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public Date getFechaDesde() {
		return this.fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Enfermedad getEnfermedad() {
		return this.enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}