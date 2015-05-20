package main.java.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import main.java.listasBusiness.ListasBusiness;

@Path("/lista")
public class ListasServices {
	
	@GET
	@Path("/getColeccion/{coleccion}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getColeccion (@QueryParam("callback") String callback, 
			@PathParam("coleccion") String coleccion) {
		
		return ListasBusiness.getColeccion(coleccion);
		 
	}	
	
}