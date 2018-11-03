package com.bean;

import java.util.LinkedList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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




	public String crearUsuario() {
		try {
			usuariosEJBBean.altaUsuario(nombre, apellido, clave, perfil);
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
	
	public String login() throws TamboException{
       
       
		Usuario loginUsuario = usuariosEJBBean.buscarUsuarioLogin(nombreUsuario, clave);
        
        if(usuario!=null && loginUser!=null && password !=null && checkPwd(usuario, DigestUtils.md5Hex(password))) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", usuario);
            FacesContext.getCurrentInstance().addMessage(null, message);
            perfilLogeado = loginUser.getPerfil();
            return "menuInicio";
            
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error en Inicio de SesiÛn", 
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



}
