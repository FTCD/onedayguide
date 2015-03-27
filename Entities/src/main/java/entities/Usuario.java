package main.java.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario{

	String userName;
	String password;
	String email;
	boolean colaborador;
	String direccion;
	Double latitud;
	Double longitud;
	String telefono;
	
	@SuppressWarnings("rawtypes")
	List<List> listaIdiomas;
	@SuppressWarnings("rawtypes")
	List<List> listaActividades;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Double getLatitud() {
		return latitud;
	}
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public Double getLongitud() {
		return longitud;
	}
	
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isColaborador() {
		return colaborador;
	}

	public void setColaborador(boolean colaborador) {
		this.colaborador = colaborador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@SuppressWarnings({ "rawtypes" })
	public List<List> getListaIdiomas() {
		return listaIdiomas;
	}

	@SuppressWarnings({ "rawtypes" })
	public void setListaIdiomas(List<List> listaIdiomas) {
		this.listaIdiomas = listaIdiomas;
	}

	@SuppressWarnings("rawtypes")
	public List<List> getListaActividades() {
		return listaActividades;
	}

	@SuppressWarnings("rawtypes")
	public void setListaActividades(List<List> listaActividades) {
		this.listaActividades = listaActividades;
	}

}