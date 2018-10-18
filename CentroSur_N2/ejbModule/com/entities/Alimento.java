package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ALIMENTOS database table.
 * 
 */
@Entity
@Table(name="ALIMENTOS")
@NamedQuery(name="Alimento.findAll", query="SELECT a FROM Alimento a")
public class Alimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ALIMENTO")
	private long idAlimento;

	private Long cantidad;

	@Column(name="COSTO_UNITARIO")
	private Long costoUnitario;

	private String nombre;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private Unidad unidad;

	//bi-directional many-to-one association to Consumo
	@OneToMany(mappedBy="alimento")
	private List<Consumo> consumos;

	public Alimento() {
	}

	public long getIdAlimento() {
		return this.idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getCostoUnitario() {
		return this.costoUnitario;
	}

	public void setCostoUnitario(Long costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<Consumo> getConsumos() {
		return this.consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public Consumo addConsumo(Consumo consumo) {
		getConsumos().add(consumo);
		consumo.setAlimento(this);

		return consumo;
	}

	public Consumo removeConsumo(Consumo consumo) {
		getConsumos().remove(consumo);
		consumo.setAlimento(null);

		return consumo;
	}

	public Alimento(Long cantidad, Long costoUnitario, String nombre, Unidad unidad) {
		super();
		this.cantidad = cantidad;
		this.costoUnitario = costoUnitario;
		this.nombre = nombre;
		this.unidad = unidad;
	}
	
	
	
	

}