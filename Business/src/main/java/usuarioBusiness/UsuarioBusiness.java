package main.java.usuarioBusiness;

import main.java.entities.Usuario;
import main.java.usuarioBD.UsuarioBD;

public class UsuarioBusiness {
	
	public static Usuario getUsuario(){
		
		Usuario u = new Usuario();		
		
		return u;
		
	}
	
	public static String getUsers(String latitud, String longitud){

		//List<Usuario> aUsuarios = UsuarioBD.getUsuarios();
		
		//List<Usuario> aUsuarios = new ArrayList<Usuario>();
		
		/*List<String> idioma = new ArrayList<String>();
		idioma.add("Castellano");
		idioma.add("Nativo");
		List<List> listaIdiomas = new ArrayList<List>();
		listaIdiomas.add(idioma);
		
		idioma = new ArrayList<String>();
		idioma.add("Euskera");
		idioma.add("Nativo");
		listaIdiomas.add(idioma);
		
		idioma = new ArrayList<String>();
		idioma.add("Ingles");
		idioma.add("Nivel Medio");
		listaIdiomas.add(idioma);
		
		List<String> actividad = new ArrayList<String>();
		actividad.add("Turismo Deportivo");
		actividad.add("Entre semana");
		actividad.add("Día");
		List<List> listaActividades = new ArrayList<List>();
		listaActividades.add(actividad);
		
		actividad = new ArrayList<String>();
		actividad.add("Turismo de Fiesta");
		actividad.add("Fin de semana");
		actividad.add("Noche");
		listaActividades.add(actividad);
		
		actividad = new ArrayList<String>();
		actividad.add("Turismo cultural");
		actividad.add("Fin de semana");
		actividad.add("Tarde");
		listaActividades.add(actividad);
		
		actividad = new ArrayList<String>();
		actividad.add("Turismo gastronómico");
		actividad.add("Fin de semana");
		actividad.add("Día");
		listaActividades.add(actividad);
		
		actividad = new ArrayList<String>();
		actividad.add("Turismo de relax");
		actividad.add("Fin de semana");
		actividad.add("Día");
		listaActividades.add(actividad);
		
		actividad = new ArrayList<String>();
		actividad.add("Turismo de playa");
		actividad.add("Fin de semana");
		actividad.add("Día");
		listaActividades.add(actividad);
		
		Usuario usuario = new Usuario();
		usuario.setUserName("Iban");
		usuario.setColaborador(true);
		usuario.setDireccion("Prueba");
		usuario.setLatitud(43.1212476);
		usuario.setLongitud(-3.130108);
		usuario.setEmail("iban@gmail.com");
		usuario.setListaIdiomas(listaIdiomas);
		usuario.setListaActividades(listaActividades);
		
		aUsuarios.add(usuario);
		
		usuario = new Usuario();
		usuario.setUserName("Arturo");
		usuario.setColaborador(true);
		usuario.setDireccion("Prueba");
		usuario.setLatitud(43.2904541);
		usuario.setLongitud(-2.9895906);
		usuario.setEmail("arturo@gmail.com");
		usuario.setListaIdiomas(listaIdiomas);
		usuario.setListaActividades(listaActividades);
		
		aUsuarios.add(usuario);*/
		
		//return aUsuarios;
		
		return UsuarioBD.getUsers(latitud, longitud);

	}

}