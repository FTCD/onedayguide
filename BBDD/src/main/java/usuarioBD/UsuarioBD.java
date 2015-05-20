package main.java.usuarioBD;

import java.io.IOException;

import main.java.utils.UtilsBD;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class UsuarioBD {

	public static String getUsers(String latitud, String longitud){
		
		try {
			
			DB baseDatos = UtilsBD.getBaseDatos();
			
			double[] coords = new double[2];
			coords[0] = Double.parseDouble(longitud);
			coords[1] = Double.parseDouble(latitud);
			
			System.out.println("COORDENADAS: " + coords[0] + ", " + coords[1]);
			
			long distance = 1000;

			DBObject query = BasicDBObjectBuilder.start()
			    .push("location")
			        .add("$maxDistance", distance)
			        .push("$near")
			            .push("$geometry")
			                .add("type", "Point")
			                .add("coordinates", coords)
			    .get();
			
			DBCollection collection = UtilsBD.getColeccion(baseDatos, "usuarios");
			
			DBCursor cursor = collection.find(query);
			
			try {
			    while(cursor.hasNext()) {
			        System.out.println(cursor.next());
			    }
			} finally {
			    cursor.close();
			}
			
			System.out.println("CURSOR :" + cursor);
			
	        return JSON.serialize(cursor);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static void setUsuario(){
		
		try {
			
			//Para pruebas en servidor openshift
			DB baseDatos = UtilsBD.getBaseDatos();
			
			/*DBObject dbObject = (DBObject) JSON.parse("{'userName':'iban', 'password':'prueba', 'email':'ibanllano@gmail.com', "
				+ "'colaborador':'true', 'direccion':'', 'latitud':'43.1210092', 'longitud':'-3.1325047', 'telefono':'956235623',}");
			
			DBCollection tablaUsuarios = UtilsBD.setColeccion(baseDatos, "usuarios", dbObject);*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}