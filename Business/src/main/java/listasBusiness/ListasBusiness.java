package main.java.listasBusiness;

import java.util.ArrayList;
import java.util.List;

import main.java.entities.Actividad;
import main.java.entities.Disponibilidad;
import main.java.entities.DisponibilidadHora;
import main.java.entities.Idioma;
import main.java.entities.NivelIdioma;

public class ListasBusiness {

	public static List<Actividad> getActividades(){
		
		List<Actividad> aActividades = new ArrayList<Actividad>();
		
		Actividad actividad = new Actividad();
		actividad.setCod("TDP");
		actividad.setDescCastellano("Turismo deportivo");
		actividad.setDescEuskera("eu-Turismo deportivo");
		actividad.setDescIngles("en-Turismo deportivo");
		
		aActividades.add(actividad);
		
		actividad = new Actividad();
		actividad.setCod("TDV");
		actividad.setDescCastellano("Turismo diversion");
		actividad.setDescEuskera("eu-Turismo diversion");
		actividad.setDescIngles("en-Turismo diversion");
		
		aActividades.add(actividad);
		
		actividad = new Actividad();
		actividad.setCod("TG");
		actividad.setDescCastellano("Turismo gastronomico");
		actividad.setDescEuskera("eu-Turismo gastronomico");
		actividad.setDescIngles("en-Turismo gastronomico");
		
		aActividades.add(actividad);
		
		actividad = new Actividad();
		actividad.setCod("TC");
		actividad.setDescCastellano("Turismo cultural");
		actividad.setDescEuskera("eu-Turismo cultural");
		actividad.setDescIngles("en-Turismo cultural");
		
		aActividades.add(actividad);
		
		return aActividades;
		
	}
	
	public static List<Disponibilidad> getDisponibilidadesActividad(){
		
		List<Disponibilidad> aDisponibilidades = new ArrayList<Disponibilidad>();
		
		Disponibilidad disponibilidad = new Disponibilidad();
		disponibilidad.setCod("ES");
		disponibilidad.setDescCastellano("Entre semana");
		disponibilidad.setDescEuskera("eu-Entre semana");
		disponibilidad.setDescIngles("en-Entre semana");
		
		aDisponibilidades.add(disponibilidad);
		
	    disponibilidad = new Disponibilidad();
		disponibilidad.setCod("FS");
		disponibilidad.setDescCastellano("Fines de semana");
		disponibilidad.setDescEuskera("eu-Fines de semana");
		disponibilidad.setDescIngles("en-Fines de semana");
		
		aDisponibilidades.add(disponibilidad);
		
		disponibilidad = new Disponibilidad();
		disponibilidad.setCod("CD");
		disponibilidad.setDescCastellano("Cualquier día");
		disponibilidad.setDescEuskera("eu-Cualquier día");
		disponibilidad.setDescIngles("en-Cualquier día");
		
		aDisponibilidades.add(disponibilidad);
		
		return aDisponibilidades;
		
	}
	
	public static List<DisponibilidadHora> getDisponibilidadesHoraActividad(){
		
		List<DisponibilidadHora> aDisponibilidadesHora = new ArrayList<DisponibilidadHora>();
		
		DisponibilidadHora disponibilidadHora = new DisponibilidadHora();
		disponibilidadHora.setCod("M");
		disponibilidadHora.setDescCastellano("Mañana");
		disponibilidadHora.setDescEuskera("eu-Mañana");
		disponibilidadHora.setDescIngles("en-Mañana");
		
		aDisponibilidadesHora.add(disponibilidadHora);
		
		disponibilidadHora = new DisponibilidadHora();
		disponibilidadHora.setCod("T");
		disponibilidadHora.setDescCastellano("Tarde");
		disponibilidadHora.setDescEuskera("eu-Tarde");
		disponibilidadHora.setDescIngles("en-Tarde");
		
		aDisponibilidadesHora.add(disponibilidadHora);
		
		disponibilidadHora = new DisponibilidadHora();
		disponibilidadHora.setCod("N");
		disponibilidadHora.setDescCastellano("Noche");
		disponibilidadHora.setDescEuskera("eu-Noche");
		disponibilidadHora.setDescIngles("en-Noche");
		
		aDisponibilidadesHora.add(disponibilidadHora);
		
		disponibilidadHora = new DisponibilidadHora();
		disponibilidadHora.setCod("C");
		disponibilidadHora.setDescCastellano("Cualquiera");
		disponibilidadHora.setDescEuskera("eu-Cualquiera");
		disponibilidadHora.setDescIngles("en-Cualquiera");
		
		aDisponibilidadesHora.add(disponibilidadHora);
		
		return aDisponibilidadesHora;
		
	}
	
	public static List<Idioma> getIdiomas(){
		
		List<Idioma> aIdiomas = new ArrayList<Idioma>();
		
		Idioma idioma = new Idioma();
		idioma.setCod("ES");
		idioma.setDescCastellano("Castellano");
		idioma.setDescEuskera("eu-Castellano");
		idioma.setDescIngles("en-Castellano");
		
		aIdiomas.add(idioma);
		
		idioma = new Idioma();
		idioma.setCod("FR");
		idioma.setDescCastellano("Frances");
		idioma.setDescEuskera("eu-Frances");
		idioma.setDescIngles("en-Frances");
		
		aIdiomas.add(idioma);
		
		idioma = new Idioma();
		idioma.setCod("EN");
		idioma.setDescCastellano("Ingles");
		idioma.setDescEuskera("eu-Ingles");
		idioma.setDescIngles("en-Ingles");
		
		aIdiomas.add(idioma);
		
		return aIdiomas;
		
	}
	
	public static List<NivelIdioma> getNivelIdiomas(){
		
		List<NivelIdioma> aNivelIdiomas = new ArrayList<NivelIdioma>();
		
		NivelIdioma nivelIdioma = new NivelIdioma();
		nivelIdioma.setCod("N");
		nivelIdioma.setDescCastellano("Nativo");
		nivelIdioma.setDescEuskera("eu-Nativo");
		nivelIdioma.setDescIngles("en-Nativo");
		
		aNivelIdiomas.add(nivelIdioma);
		
		nivelIdioma = new NivelIdioma();
		nivelIdioma.setCod("M");
		nivelIdioma.setDescCastellano("Medio");
		nivelIdioma.setDescEuskera("eu-Medio");
		nivelIdioma.setDescIngles("en-Medio");
		
		aNivelIdiomas.add(nivelIdioma);
		
		nivelIdioma = new NivelIdioma();
		nivelIdioma.setCod("B");
		nivelIdioma.setDescCastellano("Bajo");
		nivelIdioma.setDescEuskera("eu-Bajo");
		nivelIdioma.setDescIngles("en-Bajo");
		
		aNivelIdiomas.add(nivelIdioma);
		
		return aNivelIdiomas;
		
	}
	
}