package com.services;

import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Remote;

import com.excepciones.TamboException;
import com.entities.*;

@Remote
public interface TamboBeanRemote {

	
	//Madre
	Madre buscarIdMadre(Long idMadre)throws TamboException;
			
	//Padreaa
	Padre buscarIdPadre(Long idPadre) throws TamboException;


	//Guachera
	Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException;
	

	//Consumo
	LinkedList<Consumo> consumoPorTernera(Long terneraPorId) throws TamboException;
	
	
	void altaPeso(Long idTernera, Long tipoRegistro, Date fecha, double peso) throws TamboException;



	//Cuadro Clinico
	//LinkedList<CuadroClinico> diaEvento() throws TamboException;

	
	
}
