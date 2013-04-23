package br.org.tiktak.core;

import java.util.Date;
import java.util.UUID;

public class Event {
	private UUID uuid;
	private String user;
	private String funcionality;
	private Date time;

	public Event(String user, String funcionality){
		this.time = new Date();
		UUID uuid = UUID.randomUUID();		
		this.uuid = uuid;
		this.user = user;
		this.funcionality = funcionality;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getFuncionality() {
		return funcionality;
	}

	public Date getTime() {
		return time;
	}
}
