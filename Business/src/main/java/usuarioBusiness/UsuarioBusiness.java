package main.java.usuarioBusiness;

import main.java.usuarioBD.UsuarioBD;

public class UsuarioBusiness {
	
	/*public static Usuario getUsuario(){
		
		Usuario u = new Usuario();		
		
		return u;
		
	}*/
	
	public static String getUsers(String latitud, String longitud, String distancia){
		
		return UsuarioBD.getUsers(latitud, longitud, distancia);

	}

}