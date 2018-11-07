package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
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

	
	
	
	
	
		

	public Usuario altaUsuario(String nombre, String apellido, String clave, PerfilUsuario perfil, String nombreUsuario) throws TamboException {
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
				return usuario;
			}

		} catch (PersistenceException e) {
			throw new TamboException("No se pudo crear el usuario");
		}

	}
	public void eliminarUsuario(Long  idUsuario) throws TamboException{
		em.remove(em.find(Usuario.class, idUsuario));
		em.flush();
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
	public LinkedList<Usuario> obtenerUsuarios(){
		LinkedList<Usuario> listaUsuarios = new LinkedList<>();
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
		listaUsuarios.addAll(query.getResultList());
		return listaUsuarios;
	}

	

}
