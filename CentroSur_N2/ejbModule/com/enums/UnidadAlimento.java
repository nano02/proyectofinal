package com.enums;

public enum UnidadAlimento {
	KG("Kilo",1), LITRO("Litro",2); 
	
	private String nombre;
	private int numero;
	
	private UnidadAlimento (String nombre, int numero){
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
