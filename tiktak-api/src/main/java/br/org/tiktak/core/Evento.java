package br.org.tiktak.core;

import java.util.Date;
import java.util.UUID;

public class Evento {
	private UUID uuid;
	private String usuario;
	private String funcionalidade;
	private Date time;

	public Evento(String usuario, String funcionalidade){
		this.time = new Date();
		UUID uuid = UUID.randomUUID();		
		this.uuid = uuid;
		this.usuario = usuario;
		this.funcionalidade = funcionalidade;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getFuncionalidade() {
		return funcionalidade;
	}

	public Date getTime() {
		return time;
	}
}
