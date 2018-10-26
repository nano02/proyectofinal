package com.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Usuario;
import com.services.UsuarioEJBBean;


@ManagedBean
@SessionScoped
public class UsuarioBean {

	
	@EJB
	private UsuarioEJBBean usuariosEJBBean;
	
	
	public String crearUsuario() {
		try {
			usuariosEJBBean.altaUsuario(null);
			return "mostrar";
		} catch (Exception e) {
			return null;
		}
	}
	
	


	

}
