package com.enums;

public enum PerfilUsuario {
PERSONAL("Personal",0), ENCARGADO("Encargado",1);
	
	private String nombre;
	private int numero;
	private PerfilUsuario(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
}
