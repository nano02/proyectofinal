package com.enums;

public enum RazaTernera {
HOLANDO("Holando",1), JERSEY("Jersey",2), CRUZA("Cruza",3);
	
	private String nombre;
	private int numero;
	
	private RazaTernera(String nombre, int numero) {
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
