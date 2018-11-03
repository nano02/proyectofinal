package com.services;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;

import com.excepciones.TamboException;
import com.entities.*;

@Remote
public interface TamboBeanRemote {

	
	//Madre
	Madre buscarIdMadre(Long idMadre)throws TamboException;
			
	//Padre
	Padre buscarIdPadre(Long idPadre)throws TamboException;
	


	//Guachera
	Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException;
	

	//Consumo
	LinkedList<Consumo> consumoPorTernera(Ternera terneraPorId)throws TamboException;


	void altaPeso(int idPeso, int idTernera, long tipoRegistro, Date fecha, double peso) throws TamboException;

	//Cuadro Clinico
	//LinkedList<CuadroClinico> diaEvento() throws TamboException;

	
	
}
