package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the CONSUMOS_MEDICAMENTOS database table.
 * 
 */
@Entity
@Table(name="CONSUMOS_MEDICAMENTOS")
@NamedQuery(name="ConsumosMedicamento.findAll", query="SELECT c FROM ConsumosMedicamento c")
public class ConsumosMedicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CONS_MED")
	private long idConsMed;

	private double cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="ID_MEDICAMENTO")
	private Medicamento medicamento;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public ConsumosMedicamento() {
	}

	public long getIdConsMed() {
		return this.idConsMed;
	}

	public void setIdConsMed(long idConsMed) {
		this.idConsMed = idConsMed;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Medicamento getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}