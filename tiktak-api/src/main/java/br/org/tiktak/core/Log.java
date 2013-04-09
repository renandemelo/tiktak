package br.org.tiktak.core;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

public class Log {
	private UUID uuid;
	private String sistema;
	private String usuario;
	private LinkedList<String> eventos;
	private Date time;
	
	public UUID getUuid() {
		return uuid;
	}
	
	public String getSistema() {
		return sistema;
	}

	public String getUsuario() {
		return usuario;
	}

	public LinkedList<String> getEventos() {
		return eventos;
	}
	
	public Date getTime() {
		return time;
	}

	public Log(String sistema){
		this.sistema = sistema;
		this.eventos = new LinkedList<String>();
	}
	
	public void registrarEvento(String usuario, String evento){
		this.time = new Date();
		UUID uuid = UUID.randomUUID();		
		this.uuid = uuid;
		this.usuario = usuario;
		this.eventos.add(evento);		
	}
}
