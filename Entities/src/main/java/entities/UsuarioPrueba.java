package main.java.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioPrueba{

	String userName;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
