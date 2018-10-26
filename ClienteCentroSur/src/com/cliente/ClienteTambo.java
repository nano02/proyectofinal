package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.services.TamboBeanRemote;
import com.excepciones.TamboException;

public class ClienteTambo {

	/**
	 * @param args
	 * @throws NamingException
	 * @throws TamboExcepcion 
	 */
	
	public static void main(String[] args) throws NamingException, TamboException {
		// TODO Auto-generated method stub
		/*System.out.println("Voy a conectarme"); //funciona!!
		TamboBeanRemote tamboBean = (TamboBeanRemote) InitialContext 
				.doLookup("CentroSur/TamboBean!com.servicios.TamboBeanRemote");
		System.out.println("Me conecté"); // NO funciona 
		Ternera ternera = new Ternera ("GUACHERA 1", new Long(1234567899), new Long(1215872598), new Long(1357489657), new java.sql.Date(22/11/2017), 3097.25, RazaTernera.JERSEY, TipoParto.NORMAL, 0);
		Long idTernera = tamboBean.altaTernera(ternera);
		System.out.println("Di de alta la ternera");
	}
	Se comenta para que no de error y porque se cree que el cliente son las interfaces y que este cliente quedó del intento
	de pasaje a nivel 3 consultar en el equipo por las dudas
	*/

}
}
