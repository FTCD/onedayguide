package main.java.utils;

import java.io.IOException;
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
    	
    	System.out.println("GETBASEDATOS");
        String user = "admin";
        String password = "b6iuICaK79EW";
    	MongoCredential credential = MongoCredential.createCredential(user, baseDeDatos, password.toCharArray());
    	System.out.println("CREDENTIALS");
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoURIString), Arrays.asList(credential));
        System.out.println("CLIENT");
        DB dataBaseDB = mongoClient.getDB(baseDeDatos);
        System.out.println("DATABASE");
        return dataBaseDB;
        
    }
    
    public static DBCollection getColeccion(DB database, String mongoColeccion) throws IOException {
    	
        DBCollection coleccion = database.getCollection(mongoColeccion);
        return coleccion;

    }
    
    public static void closeConexion(MongoClient mongoClient) throws IOException {
    	
    	mongoClient.close();
    	
    }
 
}