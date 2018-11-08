package com.services;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Ternera;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;

@Remote
public interface TerneraBeanRemote {

	void altaTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, Date fechaNac, Long baja, RazaTernera raza, TipoParto parto, Double pesoNac) throws TamboException;
	void bajaTernera(Long idTernera, Date fechaBaja, String motivo) throws TamboException;
	void muerteTernera(Long idTernera, Date fechaMuerte, String causaMuerte, Date fechaBaja, String motivoBaja) throws TamboException;
	void eliminarTernera(Long  idTernera) throws TamboException;
	Ternera buscarTerneraPorIdTodas(Long idTernera) throws TamboException;
	Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException;
	LinkedList<Ternera> buscarTodasTernera() throws TamboException;
	
}
