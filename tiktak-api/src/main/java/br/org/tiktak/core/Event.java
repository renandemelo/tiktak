package br.org.tiktak.core;

import java.util.Date;
import java.util.UUID;

public class Event {
	private UUID uuid;
	private String sistema;
	private String usuario;
	private String funcionalidade;
	private Date time;

	public Event(String sistema, String usuario, String funcionalidade){
		this.time = new Date();
		UUID uuid = UUID.randomUUID();		
		this.uuid = uuid;
		this.sistema = sistema;
		this.usuario = usuario;
		this.funcionalidade = funcionalidade;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public String getUser() {
		return usuario;
	}
	
	public String getFuncionalidade() {
		return funcionalidade;
	}

	public Date getTime() {
		return time;
	}

	public String getSistema() {
		return sistema;
	}

}
