package com.enums;

public enum NombreEnfermedad {
	NEUMONIA("Neumon�a",1), DIARREA("Diarrea",2); 

	private String nombre;
	private int numero;

	private NombreEnfermedad(String nombre, int numero) {
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
