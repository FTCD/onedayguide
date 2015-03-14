package main.java.usuarioBD;

import java.io.IOException;

import main.java.utils.UtilsBD;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class UsuarioBD {

	public static String getUsuarios(){
		
		String usuario = "";
		
		try {
			
			DB baseDatos = UtilsBD.getBaseDatos("mongodb://$OPENSHIFT_MONGODB_DB_HOST:$OPENSHIFT_MONGODB_DB_PORT/", "onedayguide");
			DBCollection tablaUsuarios = UtilsBD.getColeccion(baseDatos, "usuarios");
			
			DBCursor cursor = tablaUsuarios.find();
			
			while (cursor.hasNext()) {
				
				DBObject usuarioObject = (DBObject) cursor.next();
				usuario = usuarioObject.toString();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
		
	}
	
}