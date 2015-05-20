package main.java.utils;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class UtilsBD {
	
    public UtilsBD() throws IOException {

    }
  		
    public static DB getBaseDatos() throws IOException {

    	//String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
    	//int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
    	String host = "Iban";
    	int port = 27017;
        
        //String user = System.getenv("admin");
        //String password = System.getenv("b6iuICaK79EW");

        try {
        	
        	//OPENSHIFT
        	/*MongoCredential credential = MongoCredential.createCredential(user, baseDeDatos, password.toCharArray());
        	MongoClient mongoClient = new MongoClient(new ServerAddress(mongoURIString), Arrays.asList(credential));
        	
        	boolean auth = dataBaseDB.authenticate("admin", "b6iuICaK79EW".toCharArray());*/
        	
        	//LOCAL
        	MongoClient mongoClient = new MongoClient(new ServerAddress(host, port));
            DB dataBaseDB = mongoClient.getDB("onedayguide");
            boolean auth = true;
            
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
    
    public static DBCollection getColeccion(DB database, String mongoColeccion) 
    		throws IOException {
    	
        DBCollection coleccion = database.getCollection(mongoColeccion);
        
        return coleccion;

    }
    
    @SuppressWarnings("null")
	public static DBCollection setColeccion(DB database, String mongoColeccion, DBObject dbObject) 
			throws IOException {
    	
        DBCollection coleccion = database.getCollection(mongoColeccion);
        if(coleccion == null){
        	coleccion.insert(dbObject);
        }
        return coleccion;

    }
    
    public static void closeConexion(MongoClient mongoClient) throws IOException {
    	
    	mongoClient.close();
    	
    }
 
}