package br.org.tiktak.core;

import java.util.ArrayList;

public class Eventv2 {
	private static Eventv2 instanciaSingleton;
	private String system;
	private ArrayList<Event> events;

	public String getSystem() {
		return system;
	}

	public ArrayList<Event> getEvent() {
		return events;
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
		this.system = null;
		this.events = new ArrayList<Event>();
    }
    
    // Construtor privado. Suprime o construtor público padrao.
    public void Init(final String system) {
         // Operações de inicialização da classe
		this.system = system;
    }

	public void log(final String user, final String funcionality) {
		Event event = new Event(user, funcionality);
		this.events.add(event);
	}
}