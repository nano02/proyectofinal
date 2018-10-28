package com.servicios;

import java.util.List;

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
import com.excepciones.TamboException;
import com.services.UsuarioEJBBean;

@Stateless
@Path("/usuarios")
@Produces("text/plain")
public class UsuarioRest {

	@EJB
	private UsuarioEJBBean usuariosEJBBean;
	
    // URI:
    // /contextPath/servletPath/usuarios
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Usuario> getUsuarios() {
        return usuariosEJBBean.obtenerUsuarios();
    }
 
    // URI:
    // /contextPath/servletPath/usuarios
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addUsuario(String nombre, String apellido, String clave, String perfil) throws TamboException {
        usuariosEJBBean.altaUsuario(nombre, apellido, clave, perfil);
    }
  
    @DELETE
    @Path("/{idUsuario}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUsuario(@PathParam("idUsuario") Long idUsuario) throws TamboException {
        usuariosEJBBean.eliminarUsuario(null);
    }

	
}
