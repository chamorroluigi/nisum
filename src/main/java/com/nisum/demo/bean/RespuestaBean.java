package com.nisum.demo.bean;

import java.util.Date;

public class RespuestaBean {
	
	private String mensaje;
	
	private String id;
	
	private Date creado;
	
	private Date modificado;
	
	private Date ultimologin;
	
	
	private String token;
	
	
	private Boolean esActivo;

	public RespuestaBean() {
		
	}
	

	public RespuestaBean(String mensaje) {
		
		this.mensaje=mensaje;
		
	}
	
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getCreado() {
		return creado;
	}


	public void setCreado(Date creado) {
		this.creado = creado;
	}


	public Date getModificado() {
		return modificado;
	}


	public void setModificado(Date modificado) {
		this.modificado = modificado;
	}


	public Date getUltimologin() {
		return ultimologin;
	}


	public void setUltimologin(Date ultimo_login) {
		this.ultimologin = ultimo_login;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Boolean getEsActivo() {
		return esActivo;
	}


	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	
	
	
	
	

}
