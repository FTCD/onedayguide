package main.java.usuarioBusiness;

import java.util.ArrayList;
import java.util.List;

import main.java.entities.Usuario;
import main.java.usuarioBD.UsuarioBD;

public class UsuarioBusiness {
	
	public static Usuario getUsuario(){
		
		Usuario u = new Usuario();		
		
		return u;
		
	}
	
	public static List<Usuario> getUsuarios(){

		//List<Usuario> aUsuarios = UsuarioBD.getUsuarios();
		
		List<String> idioma = new ArrayList<String>();
		idioma.add("Castellano");
		idioma.add("Nativo");
		List<List> listaIdiomas = new ArrayList<List>();
		listaIdiomas.add(idioma);
		
		List<String> actividad = new ArrayList<String>();
		actividad.add("Deporte");
		actividad.add("Entre semana");
		actividad.add("Día");
		List<List> listaActividades = new ArrayList<List>();
		listaActividades.add(actividad);
		
		listaIdiomas.add(idioma);
		Usuario usuario = new Usuario();
		usuario.setUserName("Iban");
		usuario.setColaborador(true);
		usuario.setDireccion("Prueba");
		usuario.setLatitud(43.1212476);
		usuario.setLongitud(-3.130108);
		usuario.setEmail("iban@gmail.com");
		usuario.setListaIdiomas(listaIdiomas);
		usuario.setListaActividades(listaActividades);
		
		List<Usuario> aUsuarios = new ArrayList<Usuario>();
		aUsuarios.add(usuario);
		
		return aUsuarios;

	}

}