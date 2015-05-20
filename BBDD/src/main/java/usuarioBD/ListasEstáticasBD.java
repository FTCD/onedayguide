package main.java.usuarioBD;

import java.io.IOException;

import main.java.utils.UtilsBD;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

public class ListasEstáticasBD {

	public static String getColeccion(String coleccion) {
		
		try {
			
			DB baseDatos = UtilsBD.getBaseDatos();
			
			DBCollection tabla = UtilsBD.getColeccion(baseDatos, coleccion);
			
			DBCursor cursor = tabla.find();

	        return JSON.serialize(cursor);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}