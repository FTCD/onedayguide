package main.java.listasBusiness;

import main.java.usuarioBD.ListasEstáticasBD;

public class ListasBusiness {
	
	public static String getColeccion(String coleccion){
		
		return ListasEstáticasBD.getColeccion(coleccion);
		
	}
	
}