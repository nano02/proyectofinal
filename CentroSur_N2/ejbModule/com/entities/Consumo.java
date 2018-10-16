package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CONSUMOS database table.
 * 
 */
@Entity
@Table(name="CONSUMOS")
@NamedQuery(name="Consumo.findAll", query="SELECT c FROM Consumo c")
public class Consumo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CONSUMO")
	private long idConsumo;

	private BigDecimal cantidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Alimento
	@ManyToOne
	@JoinColumn(name="ID_ALIMENTO")
	private Alimento alimento;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public Consumo() {
	}

	public long getIdConsumo() {
		return this.idConsumo;
	}

	public void setIdConsumo(long idConsumo) {
		this.idConsumo = idConsumo;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alimento getAlimento() {
		return this.alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}