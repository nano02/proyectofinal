package com.bean;

import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;
import com.services.UsuarioBeanRemote;



@ManagedBean(name="usuario")
@SessionScoped
public class UsuarioBean {


	@EJB
	private UsuarioBeanRemote usuariosEJBBean;

	private Long id;
	private String nombre;
	private String apellido;
	private String clave;
	private PerfilUsuario perfil;
	private String nombreUsuario;




	public String crearUsuario() throws TamboException {
			usuariosEJBBean.altaUsuario(nombre, apellido, clave, perfil, nombreUsuario);
			return "mostrarUsuario";	
	}


	public String eliminarUsuario(Long idUsuario) throws TamboException {
		usuariosEJBBean.eliminarUsuario(idUsuario);
		return "eliminarUsuario";
	}

	public String buscarUsuario(String nombreUsuario) throws TamboException {
		usuariosEJBBean.buscarUsuario(nombreUsuario);
		return "buscarUsuario";
	}
	
	public void modificarUsuario(String nombreUsuario, String clave) throws TamboException{
		usuariosEJBBean.modificarUsuario(nombreUsuario, DigestUtils.md5Hex(clave));
	}
	


	public LinkedList<Usuario> obtenerUsuarios() throws TamboException{
		return usuariosEJBBean.obtenerUsuarios();
	}
	
	public String login() throws TamboException {
        FacesMessage message = null;
        
        if(nombreUsuario!=null && clave!=null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombreUsuario);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "menuInicio";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al iniciar sesion", " ");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index";
        }
 
    } 

	
	
	
	


/*
 * Getters and Setters
 */




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
