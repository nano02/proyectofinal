package com.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Usuario;
import com.excepciones.TamboException;

/**
 * Session Bean implementation class UsuarioEJBBean
 */
@Stateless
@LocalBean
public class UsuarioEJBBean {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor. 
	 */
	public UsuarioEJBBean() {
		// TODO Auto-generated constructor stub
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
	

	public void altaUsuario(Usuario usuario) throws TamboException {
		boolean validNombre = isSoloTexto(usuario.getNombre());
		boolean validApellido = isSoloTexto(usuario.getApellido());
		boolean validContrasenia = isTextoNumeros(usuario.getClave());
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
			if(usuario.getClave().isEmpty()){
				throw new TamboException("La contrase�a del usuario no puede estar vac�a");
			}
			else if(usuario.getClave().length()<8 || usuario.getClave().length()>16 ){
				throw new TamboException("La contrase�a debe tener por lo menos 8 d�gitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La contrase�a del usuario debe tener n�meros y letras");
			}			
			else {
				Usuario usu = new Usuario();
				em.persist(usu);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el usuario");
		}

	}

	public Usuario eliminarUsuario(Usuario usuario) throws TamboException{
		em.remove(em.find(Usuario.class, usuario));
		em.flush();
		return usuario;
	}

	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave)throws TamboException {
		Usuario user = em.find(Usuario.class, nombreUsuario); //falta el parametro clave
		return user;
	}
	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		Usuario user = em.find(Usuario.class, nombreUsuario);
		return user;
	}
	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		Usuario user = em.find(Usuario.class, apellidoUsuario);
		return user;
	}


}