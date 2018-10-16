package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEDICAMENTOS database table.
 * 
 */
@Entity
@Table(name="MEDICAMENTOS")
@NamedQuery(name="Medicamento.findAll", query="SELECT m FROM Medicamento m")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MEDICAMENTO")
	private long idMedicamento;

	private BigDecimal costo;

	private String dosis;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

	private String nombre;

	//bi-directional many-to-one association to ConsumosMedicamento
	@OneToMany(mappedBy="medicamento")
	private List<ConsumosMedicamento> consumosMedicamentos;

	public Medicamento() {
	}

	public long getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ConsumosMedicamento> getConsumosMedicamentos() {
		return this.consumosMedicamentos;
	}

	public void setConsumosMedicamentos(List<ConsumosMedicamento> consumosMedicamentos) {
		this.consumosMedicamentos = consumosMedicamentos;
	}

	public ConsumosMedicamento addConsumosMedicamento(ConsumosMedicamento consumosMedicamento) {
		getConsumosMedicamentos().add(consumosMedicamento);
		consumosMedicamento.setMedicamento(this);

		return consumosMedicamento;
	}

	public ConsumosMedicamento removeConsumosMedicamento(ConsumosMedicamento consumosMedicamento) {
		getConsumosMedicamentos().remove(consumosMedicamento);
		consumosMedicamento.setMedicamento(null);

		return consumosMedicamento;
	}

}