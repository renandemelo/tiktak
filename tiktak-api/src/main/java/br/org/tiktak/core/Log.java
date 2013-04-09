package br.org.tiktak.core;

import java.util.LinkedList;

public class Log {
	private String sistema;
	private LinkedList<Evento> eventos;
	
	public String getSistema() {
		return sistema;
	}

	public LinkedList<Evento> getEventos() {
		return eventos;
	}
	
	public Log(String sistema){
		this.sistema = sistema;
		this.eventos = new LinkedList<Evento>();
	}
	
	public void registrarEvento(String usuario, String funcionalidade){
		Evento evento = new Evento(usuario, funcionalidade);
		this.eventos.add(evento);		
	}
}
