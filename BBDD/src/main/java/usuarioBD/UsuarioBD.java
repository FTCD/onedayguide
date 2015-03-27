package main.java.usuarioBD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.entities.Usuario;
import main.java.utils.UtilsBD;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UsuarioBD {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Usuario> getUsuarios(){
		
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		Usuario usuario = new Usuario();
		
		try {
			
			//Para pruebas en servidor openshift
			DB baseDatos = UtilsBD.getBaseDatos();
			
			DBCollection tablaUsuarios = UtilsBD.getColeccion(baseDatos, "usuarios");
			
			DBCursor cursor = tablaUsuarios.find();
			
			while (cursor.hasNext()) {
				
				usuario = new Usuario();
				
				DBObject usuarioObject = (DBObject) cursor.next();
				usuario.setUserName(usuarioObject.get("userName").toString());
				usuario.setDireccion(usuarioObject.get("direccion").toString());
				usuario.setColaborador(Boolean.valueOf(usuarioObject.get("userName").toString()));
				usuario.setLatitud(Double.valueOf(usuarioObject.get("latitud").toString()));
				usuario.setLongitud(Double.valueOf(usuarioObject.get("longitud").toString()));
				usuario.setEmail(usuarioObject.get("email").toString());
				usuario.setTelefono(usuarioObject.get("telefono").toString());
				usuario.setListaActividades(
					(List<List>) usuarioObject.get("listaActividadesDisponibilidad"));
				usuario.setListaIdiomas(
					(List<List>) usuarioObject.get("listaIdiomas"));
				listaUsuarios.add(usuario);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaUsuarios;
		
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