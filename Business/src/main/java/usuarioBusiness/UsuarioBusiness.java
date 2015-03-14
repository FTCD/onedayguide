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
		
		System.out.println("GETUSUARIOS");
		String usuario = UsuarioBD.getUsuarios();
		
		String prueba = usuario;
		
		List<Usuario> aUsuarios = new ArrayList<Usuario>();
		
		ArrayList<List> aActividadesDisponibilidades = new ArrayList<List>();
		ArrayList<String> aDatos = new ArrayList<String>();
		aDatos.add("Turismo deportivo");
		aDatos.add("Entre semana");
		aDatos.add("Mañana");
		aActividadesDisponibilidades.add(aDatos);
		aDatos = new ArrayList<String>();
		aDatos.add("Turismo nocturno");
		aDatos.add("Entre semana");
		aDatos.add("Noche");
		aActividadesDisponibilidades.add(aDatos);
		aDatos = new ArrayList<String>();
		aDatos.add("Turismo gastronomico");
		aDatos.add("Entre semana");
		aDatos.add("Mañana");
		aActividadesDisponibilidades.add(aDatos);
		aDatos = new ArrayList<String>();
		aDatos.add("Turismo cultural");
		aDatos.add("Entre semana");
		aDatos.add("Tarde");
		aActividadesDisponibilidades.add(aDatos);
		
		ArrayList<List> aIdiomasNivel = new ArrayList<List>();
		aDatos = new ArrayList<String>();
		aDatos.add("Castellano");
		aDatos.add("Nativo");
		aIdiomasNivel.add(aDatos);
		aDatos = new ArrayList<String>();
		aDatos.add("Ingles");
		aDatos.add("Medio");
		aIdiomasNivel.add(aDatos);
		
		Usuario user = new Usuario();

		//user.setUserName(usuario);
		user.setUserName("locura");
		user.setPassword("prueba");
		user.setEmail("user1@gmail.com");
		user.setListaIdiomas(aIdiomasNivel);
		user.setListaActividades(aActividadesDisponibilidades);
		user.setLatitud(43.3595019);
		user.setLongitud(-3.0051472);
		
		aUsuarios.add(user);
		
		user = new Usuario();
		user.setUserName("User 2");
		user.setPassword("prueba");
		user.setEmail("user2@gmail.com");
		user.setListaIdiomas(aIdiomasNivel);
		user.setListaActividades(aActividadesDisponibilidades);
		user.setLatitud(43.062897);
		user.setLongitud(-3.005718);
		
		aUsuarios.add(user);
		
		user = new Usuario();
		user.setUserName("User 3");
		user.setPassword("prueba");
		user.setEmail("user3@gmail.com");
		user.setListaIdiomas(aIdiomasNivel);
		user.setListaActividades(aActividadesDisponibilidades);
		user.setLatitud(43.150625);
		user.setLongitud(-2.956279);
		
		aUsuarios.add(user);
		
		user = new Usuario();
		user.setUserName("User 4");
		user.setPassword("prueba");
		user.setEmail("user4@gmail.com");
		user.setListaIdiomas(aIdiomasNivel);
		user.setListaActividades(aActividadesDisponibilidades);
		user.setLatitud(43.207206);
		user.setLongitud(-3.061336);
		
		aUsuarios.add(user);
		
		return aUsuarios;

	}

}