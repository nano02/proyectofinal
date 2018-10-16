package com.enums;

public enum NombreAlimento {
	CALOSTRO_NATURAL("Calostro natural",10), CALOSTRO_FORZADO("Calostro forzado",11),
	LECHE("Leche",20), SUSTITUTO_LACTEO("Sustituto lácteo",30), INICIADOR("Iniciador",40), RACION("Ración",50); 

	private String alimento;
	private int numero;

	private NombreAlimento (String alimento, int numero){
		this.numero = numero;
		this.alimento = alimento;
	}

	
	public String getAlimento() {
		return alimento;
	}


	public int getNumero() {
		return numero;
	}
}
