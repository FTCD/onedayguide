package main.java.utils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class UtilsBD {
	
    public UtilsBD() throws IOException {

    }
  		
    public static DB getBaseDatos(String mongoURIString, String baseDeDatos) throws IOException {

    	String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
    	int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        
        //String user = System.getenv("admin");
        //String password = System.getenv("b6iuICaK79EW");

        try {
        	
        	//MongoCredential credential = MongoCredential.createCredential(user, baseDeDatos, password.toCharArray());
        	//MongoClient mongoClient = new MongoClient(new ServerAddress(mongoURIString), Arrays.asList(credential));
        	MongoClient mongoClient = new MongoClient(new ServerAddress(host, port));
            DB dataBaseDB = mongoClient.getDB("onedayguide");
            
            boolean auth = dataBaseDB.authenticate("admin", "b6iuICaK79EW".toCharArray());
        	if (auth) {
         
        		return dataBaseDB;
        		
        	} else {

        		return null;
        		
        	}
            
        } catch (UnknownHostException e) {
        	System.out.println("ERROR");
        }
        
        return null;
        
    }
    
    public static DBCollection getColeccion(DB database, String mongoColeccion) throws IOException {
    	
        DBCollection coleccion = database.getCollection(mongoColeccion);
        return coleccion;

    }
    
    public static void closeConexion(MongoClient mongoClient) throws IOException {
    	
    	mongoClient.close();
    	
    }
 
}