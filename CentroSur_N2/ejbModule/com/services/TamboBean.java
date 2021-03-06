package com.services;


import java.util.Date;
import java.util.LinkedList;

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
import com.utilitary.metodosAuxiliares;

/**
 * Session Bean implementation class TamboBean
 */
@Stateless
public class TamboBean implements TamboBeanRemote {

	@PersistenceContext
	private EntityManager em;



	//private LinkedList<CuadroClinico> diaEvento = new LinkedList<>();	


	
	/**
	 * Default constructor. 
	 */
	public TamboBean() {

	}


	/**
	 * ALIMENTO
	 */
	public void crearAlimento(String nombre, Long cantidad, Long costoUnitario,  int unidad) throws TamboException {
		try {
			Alimento alimento = new Alimento();
			Unidad uni = new Unidad();
			alimento.setUnidad(uni);
			alimento.setNombre(nombre);
			alimento.setCantidad(cantidad);
			alimento.setCostoUnitario(costoUnitario);
			em.persist(alimento);
			em.flush();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el alimento");
		}	
	}


	/**
	 * CUADRO CLINICO


	@Override
	public LinkedList<CuadroClinico> diaEvento() throws TamboException {
		try {
			diaEvento = daoCuadroClinico.diaEvento();
			return diaEvento;

		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el listado" + e.getErrorCode());			
		}		

	}*/

	/**
	 * GUACHERA
	 */
	@Override
	public Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException {

		try {
			Guachera g = em.find(Guachera.class, nombreGuachera);
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
			if(idMadre == 0){
				throw new TamboException("El número de identificación de la madre solamente puede tener números");
			}
			if(idMadre.toString().length()>4){
				throw new TamboException("El número de identificación de la madre debe tener hasta 4 dígitos");
			}
			else{
				Madre madre = em.find(Madre.class, idMadre);
				return madre;
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener la madre");
		}
	}

	/**
	 * PADRE
	 */
	@Override
	public Padre buscarIdPadre(Long idPadre) throws TamboException {
		try{
			if(idPadre == 0){
				throw new TamboException("El número de identificaciún del padre solamente puede tener números");
			}
			if(idPadre.toString().length()>4){
				throw new TamboException("El número de identificación del padre debe tener hasta 4 dígitos");
			}			
			else { 			
				Padre padre = em.find(Padre.class, idPadre);
				return padre;
			}	
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener al padre");
		}
	}

	/**
	 * PESO
	 */

	@Override	
	public void altaPeso(Long idTernera, Long tipoRegistro, Date fecha, double peso) throws TamboException{
		try{
			Peso p = new Peso ();
			Ternera ternera = new Ternera ();
			ternera.setIdTernera(idTernera);

			if(p.getTernera() == null){
				throw new TamboException("Debe seleccionar una ternera");
			}
			else{
				p.setTernera(ternera);	
			}
			if(p.getTipoRegistro() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			else {
				p.setTipoRegistro(tipoRegistro);
			}
			if(p.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			else {
				p.setFecha(fecha);
			}
			if(p.getPeso() == 0){
				throw new TamboException("Debe ingresar un peso");
			}

			em.persist(p);
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

	public void editarPeso(Long idPeso, Long idTernera, Long tipoRegistro, Date fecha, double peso) throws TamboException{
		try{

			Peso p = em.find(Peso.class, idPeso);

			if(p.getTernera() == null){
				throw new TamboException("Debe seleccionar una ternera");
			}
			else{
				Ternera ternera = new Ternera ();
				ternera.setIdTernera(idTernera);	
			}
			if(p.getTipoRegistro() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			else {
				p.setTipoRegistro(tipoRegistro);
			}
			if(p.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			else{
				p.setFecha(fecha);
			}
			if(p.getPeso() == 0){
				throw new TamboException("Debe ingresar un peso");
			}
			else{
				p.setPeso(peso);
			}
			em.persist(p);
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
			throw new TamboException("No se pudo obtener el M�ximo N�mero de Identificador");
		}
	}*/

	//CONSUMO
	@Override
	public LinkedList<Consumo> consumoPorTernera(Long terneraPorId) throws TamboException {

		Ternera ternera = new Ternera();
		String idTerneraS = Long.toString(ternera.getIdTernera());
		try {

			if(ternera.getIdTernera()== 0){
				throw new TamboException("El número de identificación únicamente puede contener números");
			}

			else if(idTerneraS.length()>4){
				throw new TamboException("El número de identificación debe tener un m�ximo de 4 dígitos");
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

}


