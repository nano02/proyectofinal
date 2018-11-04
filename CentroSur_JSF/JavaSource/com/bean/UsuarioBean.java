package com.bean;

import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;
import com.services.UsuarioEJBBean;


@ManagedBean(name="usuario")
@SessionScoped
public class UsuarioBean {


	@EJB
	private UsuarioEJBBean usuariosEJBBean;

	private Long id;
	private String nombre;
	private String apellido;
	private String clave;
	private PerfilUsuario perfil;
	private String nombreUsuario;




	public String crearUsuario() {
		try {
			usuariosEJBBean.altaUsuario(nombre, apellido, clave, perfil, nombreUsuario);
			return "mostrarUsuario";
		} catch (TamboException e) {
			return null;
		}

	}


	public String eliminarUsuruario() {
		try {
			usuariosEJBBean.eliminarUsuario(id);
			return "eliminarUsuario";
		} catch (TamboException e) {
			return null;	
		}

	}

	public String buscarUsuario() {
		try {
			usuariosEJBBean.buscarUsuario(nombre);
			return "buscarUsuario";
		} catch (TamboException e) {
			return null;
		}
	}


	public LinkedList<Usuario> obtenerUsuarios() throws TamboException{
		return usuariosEJBBean.obtenerUsuarios();
	}
	
	public String login() throws TamboException {
        FacesMessage message = null;
        Usuario loginUser = usuariosEJBBean.buscarUsuario(nombreUsuario);
        
        if(nombreUsuario!=null && loginUser!=null && clave!=null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", nombreUsuario);
            FacesContext.getCurrentInstance().addMessage(null, message);
           //Validar perfil del usuario
            return "menuInicio";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al iniciar sesion", 
            		loginUser!=null ? "Credenciales Inv·lidas" : "No existe un Usuario que con coincida con los datos ingresados");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "index";
        }
 
    } 









	public UsuarioEJBBean getUsuariosEJBBean() {
		return usuariosEJBBean;
	}

	public void setUsuariosEJBBean(UsuarioEJBBean usuariosEJBBean) {
		this.usuariosEJBBean = usuariosEJBBean;
	}

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
