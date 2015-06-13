package main.java.usuarioBD;

import java.io.IOException;

import main.java.utils.UtilsBD;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

public class UsuarioBD {

	public static String getUsers(String latitud, String longitud, String distancia){
		
		try {
			
			//Se obtiene la base de datos
			DB baseDatos = UtilsBD.getBaseDatos();
			
			//Se forma la query necesaria para hacer la consulta por coordenadas
			
			//Distancia de búsqueda
			//double distance = 20000; //20 kms
			double distance = Double.parseDouble(distancia);

			
			//Se pasan las coordenadas pasadas por parámetro que hacen referencia
			//al centro del mapa
			BasicDBList geoCoord = new BasicDBList();
			geoCoord.add(Double.parseDouble(longitud));
			geoCoord.add(Double.parseDouble(latitud));
			
			//Se meten las coordenadas como un punto para hacer la búsqueda
			BasicDBObject coordinates = new BasicDBObject("type", "Point");
			coordinates.append("coordinates", geoCoord);
			BasicDBObject geometry = new BasicDBObject("$geometry", coordinates);
			
			//Se añade al objeto la distancia máxima de búsqueda
			geometry.append("$maxDistance", distance);
			
			//Se añade la condición de cercanía
			BasicDBObject near = new BasicDBObject();
			near.append("$nearSphere", geometry);
			
			//Se añade la query sobre el campo GEOJSON sobre el que se efectuará
			BasicDBObject query = new BasicDBObject();
			query.append("localizacion", near); //"localizacion" es el nombre del objeto GEOJON en la BD
			
			//Se lanza la consulta
			DBCursor cursor = (UtilsBD.getColeccion(baseDatos, "usuarios")).find(query);
			
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