package br.org.tiktak.core;

import java.util.Date;
import java.util.UUID;

public class Dados {
	private UUID uuid;
	private String usuario;
	private String evento;
	private Date time;
	
	public UUID getUuid() {
		return uuid;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getEvento() {
		return evento;
	}

	public Dados(String usuario, String evento){
		this.time = new Date();
		UUID uuid = UUID.randomUUID();		
		this.uuid = uuid;
		this.usuario = usuario;
		this.evento = evento;		
	}

	public Date getTime() {
		return time;
	}
}
