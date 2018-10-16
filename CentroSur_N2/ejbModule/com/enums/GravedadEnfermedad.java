package com.enums;

public enum GravedadEnfermedad {
	UNO("1",1), DOS("2",2), TRES("3",3); 
	
	private String nombre;
	private int numero;
	
	private GravedadEnfermedad (String nombre, int numero){
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
