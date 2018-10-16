package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GUACHERAS database table.
 * 
 */
@Entity
@Table(name="GUACHERAS")
@NamedQuery(name="Guachera.findAll", query="SELECT g FROM Guachera g")
public class Guachera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GUACHERA")
	private long idGuachera;

	@Column(name="NOM_GUACHERA")
	private String nomGuachera;

	//bi-directional many-to-one association to Ternera
	@OneToMany(mappedBy="guachera")
	private List<Ternera> terneras;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="guachera")
	private List<Usuario> usuarios;

	public Guachera() {
	}

	public long getIdGuachera() {
		return this.idGuachera;
	}

	public void setIdGuachera(long idGuachera) {
		this.idGuachera = idGuachera;
	}

	public String getNomGuachera() {
		return this.nomGuachera;
	}

	public void setNomGuachera(String nomGuachera) {
		this.nomGuachera = nomGuachera;
	}

	public List<Ternera> getTerneras() {
		return this.terneras;
	}

	public void setTerneras(List<Ternera> terneras) {
		this.terneras = terneras;
	}

	public Ternera addTernera(Ternera ternera) {
		getTerneras().add(ternera);
		ternera.setGuachera(this);

		return ternera;
	}

	public Ternera removeTernera(Ternera ternera) {
		getTerneras().remove(ternera);
		ternera.setGuachera(null);

		return ternera;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setGuachera(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setGuachera(null);

		return usuario;
	}

}