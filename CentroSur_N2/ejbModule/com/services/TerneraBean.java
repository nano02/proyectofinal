package com.services;

import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Baja;
import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Ternera;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;

/**
 * Session Bean implementation class TerneraBean2
 */
@Stateless
public class TerneraBean implements TerneraBeanRemote {

    /**
     * Default constructor. 
     */
    public TerneraBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext
	private EntityManager em;



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
	public boolean isSoloTexto (String texto){
		char[] chars = texto.toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c)) {
				return false;
			}
		}

		return true;
	}
	//isLetterOrDigit

	boolean isTextoNumeros (String texto){
		char[] chars = texto.toCharArray();	
		int n = 0;
		int l = 0;

		for (char c : chars) {
			if(Character.isLetterOrDigit(c)) {
				if (Character.isLetter(c)){
					l++;
				}
				if (Character.isDigit(c)){
					n++;
				}
				if (l>0 && n>0){
					return true;}
			}
		}

		return false;
	}


	public boolean terneraExiste (String nroCaravanaTernera) throws TamboException{
		try{
			TypedQuery<Ternera> query =  em.createQuery("SELECT t FROM Terneras WHERE nro_caravana LIKE :nroCaravana", Ternera.class)
					.setParameter("nroCaravana", nroCaravanaTernera);
			return query.getResultList().get(0) != null;

		} catch (PersistenceException e) {
			throw new TamboException("La ternera no existe");
		}

	};


	public void altaTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, Date fechaNac, Long baja, RazaTernera raza, TipoParto parto, Double pesoNac) throws TamboException {
		try{

			Guachera guachera = new Guachera();
			Madre madre = new Madre();
			Padre padre = new Padre();
			Ternera ternera = new Ternera();
			Baja bajaTernera = new Baja();
			madre.setIdMadre(idMadre);
			padre.setIdPadre(idPadre);

			//Validación de número de caravana 
			if(ternera.getNroCaravana().isEmpty()){
				throw new TamboException("El número de caravana no puede estar vacío");
			}
			else if(ternera.getNroCaravana().length()!=10){
				throw new TamboException("El número de caravana debe tener 10 dígitos");
			}
			else if(!tryParseLong(ternera.getNroCaravana())){
				throw new TamboException("El número de caravana debe contener �nicamente n�meros");
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
			else {
				ternera.setNroCaravana(nroCaravana);
			}

			//Validación de número de guachera
			if(ternera.getGuachera().getIdGuachera() == 0){
				throw new TamboException("Debe ingresar el nombre de una guachera válida");
			}
			else {
				ternera.setGuachera(guachera);
			}

			//Validación de fecha de nacimiento
			if(ternera.getFechaNac().getTime() ==0) {
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}

			else if(ternera.getFechaNac() == null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}
			else {
				ternera.setFechaNac(fechaNac);
			}

			if(ternera.getBaja().equals(1)){
				bajaTernera.setIdTernera(idTernera);
			}

			//Validación de peso
			if(ternera.getPesoNacimiento().equals(0)){
				throw new TamboException("Debe ingresar un peso de nacimiento válido");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener más de 4 enteros y 2 decimales");
			}
			else {
				ternera.setPesoNacimiento(pesoNac);
			}
			//Validación de Parto
			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}
			//Validación de Raza
			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}
			else {
				em.persist(ternera);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear la ternera");
		}
	}


	public void editarTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, Date fechaNac, Long baja, RazaTernera raza, TipoParto parto, Double pesoNac) throws TamboException {
		try{

			Guachera guachera = new Guachera();
			Madre madre = new Madre();
			Padre padre = new Padre();
			Baja bajaTernera = new Baja();
			madre.setIdMadre(idMadre);
			padre.setIdPadre(idPadre);
			guachera.setIdGuachera(idGuachera);
			bajaTernera.setIdTernera(idTernera);

			Ternera ternera = em.find(Ternera.class, idTernera);

			
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

			if(ternera.getPesoNacimiento() == null){
				throw new TamboException("Debe ingresar un peso de nacimiento válida");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener más de 4 enteros");
			}

			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}

			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}

			else {
				em.persist(ternera);
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

	public void bajaTernera(Long idTernera, Date fechaBaja, String motivo) throws TamboException {
		try {

			Baja baja = new Baja();

			if(baja.getFechaBaja()!= null && baja.getMotivoBaja().isEmpty()){
				throw new TamboException("Debe ingresar un motivo de baja");
			}
			else{
				baja.setIdTernera(idTernera);
				baja.setFechaBaja(fechaBaja);
				baja.setMotivoBaja(motivo);
				em.persist(baja);
				em.flush();
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo realizar la baja de la ternera");
		}
	}


	public void muerteTernera(Long idTernera, Date fechaMuerte, String causaMuerte, Date fechaBaja, String motivoBaja) throws TamboException {
		try {

			Baja baja = new Baja();
			if(baja.getFechaMuerte()!= null) {	
				baja.setFechaMuerte(fechaMuerte);
				Date fecha = new java.sql.Date(baja.getFechaMuerte().getTime());
				baja.setFechaBaja(fecha);
				baja.setMotivoBaja("Muerte");
			}
			else if(baja.getCausaMuerte().isEmpty()){
				throw new TamboException("Debe ingresar una causa de muerte");
			}
			else {

				baja.setCausaMuerte(causaMuerte);
				em.persist(baja);
				em.flush();
			}	

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo registrar la baja por muerte");
		}
	}


	public void eliminarTernera(Long  idTernera) throws TamboException {
		try{
			em.remove(em.find(Ternera.class, idTernera));
			em.flush();
		} catch (PersistenceException e){
			throw new TamboException("No se pudo eliminar la ternera");
		}
	}


	public Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		try{			
			TypedQuery<Ternera> query =  em.createQuery("SELECT t FROM Terneras t WHERE t.id_ternera LIKE :idternera AND t.baja LIKE :0", Ternera.class).setParameter("idTernera", idTernera);
			return query.getResultList().get(0);
		} catch (PersistenceException e){
			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}


	public Ternera buscarTerneraPorIdTodas(Long idTernera) throws TamboException {
		//Casteamos a String el idTernera para calcular cantidad de dígitos
		Ternera ternera = new Ternera();
		String idTerneraS = Long.toString(ternera.getIdTernera());

		try {			
			if(ternera.getIdTernera()== 0){
				throw new TamboException("El número de identificación únicamente puede contener números");
			}

			else if(idTerneraS.length()>4){
				throw new TamboException("El número de identificación debe tener un mximo de 4 dígitos");
			}

			else if(ternera.getIdTernera() <= 0){
				throw new TamboException("El número de identificación debe ser mayor a 0");
			}
			else {
				TypedQuery<Ternera> query =  em.createQuery("SELECT t FROM Terneras t WHERE t.id_ternera LIKE :idternera ", Ternera.class).setParameter("idTernera", idTernera);
				return query.getResultList().get(0);
			}
		} catch (PersistenceException e) {

			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}


	public Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		try {					
			TypedQuery<Ternera> query =  em.createQuery("SELECT t FROM Terneras t WHERE t.nro_caravana LIKE :crvnTernera",Ternera.class)
					.setParameter("crvnTernera", crvnTernera);
			return query.getResultList().get(0);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener la ternera");
		}

	}


	public LinkedList<Ternera> buscarTodasTernera() throws TamboException {
		try {
			TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Terneras t", Ternera.class);
			LinkedList<Ternera> lista = new LinkedList<>();
			lista.addAll(query.getResultList());
			return lista;
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el listado de la totalidad de las terneras");
		}

	}

}

