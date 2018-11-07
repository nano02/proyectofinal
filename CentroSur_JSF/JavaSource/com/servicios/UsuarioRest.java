package com.servicios;

import java.util.LinkedList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;
import com.services.UsuarioBean;
import com.sun.mail.smtp.DigestMD5;

@Stateless
@Path("/usuarios")
@Produces("text/plain")
public class UsuarioRest {

	@EJB
	private UsuarioBean usuariosEJBBean;
	
    // URI:
    // /contextPath/servletPath/usuarios
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public LinkedList<Usuario> obtenerUsuarios() throws TamboException{
        LinkedList<Usuario> listaUsuarios = null; 
    	try {
    		 listaUsuarios = usuariosEJBBean.obtenerUsuarios();
		} catch (Exception e) {
			throw new TamboException(e.getMessage());
		}
       return listaUsuarios;
    
    }
    
   
    // URI:
    // /contextPath/servletPath/usuarios
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addUsuario(String nombre, String apellido, String clave, String perfil, String nombreUsuario) throws TamboException {
        try {
        	
        	usuariosEJBBean.altaUsuario(nombre, apellido, clave, PerfilUsuario.valueOf(perfil), nombreUsuario);
		} catch (Exception e) {
			throw new TamboException(e.getMessage());
		}
    	
    }
      
    
    @DELETE
    @Path("/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUsuario(@PathParam("idUsuario") Long idUsuario) throws TamboException {
        usuariosEJBBean.eliminarUsuario(idUsuario);
    }

	
}
