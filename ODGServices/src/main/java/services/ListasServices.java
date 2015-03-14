package main.java.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import main.java.entities.Actividad;
import main.java.entities.Disponibilidad;
import main.java.entities.DisponibilidadHora;
import main.java.entities.Idioma;
import main.java.entities.NivelIdioma;
import main.java.listasBusiness.ListasBusiness;

import org.eclipse.persistence.oxm.JSONWithPadding;

@Path("/lista")
public class ListasServices {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getActividades")
	@Produces("application/x-javascript")
	public JSONWithPadding<Actividad> getActividades (@QueryParam("callback") String callback) {
		
		List<Actividad> aActividades = new ArrayList<Actividad>();
		
		aActividades = ListasBusiness.getActividades();
		
		return new JSONWithPadding (aActividades, callback);
		 
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getDisponibilidadesActividad")
	@Produces("application/x-javascript")
	public JSONWithPadding<Disponibilidad> getDisponibilidadesActividad (@QueryParam("callback") String callback) {
		
		List<Disponibilidad> aDisponibilidades = new ArrayList<Disponibilidad>();
		
		aDisponibilidades = ListasBusiness.getDisponibilidadesActividad();
		
		return new JSONWithPadding (aDisponibilidades, callback);
		 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getDisponibilidadesHoraActividad")
	@Produces("application/x-javascript")
	public JSONWithPadding<DisponibilidadHora> getDisponibilidadesHoraActividad (@QueryParam("callback") String callback) {
		
		List<DisponibilidadHora> aDisponibilidadesHora = new ArrayList<DisponibilidadHora>();
		
		aDisponibilidadesHora = ListasBusiness.getDisponibilidadesHoraActividad();
		
		return new JSONWithPadding (aDisponibilidadesHora, callback);
		 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getIdiomas")
	@Produces("application/x-javascript")
	public JSONWithPadding<Idioma> getIdiomas (@QueryParam("callback") String callback) {
		
		List<Idioma> aIdiomas = new ArrayList<Idioma>();
		
		aIdiomas = ListasBusiness.getIdiomas();
		
		return new JSONWithPadding (aIdiomas, callback);
		 
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getNivelIdiomas")
	@Produces("application/x-javascript")
	public JSONWithPadding<NivelIdioma> getNivelIdiomas (@QueryParam("callback") String callback) {
		
		List<NivelIdioma> aNivelIdiomas = new ArrayList<NivelIdioma>();
		
		aNivelIdiomas = ListasBusiness.getNivelIdiomas();
		
		return new JSONWithPadding (aNivelIdiomas, callback);
		 
	}
	
}