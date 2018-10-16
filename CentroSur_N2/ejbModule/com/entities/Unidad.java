package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the UnidadS database table.
 * 
 */
@Entity
@Table(name="Unidades")
@NamedQuery(name="Unidad.findAll", query="SELECT u FROM Unidad u")
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_UNIDAD")
	private long idUnidad;

	private String descripcion;

	//bi-directional many-to-one association to Alimento
	@OneToMany(mappedBy="unidad")
	private List<Alimento> alimentos;

	public Unidad() {
	}

	public long getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Alimento> getAlimentos() {
		return this.alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public Alimento addAlimento(Alimento alimento) {
		getAlimentos().add(alimento);
		alimento.setUnidad(this);

		return alimento;
	}

	public Alimento removeAlimento(Alimento alimento) {
		getAlimentos().remove(alimento);
		alimento.setUnidad(null);

		return alimento;
	}

}