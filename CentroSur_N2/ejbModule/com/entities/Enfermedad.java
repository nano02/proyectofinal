package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="ENFERMEDADES")
@NamedQuery(name="Enfermedad.findAll", query="SELECT e FROM Enfermedad e")
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ENFERMEDAD")
	private long idEnfermedad;

	private Long grado;

	private String nombre;

	//bi-directional many-to-one association to Tratamiento
	@OneToMany(mappedBy="enfermedad")
	private List<Tratamiento> tratamientos;

	public Enfermedad() {
	}

	public long getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Long getGrado() {
		return this.grado;
	}

	public void setGrado(Long grado) {
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
		tratamiento.setEnfermedad(this);

		return tratamiento;
	}

	public Tratamiento removeTratamiento(Tratamiento tratamiento) {
		getTratamientos().remove(tratamiento);
		tratamiento.setEnfermedad(null);

		return tratamiento;
	}

}