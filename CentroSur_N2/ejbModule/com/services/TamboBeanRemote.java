package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;

import com.excepciones.TamboException;
import com.entities.*;

@Remote
public interface TamboBeanRemote {

	// Alimento
	void crearAlimento(Alimento alimento) throws TamboException;
	
	// Ternera
	void altaTernera(Ternera ternera) throws TamboException;
	Long buscarMaxId()throws TamboException;
	void editarTernera(Ternera ternera) throws TamboException;
	void eliminarTernera(Ternera ternera) throws TamboException;
	List<Ternera> buscarTodasTernera() throws TamboException;
	Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException;
	void buscarTerneraPorIdTodas(Long idTernera) throws TamboException;
	Ternera buscarTerneraPorCaravana (String crvnTernera)throws TamboException;
		
	//Baja
	void bajaTernera(Baja baja)throws TamboException;
	void muerteTernera(Baja baja)throws TamboException;
	
	//Madre
	Madre buscarIdMadre(Long idMadre)throws TamboException;
			
	//Padre
	Padre buscarIdPadre(Long idPadre)throws TamboException;
	
	//Peso
	void altaPeso(Peso peso) throws TamboException;
	void bajaPeso(Peso peso) throws TamboException;

	//Guachera
	Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException;
	
	//Usuario
	void altaUsuario(Usuario usuario) throws TamboException;
	void editarUsuario(Usuario usuario) throws TamboException;
	void eliminarUsuario(Usuario usuario) throws TamboException;	
	Usuario buscarUsuarioLogin(String nombreUsuario, String clave)throws TamboException;
	Usuario buscarUsuario(String nombreUsuario) throws TamboException;
	Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException;

	//Consumo
	LinkedList<Consumo> consumoPorTernera(Ternera terneraPorId)throws TamboException;

	//Cuadro Clinico
	LinkedList<CuadroClinico> diaEvento() throws TamboException;

	
	
}
