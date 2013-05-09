package br.org.tiktak.core;

import java.util.ArrayList;

public class Eventv2 {
	private static Eventv2 instanciaSingleton;
	private String sistema;
	private ArrayList<Event> eventos;

	public String getSystem() {
		return sistema;
	}

	public ArrayList<Event> getEvent() {
		return eventos;
	}
	
    // Método público estático de acesso único ao objeto!
    public static synchronized Eventv2 getInstance(){
    	if(instanciaSingleton == null) {
    		instanciaSingleton = new Eventv2();
    	}
    	return instanciaSingleton;
    }

    // Construtor privado. Suprime o construtor público padrao.
    private Eventv2() {
         // Operações de inicialização da classe
		this.sistema = null;
		this.eventos = new ArrayList<Event>();
    }
    
    // Construtor privado. Suprime o construtor público padrao.
    public void Init(final String system) {
         // Operações de inicialização da classe
		this.sistema = system;
    }

	public void log(final String user, final String funcionality) {
		Event event = new Event(user, funcionality);
		this.eventos.add(event);
	}
}