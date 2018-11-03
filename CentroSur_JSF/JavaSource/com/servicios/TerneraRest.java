package com.servicios;

import java.util.Date;
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

import com.entities.Ternera;
import com.entities.Usuario;
import com.enums.PerfilUsuario;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;
import com.services.TerneraEJBBean;

@Stateless
@Path("/terneras")
@Produces("text/plain")
public class TerneraRest {

	
	@EJB
	private TerneraEJBBean terneraEJBBean;
	
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public LinkedList<Ternera> obtenerTeneras () throws TamboException{
        LinkedList<Ternera> listaTerneras = null; 
    	try {
    		listaTerneras = terneraEJBBean.buscarTodasTernera(); 
		} catch (Exception e) {
			throw new TamboException(e.getMessage());
		}
       return listaTerneras;
    
    }
    
   
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void addTernera(Long idTernera, String nroCaravana, Long idGuachera, Long idMadre, Long idPadre, Date fechaNac, Long baja, RazaTernera raza, TipoParto parto, Double pesoNac) throws TamboException {
        try {
        	terneraEJBBean.altaTernera(idTernera, nroCaravana, idGuachera, idMadre, idPadre, fechaNac, baja, raza, parto, pesoNac);
		} catch (Exception e) {
			throw new TamboException(e.getMessage());
		}
    	
    }
      
    
    @DELETE
    @Path("/{idTernera}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUsuario(@PathParam("idTernera") Long idTernera, @PathParam("fechaBaja") Date  fechaBaja, @PathParam("motivo") String motivo) throws TamboException {
        terneraEJBBean.bajaTernera(idTernera, fechaBaja, motivo);
    }

	
	
	
	
}
