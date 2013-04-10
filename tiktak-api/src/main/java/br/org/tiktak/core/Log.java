package br.org.tiktak.core;

import java.util.ArrayList;

public class Log {
	private final String sistema;
	private final ArrayList<Evento> eventos;

	public String getSistema() {
		return sistema;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public Log(final String sistema) {
		this.sistema = sistema;
		this.eventos = new ArrayList<Evento>();
	}

	public void registrarEvento(final String usuario, final String funcionalidade) {
		Evento evento = new Evento(usuario, funcionalidade);
		this.eventos.add(evento);
	}
}
