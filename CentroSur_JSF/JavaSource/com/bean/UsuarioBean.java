package com.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Usuario;
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
	private String perfil;
	//Ojo Perfil es un enumerado
	
	
	
	public String crearUsuario() {
		try {
			usuariosEJBBean.altaUsuario(nombre, apellido, clave, perfil);
			return "mostrar";
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public String eliminarUsuruario() {
		try {
			usuariosEJBBean.eliminarUsuario(null);
			return "eliminar";
		} catch (Exception e) {
			return null;	
		}
		
	}
	
	public String buscarUsuario() {
		try {
			usuariosEJBBean.buscarUsuario(null);
			return "buscar";
		} catch (Exception e) {
			return null;
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

}
