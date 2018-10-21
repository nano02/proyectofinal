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
import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

/**
 * Session Bean implementation class TamboBean
 */
@Stateless
public class TamboBean implements TamboBeanRemote {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private List<Ternera> terneras = new ArrayList<>();
	@EJB
	private LinkedList<Consumo> consumos = new LinkedList<>();
	@EJB
	private Ternera ternera;
	@EJB
	private Padre padre;
	@EJB
	private Madre madre;
	@EJB
	private Guachera guachera;
	@EJB
	private Alimento alimento;

	//private LinkedList<CuadroClinico> diaEvento = new LinkedList<>();	


	/**
	 * Funciones auxiliares. 
	 */

	boolean terneraExiste (String nroCrvnTernera){
		try {
			ternera = daoTernera.findCaravanaTodas(nroCrvnTernera);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (ternera == null){
			return false;
		}
		else {
			return true;
		}
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


	boolean isSoloTexto (String texto){
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
				throw new TamboException("El n�mero de identificaci�n de la madre solamente puede tener n�meros");
			}
			if(idMadre.toString().length()>4){
				throw new TamboException("El n�mero de identificaci�n de la madre debe tener hasta 4 d�gitos");
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
			throw new TamboException("El n�mero de identificaci�n del padre solamente puede tener n�meros");
		}
		if(idPadre.toString().length()>4){
			throw new TamboException("El n�mero de identificaci�n del padre debe tener hasta 4 d�gitos");
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
			/*if(peso.getPeso() == null){
				throw new TamboException("Debe ingresar un peso");
			}*/

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
		}catch (PersistenceException e){
			throw new TamboException("No se pudo editar el peso");
		}
	}

	/**
	 * TERNERA
	 */


	@Override
	public void altaTernera(Ternera ternera) throws TamboException {
		try{

			if(ternera.getNroCaravana().isEmpty()){
				throw new TamboException("El n�mero de caravana no puede estar vac�o");
			}
			else if(ternera.getNroCaravana().length()!=10){
				throw new TamboException("El n�mero de caravana debe tener 10 d�gitos");
			}
			else if(!tryParseLong(ternera.getNroCaravana())){
				throw new TamboException("El n�mero de caravana debe contener �nicamente n�meros");
			}
			else if(Long.parseLong(ternera.getNroCaravana())<= 0){
				throw new TamboException("El n�mero de caravana debe ser mayor a 0");
			}
			else if(!crvnIsValid(Long.parseLong(ternera.getNroCaravana()))){
				throw new TamboException("El n�mero de caravana debe seguir el formato (000 seguido de 7 d�gitos)");
			}
			else if(terneraExiste(ternera.getNroCaravana())){
				throw new TamboException("El n�mero de caravana ya existe en el sistema");
			}

			if(ternera.getGuachera().getIdGuachera() == 0){
				throw new TamboException("Debe ingresar el nombre de una guachera v�lida");
			}

			/*if(ternera.getFechaNac().equals(0)) {
				throw new TamboException("Debe seleccionar una fecha de nacimiento v�lida (DD/MM/AA)");
			}*/


			/*if(ternera.getFechaNacimiento() == null{
				throw new TamboException("Debe seleccionar una fecha de nacimiento v�lida (DD/MM/AA)");
			}

			if(ternera.getPesoNacimiento()== 0){
				throw new TamboException("Debe ingresar un peso de nacimiento v�lido");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener m�s de 4 enteros y 2 decimales");
			}
			 */
			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}
			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}
			else {
				Ternera ternera = new Ternera();
				em.persist(ternera);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear la ternera");
		}
	}

