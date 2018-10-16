package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MADRES database table.
 * 
 */
@Entity
@Table(name="MADRES")
@NamedQuery(name="Madre.findAll", query="SELECT m FROM Madre m")
public class Madre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_MADRE")
	private long idMadre;

	@Column(name="NRO_CRVN_MADRE")
	private String nroCrvnMadre;

	//bi-directional many-to-one association to Ternera
	@OneToMany(mappedBy="madre")
	private List<Ternera> terneras;

	public Madre() {
	}

	public long getIdMadre() {
		return this.idMadre;
	}

	public void setIdMadre(long idMadre) {
		this.idMadre = idMadre;
	}

	public String getNroCrvnMadre() {
		return this.nroCrvnMadre;
	}

	public void setNroCrvnMadre(String nroCrvnMadre) {
		this.nroCrvnMadre = nroCrvnMadre;
	}

	public List<Ternera> getTerneras() {
		return this.terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	public Ternera addTernera(Ternera ternera) {
		getTerneras().add(ternera);
		ternera.setMadre(this);

		return ternera;
	}

	public Ternera removeTernera(Ternera ternera) {
		getTerneras().remove(ternera);
		ternera.setMadre(null);

		return ternera;
	}

}