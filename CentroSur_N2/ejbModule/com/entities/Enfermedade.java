package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="ENFERMEDADES")
@NamedQuery(name="Enfermedade.findAll", query="SELECT e FROM Enfermedade e")
public class Enfermedade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ENFERMEDAD")
	private long idEnfermedad;

	private BigDecimal grado;

	private String nombre;

	//bi-directional many-to-one association to Tratamiento
	@OneToMany(mappedBy="enfermedade")
	private List<Tratamiento> tratamientos;

	public Enfermedade() {
	}

	public long getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public BigDecimal getGrado() {
		return this.grado;
	}

	public void setGrado(BigDecimal grado) {
		this.grado = grado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tratamiento> getTratamientos() {
		return this.tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Tratamiento addTratamiento(Tratamiento tratamiento) {
		getTratamientos().add(tratamiento);
		tratamiento.setEnfermedade(this);

		return tratamiento;
	}

	public Tratamiento removeTratamiento(Tratamiento tratamiento) {
		getTratamientos().remove(tratamiento);
		tratamiento.setEnfermedade(null);

		return tratamiento;
	}

}