	@Override
	public void editarTernera(Ternera ternera) throws TamboException {
		try{
			if(ternera.getNroCaravana().isEmpty()){
				throw new TamboException("El n�mero de caravana no puede estar vac�o");
			}
			else if(ternera.getNroCaravana().length()!=10){
				throw new TamboException("El n�mero de caravana debe tener 10 d�gitos");
			}
			else if(!tryParseLong(ternera.getNroCaravana())){
				throw new TamboException("El n�mero de caravana debe contener �nicamente n�meros");
			}
			else if(Long.parseLong(ternera.getNroCaravana())<= 0){
				throw new TamboException("El n�mero de caravana debe ser mayor a 0");
			}
			else if(!crvnIsValid(Long.parseLong(ternera.getNroCaravana()))){
				throw new TamboException("El n�mero de caravana debe seguir el formato (000 seguido de 7 d�gitos)");
			}

			/*if(ternera.getFechaNacimiento()== null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento v�lida (DD/MM/AA)");
			}

			if(ternera.getPesoNacimiento()== null){
				throw new TamboException("Debe ingresar un peso de nacimiento v�lido");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener m�s de 4 enteros");
			}
			 */
			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}

			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}

			else {
				/*Ternera ternera = em.find(Ternera.class, nroCaravana);
				em.persist(ternera);
				em.merge(null)
			}*/
			}
		}
		catch (PersistenceException e) {
			throw new TamboException("No se pudo editar la ternera");
		}
	}


	/**
	 * BAJA 
	 */
	@Override
	public void bajaTernera(Baja baja) throws TamboException {
		try {
			if(baja.getFechaBaja()!= null && baja.getMotivoBaja().isEmpty()){
				throw new TamboException("Debe ingresar un motivo de baja");
			}
			else{
				//daoBaja.insertBaja(baja);
				em.persist(baja);
				em.flush();
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo realizar la baja de la ternera");
		}
	}

	@Override
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
					//daoBaja.insertMuerte(baja);
					em.persist(baja);
					em.flush();
				}
			}		

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo registrar la baja por muerte");
		}
	}

	@Override
	public void eliminarTernera(Ternera ternera) throws TamboException {
		try{
			//daoTernera.delete(ternera);
			em.remove(ternera);
			em.flush();
		} catch (PersistenceException e){
			throw new TamboException("No se pudo eliminar la ternera");
		}
	}



	@Override
	public Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		try{			
			Ternera ternera = em.find(Ternera.class, idTernera);
			em.persist(ternera);
			em.merge(ternera);
			em.flush();
			//ternera = daoTernera.find(idTernera);
		} catch (PersistenceException e){
			throw new TamboException("No se pudo buscar la ternera por ID");
		}
		return ternera;
	}

	@Override
	public void buscarTerneraPorIdTodas(Long idTernera) throws TamboException {
		//Casteamos a String en idTernera para calcular cantidad de d�gitos
		String idTerneraS = Long.toString(ternera.getIdTernera());
		
		try {			
			if(ternera.getIdTernera()== 0){
				throw new TamboException("El n�mero de identificaci�n �nicamente puede contener n�meros");
			}

			else if(idTerneraS.length()>4){
				throw new TamboException("El n�mero de identificaci�n debe tener un m�ximo de 4 d�gitos");
			}

			else if(ternera.getIdTernera() <= 0){
				throw new TamboException("El n�mero de identificaci�n debe ser mayor a 0");
			}
			else {
				//daoTernera.findTernera(idTernera);
				Ternera ternera = em.find(Ternera.class, idTernera);
			
			}
		} catch (PersistenceException e) {

			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}

	@Override
	public Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		try {					
			//ternera = daoTernera.findCaravana(crvnTernera);
			Ternera ternera = em.find(Ternera.class, crvnTernera);
			
		} catch (PersistenceException e) {

			throw new TamboException("No se pudo buscar la ternera por caravana");
		}
		return ternera;
	}

	@Override
	public List<Ternera> buscarTodasTernera() throws TamboException {
		try {
			try {
				//terneras = daoTernera.findAll();
				TypedQuery<Ternera> query = em.createQuery("SELECT t FROM Terneras t", Ternera.class);
				List resultados = query.getResultList();
				
			} catch (PersistenceException e) {
				throw new TamboException("No se pudo obtener el listado de la totalidad de las terneras");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return terneras;
	}

	@Override
	public Long buscarMaxId() throws TamboException {
		try {
			return this.daoTernera.getMaxIdTernera();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el M�ximo N�mero de Identificador");
		}
	}

	//CONSUMO
	@Override
	public LinkedList<Consumo> consumoPorTernera(Ternera terneraPorId) throws TamboException {

		//Casteamos a String en idTernera para calcular cantidad de d�gitos
		String idTerneraS = Long.toString(ternera.getIdTernera());
		try {

			if(ternera.getIdTernera()== 0){
				throw new TamboException("El n�mero de identificaci�n �nicamente puede contener n�meros");
			}

			else if(idTerneraS.length()>4){
				throw new TamboException("El n�mero de identificaci�n debe tener un m�ximo de 4 d�gitos");
			}

			else if(ternera.getIdTernera() <= 0){
				throw new TamboException("El n�mero de identificaci�n debe ser mayor a 0");
			}

			else{
				
				/*consumos = daoConsumo.consumoPorTernera(terneraPorId.getIdTernera());
				return consumos;*/
				TypedQuery<Consumo> query = em.createQuery("SELECT c FROM Consumos c", Consumo.class);
				List resultados = query.getResultList();
				
			}
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el consumo");
		}

	}


	/**
	 * USUARIO 
	 */

	@Override
	public void altaUsuario(Usuario usuario) throws TamboException {
		boolean validNombre = isSoloTexto(usuario.getNombre());
		boolean validApellido = isSoloTexto(usuario.getApellido());
		boolean validContrasenia = isTextoNumeros(usuario.getContrase�a());

		try{

			if(usuario.getNombre().isEmpty()){
				throw new TamboException("El nombre del usuario no puede estar vac�o");
			}
			else if(usuario.getNombre().length()> 50){
				throw new TamboException("El nombre del usuario no puede tener m�s de 50 letras");
			}
			else if(!validNombre){
				throw new TamboException("El nombre del usuario debe contener solamente letras");
			}
			if(usuario.getApellido().isEmpty()){
				throw new TamboException("El apellido del usuario no puede estar vac�o");
			}
			else if(usuario.getApellido().length()> 50){
				throw new TamboException("El apellido del usuario no puede tener m�s de 50 letras");
			}
			else if(!validApellido){
				throw new TamboException("El apellido del usuario debe contener solamente letras");
			}
			if(usuario.getContrase�a().isEmpty()){
				throw new TamboException("La contrase�a del usuario no puede estar vac�a");
			}
			else if(usuario.getContrase�a().length()<8 || usuario.getContrase�a().length()>16 ){
				throw new TamboException("La contrase�a debe tener por lo menos 8 d�gitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La contrase�a del usuario debe tener n�meros y letras");
			}			
			else {
				//daoUsuario.insert(usuario);
				Usuario usu = new Usuario();
				em.persist(usu);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el usuario");
		}

	}

	@Override
	public void editarUsuario(Usuario usuario) throws TamboException {
		boolean validContrasenia = isTextoNumeros(usuario.getContrase�a());

		try{ 
			if(usuario.getContrase�a().isEmpty()){
				throw new TamboException("La contrase�a del usuario no puede estar vac�a");
			}
			else if(usuario.getContrase�a().length()<8 || usuario.getContrase�a().length()>16 ){
				throw new TamboException("La contrase�a debe tener por lo menos 8 d�gitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La contrase�a del usuario debe tener n�meros y letras");
			}			
			else {
				//daoUsuario.edit(usuario);;
				
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el usuario");
		}		

	}

	@Override
	public void eliminarUsuario(Usuario usuario) throws TamboException {
		try {
			//daoUsuario.delete(usuario);
			em.remove(usuario);
			em.flush();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo eliminar el usuario");
		}		
	}

	@Override
	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave) throws TamboException {
		try {
			return this.daoUsuario.findLogin(nombreUsuario, clave);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}


	@Override
	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		try {
			return this.daoUsuario.find(nombreUsuario);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}


	@Override
	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		try {
			return this.daoUsuario.findApellido(apellidoUsuario);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}



}


