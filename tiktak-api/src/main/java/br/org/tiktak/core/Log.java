package br.org.tiktak.core;

import java.util.ArrayList;

public class Log {
	private final String system;
	private final ArrayList<Event> events;

	public String getSystem() {
		return system;
	}

	public ArrayList<Event> getEvent() {
		return events;
	}

	public Log(final String system) {
		this.system = system;
		this.events = new ArrayList<Event>();
	}

	public void log(final String system, final String user, final String funcionality) {
		Event event = new Event(user, funcionality);
		this.events.add(event);
	}
}
