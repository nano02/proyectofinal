package com.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Baja;
import com.entities.Ternera;
import com.excepciones.TamboException;

/**
 * Session Bean implementation class TerneraEJBBean
 */
@Stateless
@LocalBean
public class TerneraEJBBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor. 
	 */
	public TerneraEJBBean() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Funciones auxiliares. 
	 */

	boolean terneraExiste (String nroCrvnTernera) throws TamboException{
		try {
			Ternera ternera = em.find(Ternera.class, nroCrvnTernera);
			em.flush();

		} catch (PersistenceException e) {
			throw new TamboException("La ternera no existe");
		}
		return true;

	};
	boolean tryParseLong (String value) {  
		try {  
			Long.parseLong(value);  
			return true;  
		} catch (NumberFormatException e) {  
			return false;  
		}  
	}
	boolean crvnIsValid (Long crvn){
		String crvnAux = crvn.toString();
		if (crvnAux.length()!=7){
			return false;
		}
		else {
			return true;
		}

	}	



	public void altaTernera(Ternera ternera) throws TamboException {
		try{

			if(ternera.getNroCaravana().isEmpty()){
				throw new TamboException("El número de caravana no puede estar vacío");
			}
			else if(ternera.getNroCaravana().length()!=10){
				throw new TamboException("El número de caravana debe tener 10 dígitos");
			}
			else if(!tryParseLong(ternera.getNroCaravana())){
				throw new TamboException("El número de caravana debe contener únicamente números");
			}
			else if(Long.parseLong(ternera.getNroCaravana())<= 0){
				throw new TamboException("El número de caravana debe ser mayor a 0");
			}
			else if(!crvnIsValid(Long.parseLong(ternera.getNroCaravana()))){
				throw new TamboException("El número de caravana debe seguir el formato (000 seguido de 7 dígitos)");
			}
			else if(terneraExiste(ternera.getNroCaravana())){
				throw new TamboException("El número de caravana ya existe en el sistema");
			}

			if(ternera.getGuachera().getIdGuachera() == 0){
				throw new TamboException("Debe ingresar el nombre de una guachera válida");
			}

			if(ternera.getFechaNac().equals(0)) {
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}


			if(ternera.getFechaNac() == null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}

			if(ternera.getPesos()== null){
				throw new TamboException("Debe ingresar un peso de nacimiento válido");
			}

			/*if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener más de 4 enteros y 2 decimales");
			}*/

			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}
			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}
			else {
				Ternera ternera2 = new Ternera();
				em.persist(ternera2);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear la ternera");
		}
	}


	public void editarTernera(Ternera ternera) throws TamboException {
		try{
			if(ternera.getNroCaravana().isEmpty()){
				throw new TamboException("El número de caravana no puede estar vacío");
			}
			else if(ternera.getNroCaravana().length()!=10){
				throw new TamboException("El número de caravana debe tener 10 dígitos");
			}
			else if(!tryParseLong(ternera.getNroCaravana())){
				throw new TamboException("El número de caravana debe contener únicamente números");
			}
			else if(Long.parseLong(ternera.getNroCaravana())<= 0){
				throw new TamboException("El número de caravana debe ser mayor a 0");
			}
			else if(!crvnIsValid(Long.parseLong(ternera.getNroCaravana()))){
				throw new TamboException("El número de caravana debe seguir el formato (000 seguido de 7 dígitos)");
			}

			if(ternera.getFechaNac() == null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}

			/*
			if(ternera.getPesos() == null){
				throw new TamboException("Debe ingresar un peso de nacimiento válido");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener más de 4 enteros");
			}
			 */
			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}

			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}

			else {
				Ternera ternera2 = em.find(Ternera.class, ternera);
				em.persist(ternera2);
				em.merge(ternera2);
				em.flush();
			}

		}
		catch (PersistenceException e) {
			throw new TamboException("No se pudo editar la ternera");
		}
	}


	/**
	 * BAJA 
	 */

	public void bajaTernera(Baja baja) throws TamboException {
		try {
			if(baja.getFechaBaja()!= null && baja.getMotivoBaja().isEmpty()){
				throw new TamboException("Debe ingresar un motivo de baja");
			}
			else{
				em.persist(baja);
				em.flush();
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo realizar la baja de la ternera");
		}
	}


	public void muerteTernera(Baja baja) throws TamboException {
		try {
			if(baja.getFechaMuerte()!= null) {	
				Date fechaBaja = new java.sql.Date(baja.getFechaMuerte().getTime());
				baja.setFechaBaja(fechaBaja);
				baja.setMotivoBaja("Muerte");

				if(baja.getCausaMuerte().isEmpty()){
					throw new TamboException("Debe ingresar una causa de muerte");
				}
				else {
					em.persist(baja);
					em.flush();
				}
			}		

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo registrar la baja por muerte");
		}
	}


	public void eliminarTernera(Ternera ternera) throws TamboException {
		try{
			em.remove(ternera);
			em.flush();
		} catch (PersistenceException e){
			throw new TamboException("No se pudo eliminar la ternera");
		}
	}


	public Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		try{			
			Ternera ternera = em.find(Ternera.class, idTernera);
			em.flush();
			return ternera;	
		} catch (PersistenceException e){
			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}


	public void buscarTerneraPorIdTodas(Long idTernera) throws TamboException {
		//Casteamos a String el idTernera para calcular cantidad de dígitos
		Ternera ternera = new Ternera();
		String idTerneraS = Long.toString(ternera.getIdTernera());

		try {			
			if(ternera.getIdTernera()== 0){
				throw new TamboException("El número de identificación únicamente puede contener números");
			}

			else if(idTerneraS.length()>4){
				throw new TamboException("El número de identificación debe tener un máximo de 4 dígitos");
			}

			else if(ternera.getIdTernera() <= 0){
				throw new TamboException("El número de identificación debe ser mayor a 0");
			}
			else {
				ternera = em.find(Ternera.class, idTernera);
			}
		} catch (PersistenceException e) {

			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}


	public Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		try {					
			Ternera ternera = em.find(Ternera.class, crvnTernera);
			return ternera;
		} catch (PersistenceException e) {

			throw new TamboException("No se pudo buscar la ternera por caravana");
		}

	}


	public List<Ternera> buscarTodasTernera() throws TamboException {
		try {
			TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Terneras t", Ternera.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el listado de la totalidad de las terneras");
		}

	}


}
