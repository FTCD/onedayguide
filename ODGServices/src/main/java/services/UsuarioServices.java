package main.java.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import main.java.entities.Usuario;
import main.java.usuarioBusiness.UsuarioBusiness;

import org.eclipse.persistence.oxm.JSONWithPadding;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/usuario")
public class UsuarioServices {	
		
	@GET
	@Path("/getUser/{name}")
	@Produces("application/x-javascript")
	public JSONWithPadding<Usuario> getUser (@QueryParam("callback") String callback,
		@PathParam("name") String name) {
		
		Usuario u = new Usuario();
		
		u = UsuarioBusiness.getUsuario(); 
		
		return new JSONWithPadding<Usuario>(u,callback);
		 
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GET
	@Path("/getUsers")
	@Produces("application/x-javascript")
	public JSONWithPadding<Usuario> getUsers (@QueryParam("callback") String callback) {
		
		ArrayList<Usuario> aUsuarios = new ArrayList<Usuario>();
		
		aUsuarios = (ArrayList<Usuario>) UsuarioBusiness.getUsuarios();
		
		return new JSONWithPadding (aUsuarios,callback);
		 
	}
	
	@SuppressWarnings("rawtypes")
	@GET
	@Path("/registrarUsuario/{datos}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registrarUsuario (@QueryParam("callback") String callback, 
			@PathParam("datos") JSONObject inputJsonObj) {
		
		try {
			
			Usuario usuario = new Usuario();
			
			String datosFormulario = (String)inputJsonObj.get("formData");
			
			String[] aDatosFormulario = datosFormulario.split("&");
			
			String nomCampo = "";
			Object valorCampo = "";
			ArrayList<String> listaIdiomas = new ArrayList<String>();
			ArrayList<String> listaActividades = new ArrayList<String>();
			
			for(String sCampoValor : aDatosFormulario){
				
				String[] campoValor = sCampoValor.split("=");
				nomCampo = campoValor[0];
				valorCampo = campoValor.length>1?campoValor[1]:null;
				
				if(valorCampo!=null && !nomCampo.contains("idioma") && !nomCampo.contains("actividad")){
				
					String nomMetodo = nomCampo.charAt(0)+"";
					nomMetodo = "set" + nomMetodo.toUpperCase() + nomCampo.substring(1, nomCampo.length());
					
					Method[] methods = Usuario.class.getMethods();
					
					for(Method method : methods){
						
						if(method.getName().equalsIgnoreCase(nomMetodo)){
							
							Class[] parameters = method.getParameterTypes();
							Class claseParametro = parameters[0];
							if(claseParametro == boolean.class){
								if(valorCampo.toString().equalsIgnoreCase("on")){
									valorCampo = true;
								}else{
									valorCampo = false;
								}
							}else if(claseParametro == Double.class){
								valorCampo = Double.parseDouble(valorCampo.toString());
							}
							method.invoke(usuario, valorCampo);
							
							break;
							
						}
						
					}
					
				}else if(valorCampo!=null && (nomCampo.contains("idioma") || nomCampo.contains("actividad"))){
					
					if(nomCampo.contains("idioma")){
						listaIdiomas.add(valorCampo.toString());
					}else if(nomCampo.contains("actividad")){
						listaActividades.add(valorCampo.toString());
					}
					
				}
					
			}
			
			//usuario.setListaIdiomas(listaIdiomas);
			//usuario.setListaActividades(listaActividades);
	        
        } catch (JSONException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//return new JSONWithPadding (null, callback);
		 
	}
	
}