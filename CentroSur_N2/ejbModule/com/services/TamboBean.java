package com.services;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import com.entities.Alimento;
import com.entities.Baja;
import com.entities.Consumo;
import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Peso;
import com.entities.Ternera;
import com.entities.Unidad;
import com.entities.Usuario;
import com.excepciones.TamboException;

/**
 * Session Bean implementation class TamboBean
 */
@Stateless
public class TamboBean implements TamboBeanRemote {

	@PersistenceContext
	private EntityManager em;



	//private LinkedList<CuadroClinico> diaEvento = new LinkedList<>();	


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


	/**
	 * Default constructor. 
	 */
	public TamboBean() {

	}


	/**
	 * ALIMENTO
	 */
	public void crearAlimento(Long cantidad, Long costoUnitario, String nombre, Unidad unidad) throws TamboException {
		try {
			Alimento alimento = new Alimento(cantidad, costoUnitario, nombre, unidad);
			em.persist(alimento);
			em.flush();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el alimento");
		}	
	}


	/**
	 * CUADRO CLINICO
	 */
	/**@Override

	public LinkedList<CuadroClinico> diaEvento() throws TamboException {
		try {
			diaEvento = daoCuadroClinico.diaEvento();
			return diaEvento;

		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el listado" + e.getErrorCode());			
		}		

	}**/

	/**
	 * GUACHERA
	 */
	@Override
	public Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException {

		try {
			Guachera g = em.find(Guachera.class, nombreGuachera);
			em.flush();
			return g;

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener la guachera");
		}

	}


	/**
	 * MADRE
	 */
	@Override
	public Madre buscarIdMadre(Long idMadre) throws TamboException {

		try {
			if(idMadre == null){
				throw new TamboException("El número de identificación de la madre solamente puede tener números");
			}
			if(idMadre.toString().length()>4){
				throw new TamboException("El número de identificación de la madre debe tener hasta 4 dígitos");
			}

			Madre madre = em.find(Madre.class, idMadre);
			em.flush();
			return madre;

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener la madre");
		}
	}

	/**
	 * PADRE
	 */
	@Override
	public Padre buscarIdPadre(Long idPadre) throws TamboException {

		if(idPadre == null){
			throw new TamboException("El número de identificación del padre solamente puede tener números");
		}
		if(idPadre.toString().length()>4){
			throw new TamboException("El número de identificación del padre debe tener hasta 4 dígitos");
		}			
		else { 			
			try {
				Padre padre = em.find(Padre.class, idPadre);
				em.flush();
				return padre;
			} catch (PersistenceException e) {
				throw new TamboException("No se pudo obtener al padre");
			}
		}
	}

	/**
	 * PESO
	 */

	@Override	

	public void altaPeso(Peso peso) throws TamboException{
		try{
			if(peso.getTernera() == null){
				throw new TamboException("Debe seleccionar una ternera");
			}
			if(peso.getTipoRegistro() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			if(peso.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			if(peso.getPeso() == 0){
				throw new TamboException("Debe ingresar un peso");
			}
			Peso p = new Peso();
			em.persist(peso);
			em.flush();
		}catch (PersistenceException e){
			throw new TamboException("No se pudo ingresar el peso");
		}
	}

	public void bajaPeso(Long idPeso) throws TamboException{
		try{
			if(idPeso ==0){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}else {
				Peso p = em.find(Peso.class, idPeso);
				em.remove(idPeso);
				em.flush();
			}

		}catch (PersistenceException e){
			throw new TamboException("No se pudo eliminar el peso");
		}
	}

	public void editarPeso(Peso peso) throws TamboException{
		try{
			if(peso.getTernera() == null){
				throw new TamboException("Debe seleccionar una ternera");
			}
			if(peso.getTipoRegistro() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			if(peso.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			if(peso.getPeso() == 0){
				throw new TamboException("Debe ingresar un peso");
			}

			//daoPeso.edit(peso);
			em.find(Peso.class, peso);
			em.merge(peso);
			em.flush();

		}catch (PersistenceException e){
			throw new TamboException("No se pudo editar el peso");
		}
	}

	/**
	 * TERNERA
	 */


	
	/*@Override
	public Long buscarMaxId() throws TamboException {
		try {
			return this.daoTernera.getMaxIdTernera();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el Máximo Número de Identificador");
		}
	}*/

	//CONSUMO
	@Override
	public LinkedList<Consumo> consumoPorTernera(Ternera terneraPorId) throws TamboException {

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

			else{
				TypedQuery<Consumo> query = em.createQuery("SELECT c FROM Consumos c", Consumo.class);
				return (LinkedList<Consumo>) query.getResultList();
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el consumo");
		}

	}


	
	@Override
	public void crearAlimento(Alimento alimento) throws TamboException {
		// TODO Auto-generated method stub

	}



	@Override
	public void bajaPeso(Peso peso) throws TamboException {
		// TODO Auto-generated method stub

	}



}


