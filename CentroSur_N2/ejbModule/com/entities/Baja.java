package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BAJAS database table.
 * 
 */
@Entity
@Table(name="BAJAS")
@NamedQuery(name="Baja.findAll", query="SELECT b FROM Baja b")
public class Baja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TERNERA")
	private long idTernera;

	@Column(name="CAUSA_MUERTE")
	private String causaMuerte;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_MUERTE")
	private Date fechaMuerte;

	@Column(name="MOTIVO_BAJA")
	private String motivoBaja;

	//bi-directional one-to-one association to Ternera
	@OneToOne
	@JoinColumn(name="ID_TERNERA")
	private Ternera ternera;

	public Baja() {
	}

	public long getIdTernera() {
		return this.idTernera;
	}

	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}

	public String getCausaMuerte() {
		return this.causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaMuerte() {
		return this.fechaMuerte;
	}

	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public String getMotivoBaja() {
		return this.motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public Ternera getTernera() {
		return this.ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

}