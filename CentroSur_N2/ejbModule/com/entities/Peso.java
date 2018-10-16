package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PESOS database table.
 * 
 */
@Entity
@Table(name="PESOS")
@NamedQuery(name="Peso.findAll", query="SELECT p FROM Peso p")
public class Peso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PESO")
	private long idPeso;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private double peso;

	@Column(name="TIPO_REGISTRO")
	private BigDecimal tipoRegistro;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public Peso() {
	}

	public long getIdPeso() {
		return this.idPeso;
	}

	public void setIdPeso(long idPeso) {
		this.idPeso = idPeso;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public BigDecimal getTipoRegistro() {
		return this.tipoRegistro;
	}

	public void setTipoRegistro(BigDecimal tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}