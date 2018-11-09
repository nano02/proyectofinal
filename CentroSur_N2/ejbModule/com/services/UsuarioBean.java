package com.services;


import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor. 
	 */
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
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



	public void altaUsuario(String nombre, String apellido, String clave, PerfilUsuario perfil, String nombreUsuario) throws TamboException {
		Usuario usuario  = new Usuario();
		boolean validNombre = isSoloTexto(usuario.getNombre());
		boolean validApellido = isSoloTexto(usuario.getApellido());
		boolean validContrasenia = isTextoNumeros(usuario.getClave());
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
			else{
				nombreUsuario = nombre + "." + apellido;
			}

			if(usuario.getClave().isEmpty()){
				throw new TamboException("La clave del usuario no puede estar vacía");
			}
			else if(usuario.getClave().length()<8 || usuario.getClave().length()>16 ){
				throw new TamboException("La clave debe tener por lo menos 8 dígitos y no superar los 16. Por favor revise el dato ingresado");
			}			
			else if(!validContrasenia){
				throw new TamboException("La clave del usuario debe tener números y letras");
			}
			else {
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setClave(clave);
				usuario.setPerfil(perfil);
				usuario.setUsuario(nombreUsuario);
				em.persist(usuario);
				em.flush();
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el usuario");
		}

	}
	public void eliminarUsuario(Long  idUsuario) throws TamboException{
		try {
			em.remove(em.find(Usuario.class, idUsuario));
			em.flush();
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo eliminar el usuario");
		}
	}


	public Usuario buscarUsuarioLogin(String nombreUsuario, String clave)throws TamboException {
		try {
			TypedQuery<Usuario> query =  em.createQuery("SELECT u FROM Usuario u WHERE u.usuario LIKE :nombreUsuario AND u.clave :contraseña", Usuario.class).setParameter("nombreUsuario", nombreUsuario);
			return query.getResultList().get(0);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo encontrar el usuario");
		}
		
	}

	public Usuario buscarUsuario(String nombreUsuario) throws TamboException {
		try {	
			if(nombreUsuario == null ){
				throw new TamboException("Debe ingresar un nombre de usuario válido");
			}
			else if(nombreUsuario.isEmpty()){
				throw new TamboException("El nombre de usuario no puede ser vacío");
			}
			TypedQuery<Usuario> query =  em.createQuery("SELECT u FROM Usuario u WHERE usuario LIKE: nombreUsuario", Usuario.class)
					.setParameter("nombreUsuario", nombreUsuario);
			return query.getResultList().get(0);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo buscar el usuario por nombre de usuario");
		}

	}


	public Usuario buscarApellidoUsuario(String apellidoUsuario) throws TamboException {
		try {	
			if(apellidoUsuario == null ){
				throw new TamboException("Debe ingresar un apellido válido");
			}
			else if(apellidoUsuario.isEmpty()){
				throw new TamboException("El apellido no puede ser vacío");
			}
			TypedQuery<Usuario> query =  em.createQuery("SELECT u FROM Usuario u WHERE apellido LIKE: apellido", Usuario.class)
					.setParameter("apellido", apellidoUsuario);
			return query.getResultList().get(0);
		} catch (PersistenceException e) {
			throw new TamboException("No se pudo buscar el usuario por apellido");
		}

	}
	
	public void modificarUsuario(String nombreUsuario, String clave) throws TamboException{
		Usuario user = buscarUsuario(nombreUsuario);
		user.setClave(clave);
		em.merge(user);
		em.flush();
	}
	
	

	public LinkedList<Usuario> obtenerUsuarios()throws TamboException {
		LinkedList<Usuario> listaUsuarios = new LinkedList<>();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		listaUsuarios.addAll(query.getResultList());
		return listaUsuarios;
	}




}
