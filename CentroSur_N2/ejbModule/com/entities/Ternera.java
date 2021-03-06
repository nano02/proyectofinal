package com.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TERNERAS database table.
 * 
 */
@Entity
@Table(name="TERNERAS")
@NamedQuery(name="Ternera.findAll", query="SELECT t FROM Ternera t")
public class Ternera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TERNERA")
	private long idTernera;

	private Long baja;
	private Double pesoNacimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC")
	private Date fechaNac;

	@Column(name="NRO_CARAVANA")
	private String nroCaravana;

	@Column(name="PARTO")
	@Enumerated(EnumType.STRING)
	private TipoParto parto;

	@Column(name="RAZA")
	@Enumerated(EnumType.STRING)
	private RazaTernera raza;

	//bi-directional one-to-one association to Baja
	@OneToOne(mappedBy="ternera")
	private Baja bajaBean;

	//bi-directional many-to-one association to Consumo
	@OneToMany(mappedBy="ternera")
	private List<Consumo> consumos;

	//bi-directional many-to-one association to ConsumosMedicamento
	@OneToMany(mappedBy="ternera")
	@JsonIgnore
	private List<ConsumosMedicamento> consumosMedicamentos;

	//bi-directional many-to-one association to Peso
	@OneToMany(mappedBy="ternera")
	@JsonIgnore
	private List<Peso> pesos;

	//bi-directional many-to-one association to Guachera
	@ManyToOne
	@JoinColumn(name="ID_GUACHERA")
	private Guachera guachera;

	//bi-directional many-to-one association to Madre
	@ManyToOne
	@JoinColumn(name="ID_MADRE")
	@JsonIgnore
	private Madre madre;

	//bi-directional many-to-one association to Padre
	@ManyToOne
	@JoinColumn(name="ID_PADRE")
	@JsonIgnore
	private Padre padre;

	//bi-directional many-to-one association to Tratamiento
	@OneToMany(mappedBy="ternera")
	private List<Tratamiento> tratamientos;

	public Ternera() {
	}

	public long getIdTernera() {
		return this.idTernera;
	}

	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}

	public Long getBaja() {
		return this.baja;
	}

	public void setBaja(Long baja) {
		this.baja = baja;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNroCaravana() {
		return this.nroCaravana;
	}

	public void setNroCaravana(String nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public TipoParto getParto() {
		return parto;
	}

	public void setParto(TipoParto parto) {
		this.parto = parto;
	}

	public RazaTernera getRaza() {
		return raza;
	}

	public void setRaza(RazaTernera raza) {
		this.raza = raza;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Baja getBajaBean() {
		return this.bajaBean;
	}

	public void setBajaBean(Baja bajaBean) {
		this.bajaBean = bajaBean;
	}

	public List<Consumo> getConsumos() {
		return this.consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public Consumo addConsumo(Consumo consumo) {
		getConsumos().add(consumo);
		consumo.setTernera(this);

		return consumo;
	}

	public Consumo removeConsumo(Consumo consumo) {
		getConsumos().remove(consumo);
		consumo.setTernera(null);

		return consumo;
	}

	public List<ConsumosMedicamento> getConsumosMedicamentos() {
		return this.consumosMedicamentos;
	}

	public void setConsumosMedicamentos(List<ConsumosMedicamento> consumosMedicamentos) {
		this.consumosMedicamentos = consumosMedicamentos;
	}

	public ConsumosMedicamento addConsumosMedicamento(ConsumosMedicamento consumosMedicamento) {
		getConsumosMedicamentos().add(consumosMedicamento);
		consumosMedicamento.setTernera(this);

		return consumosMedicamento;
	}

	public ConsumosMedicamento removeConsumosMedicamento(ConsumosMedicamento consumosMedicamento) {
		getConsumosMedicamentos().remove(consumosMedicamento);
		consumosMedicamento.setTernera(null);

		return consumosMedicamento;
	}

	public List<Peso> getPesos() {
		return this.pesos;
	}

	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	public Peso addPeso(Peso peso) {
		getPesos().add(peso);
		peso.setTernera(this);

		return peso;
	}

	public Peso removePeso(Peso peso) {
		getPesos().remove(peso);
		peso.setTernera(null);

		return peso;
	}

	public Double getPesoNacimiento() {
		return pesoNacimiento;
	}

	public void setPesoNacimiento(Double pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}

	public Guachera getGuachera() {
		return this.guachera;
	}

	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}

	public Madre getMadre() {
		return this.madre;
	}

	public void setMadre(Madre madre) {
		this.madre = madre;
	}

	public Padre getPadre() {
		return this.padre;
	}

	public void setPadre(Padre padre) {
		this.padre = padre;
	}

	public List<Tratamiento> getTratamientos() {
		return this.tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public Tratamiento addTratamiento(Tratamiento tratamiento) {
		getTratamientos().add(tratamiento);
		tratamiento.setTernera(this);

		return tratamiento;
	}

	public Tratamiento removeTratamiento(Tratamiento tratamiento) {
		getTratamientos().remove(tratamiento);
		tratamiento.setTernera(null);

		return tratamiento;
	}

}