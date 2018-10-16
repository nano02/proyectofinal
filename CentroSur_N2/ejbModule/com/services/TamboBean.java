package com.services;

import java.sql.Date;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;

import com.entities.Consumo;
import com.entities.Guachera;
import com.entities.Madre;
import com.entities.Padre;
import com.entities.Ternera;
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
	private LinkedList<CuadroClinico> diaEvento = new LinkedList<>();	
	
	
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
	@Override
	public void crearAlimento(Alimento alimento) throws TamboException {
		try {
			if (alimento.getNombre() == null){
				throw new TamboException("Debe ingresar un nombre de alimento");
			}
			daoAlimento.insert(alimento);
		} catch (SQLException e) {
			throw new TamboException("No se pudo crear el alimento");
		}	
	}


	/**
	 * CUADRO CLINICO
	 */
	@Override

	public LinkedList<CuadroClinico> diaEvento() throws TamboException {
		try {
			diaEvento = daoCuadroClinico.diaEvento();
			return diaEvento;

		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el listado" + e.getErrorCode());			
		}		

	}

	/**
	 * GUACHERA
	 */
	@Override
	public Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException {

		if(nombreGuachera.isEmpty()){
			throw new TamboException("El nombre de la guachera no puede ser vacío");
		}
		if(nombreGuachera.length()!=11){
			throw new TamboException("El nombre de la guachera debe tener 11 caracteres (GUACHERANNN)");
		}			
		else { 			
			try {
				guachera = daoGuachera.find(nombreGuachera);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (guachera != null){
				return guachera;
			}
			else {
				throw new TamboException("No se pudo obtener la guachera");
			}
		}

	}


	/**
	 * MADRE
	 */
	@Override
	public Madre buscarIdMadre(Long idMadre) throws TamboException {

		if(idMadre == null){
			throw new TamboException("El número de identificación de la madre solamente puede tener números");
		}
		if(idMadre.toString().length()>4){
			throw new TamboException("El número de identificación de la madre debe tener hasta 4 dígitos");
		}			
		else { 			
			try {
				madre = daoMadre.findIdMadre(idMadre);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (madre != null){
				return madre;
			}
			else {
				throw new TamboException("No se pudo obtener la madre");
			}
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
				padre = daoPadre.findIdPadre(idPadre);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (padre != null){
				return padre;
			}
			else {
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
			if(peso.getTipo() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			if(peso.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			if(peso.getPeso() == null){
				throw new TamboException("Debe ingresar un peso");
			}
			daoPeso.insert(peso);
		}catch (SQLException e){
			throw new TamboException("No se pudo ingresar el peso");
		}
	}

	public void bajaPeso(Peso peso) throws TamboException{
		try{
			if(peso.getIdPeso() == null){
				throw new TamboException("Debe seleccionar un peso");
			}
			daoPeso.delete(peso);
		}catch (SQLException e){
			throw new TamboException("No se pudo eliminar el peso");
		}
	}

	public void editarPeso(Peso peso) throws TamboException{
		try{
			if(peso.getTernera() == null){
				throw new TamboException("Debe seleccionar una ternera");
			}
			if(peso.getTipo() == null){
				throw new TamboException("Debe seleccionar un tipo de peso");
			}
			if(peso.getFecha() == null){
				throw new TamboException("Debe seleccionar una fecha");
			}
			if(peso.getPeso() == null){
				throw new TamboException("Debe ingresar un peso");
			}
			daoPeso.edit(peso);
		}catch (SQLException e){
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

			if(ternera.getGuachera().getIdGuachera()== null){
				throw new TamboException("Debe ingresar el nombre de una guachera válida");
			}
			if(ternera.getFechaNacimiento()== null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}

			if(ternera.getPesoNacimiento()== null){
				throw new TamboException("Debe ingresar un peso de nacimiento válido");
			}

			if(ternera.getPesoNacimiento()<=0){
				throw new TamboException("El peso no puede ser menor o igual a 0");
			}

			if(ternera.getPesoNacimiento()>=10000){
				throw new TamboException("El peso no puede tener más de 4 enteros y 2 decimales");
			}

			if(ternera.getParto() == null){
				throw new TamboException("Debe seleccionar un tipo de parto");
			}
			if(ternera.getRaza()== null){
				throw new TamboException("Debe seleccionar una raza");
			}
			else {
				daoTernera.insert(ternera);
			}

		} catch (SQLException e) {
			throw new TamboException("No se pudo crear la ternera");
		}
	}

	@Override
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

			if(ternera.getFechaNacimiento()== null){
				throw new TamboException("Debe seleccionar una fecha de nacimiento válida (DD/MM/AA)");
			}

			if(ternera.getPesoNacimiento()== null){
				throw new TamboException("Debe ingresar un peso de nacimiento válido");
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
				daoTernera.edit(ternera);
			}
		}
		 catch (SQLException e) {
			throw new TamboException("No se pudo editar la ternera");
		}
	}
	

	/**
	 * BAJA 
	 */
	@Override
	public void bajaTernera(BajaTernera baja) throws TamboException {
		try {
			if(baja.getFechaBaja()!= null && baja.getMotivoBaja().isEmpty()){
				throw new TamboException("Debe ingresar un motivo de baja");
			}
			else{
				daoBaja.insertBaja(baja);
			}
		} catch (SQLException e) {
			throw new TamboException("No se pudo realizar la baja de la ternera");
		}
	}

	@Override
	public void muerteTernera(BajaTernera baja) throws TamboException {
		try {
			if(baja.getFechaMuerte()!= null) {	
				Date fechaBaja = new java.sql.Date(baja.getFechaMuerte().getTime());
				baja.setFechaBaja(fechaBaja);
				baja.setMotivoBaja("Muerte");

				if(baja.getCausaMuerte().isEmpty()){
					throw new TamboException("Debe ingresar una causa de muerte");
				}
				else {
					daoBaja.insertMuerte(baja);
				}
			}		

		} catch (SQLException e) {
			throw new TamboException("No se pudo registrar la baja por muerte");
		}
	}

	@Override
	public void eliminarTernera(Ternera ternera) throws TamboException {
		try{
			daoTernera.delete(ternera);
		} catch (SQLException e){
			throw new TamboException("No se pudo eliminar la ternera");
		}
	}

	

	@Override
	public Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		try{			
			ternera = daoTernera.find(idTernera);
		} catch (SQLException e){
			throw new TamboException("No se pudo buscar la ternera por ID");
		}
		return ternera;
	}

	@Override
	public void buscarTerneraPorIdTodas(Long idTernera) throws TamboException {
		try {			
				if(ternera.getIdTernera()== null){
					throw new TamboException("El número de identificación únicamente puede contener números");
				}

				else if(ternera.getIdTernera().toString().length()>4){
					throw new TamboException("El número de identificación debe tener un máximo de 4 dígitos");
				}

				else if(ternera.getIdTernera() <= 0){
					throw new TamboException("El número de identificación debe ser mayor a 0");
				}
				else {
					daoTernera.findTernera(idTernera);
			}
		} catch (SQLException e) {

			throw new TamboException("No se pudo buscar la ternera por ID");
		}

	}

	@Override
	public Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		try {					
			ternera = daoTernera.findCaravana(crvnTernera);
		} catch (SQLException e) {

			throw new TamboException("No se pudo buscar la ternera por caravana");
		}
		return ternera;
	}

	@Override
	public List<Ternera> buscarTodasTernera() throws TamboException {
		try {
			try {
				terneras = daoTernera.findAll();
			} catch (SQLException e) {
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
		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el Máximo Número de Identificador");
		}
	}

	//CONSUMO
	@Override
	public LinkedList<Consumo> consumoPorTernera(Ternera terneraPorId) throws TamboException {

		try {

			if(ternera.getIdTernera()== null){
				throw new TamboException("El número de identificación únicamente puede contener números");
			}

			else if(ternera.getIdTernera().toString().length()>4){
				throw new TamboException("El número de identificación debe tener un máximo de 4 dígitos");
			}

			else if(ternera.getIdTernera() <= 0){
				throw new TamboException("El número de identificación debe ser mayor a 0");
			}

			else{
				consumos = daoConsumo.consumoPorTernera(terneraPorId.getIdTernera());
				return consumos;
			}
		} catch (SQLException e) {
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
		boolean validContrasenia = isTextoNumeros(usuario.getContraseña());
		
		try{

			if(usuario.getNombre().isEmpty()){
				throw new TamboException("El nombre del usuario no puede estar vacío");
			}
			else if(usuario.getNombre().length()> 50){
				throw new TamboException("El nombre del usuario no puede tener más de 50 letras");
			}
			else if(!validNombre){
				throw new TamboException("El nombre del usuario debe contener solamente letras");
			}
			if(usuario.getApellido().isEmpty()){
				throw new TamboException("El apellido del usuario no puede estar vacío");
			}
			else if(usuario.getApellido().length()> 50){
				throw new TamboException("El apellido del usuario no puede tener más de 50 letras");
			}
			else if(!validApellido){
				throw new TamboException("El apellido del usuario debe contener solamente letras");
			}
			if(usuario.getContraseña().isEmpty()){
				throw new TamboException("La contraseña del usuario no puede estar vacía");
			}
			else if(usuario.getContraseña().length()<8 || usuario.getContraseña().length()>16 ){
				throw new TamboException("La contraseña debe tener por lo menos 8 dígitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La contraseña del usuario debe tener números y letras");
			}			
			else {
				daoUsuario.insert(usuario);
			}

		} catch (SQLException e) {
			throw new TamboException("No se pudo crear el usuario");
		}
		
	}

	@Override
	public void editarUsuario(Usuario usuario) throws TamboException {
		boolean validContrasenia = isTextoNumeros(usuario.getContraseña());

		try{ 
			if(usuario.getContraseña().isEmpty()){
				throw new TamboException("La contraseña del usuario no puede estar vacía");
			}
			else if(usuario.getContraseña().length()<8 || usuario.getContraseña().length()>16 ){
				throw new TamboException("La contraseña debe tener por lo menos 8 dígitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La contraseña del usuario debe tener números y letras");
			}			
			else {
				daoUsuario.edit(usuario);;
			}

		} catch (SQLException e) {
			throw new TamboException("No se pudo crear el usuario");
		}		

	}

	@Override
	public void eliminarUsuario(Usuario usuario) throws TamboException {
		try {
			daoUsuario.delete(usuario);
		} catch (SQLException e) {
			throw new TamboException("No se pudo eliminar el usuario");
		}		
	}
	
	@Override
	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave) throws TamboException {
		try {
			return this.daoUsuario.findLogin(nombreUsuario, clave);
		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}


	@Override
	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		try {
			return this.daoUsuario.find(nombreUsuario);
		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}


	@Override
	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		try {
			return this.daoUsuario.findApellido(apellidoUsuario);
		} catch (SQLException e) {
			throw new TamboException("No se pudo obtener el usuario");
		}
	}



	@Override
	public void altaTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void editarTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void eliminarTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public com.services.Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Madre buscarIdMadre(Long idMadre) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Padre buscarIdPadre(Long idPadre) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public LinkedList<com.services.Consumo> consumoPorTernera(com.services.Ternera terneraPorId) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void altaTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void editarTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void eliminarTernera(com.services.Ternera ternera) throws TamboException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public com.services.Ternera buscarTerneraPorIdViva(Long idTernera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Ternera buscarTerneraPorCaravana(String crvnTernera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Madre buscarIdMadre(Long idMadre) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Padre buscarIdPadre(Long idPadre) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public com.services.Guachera buscarNombreGuachera(String nombreGuachera) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public LinkedList<com.services.Consumo> consumoPorTernera(com.services.Ternera terneraPorId) throws TamboException {
		// TODO Auto-generated method stub
		return null;
	}

}


