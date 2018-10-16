package com.enums;

public enum TipoPeso {
	NACIMIENTO("Nacimiento",1), SEMANAL("Semanal",2), QUINCENAL("Quincenal",3); 

	private String nombre;
	private int numero;

	private TipoPeso (String nombre, int numero){
		this.numero = numero;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumero() {
		return numero;
	}
}
