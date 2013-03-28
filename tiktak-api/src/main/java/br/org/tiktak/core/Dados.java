package br.org.tiktak.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Dados {
	UUID uuid;
	String usuario;
	String evento;

	
	public String getTimeStamp(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public Dados(String usuario, String evento){
		String time = getTimeStamp();
		UUID uuid = UUID.nameUUIDFromBytes((time + usuario + evento).getBytes());
		
		this.uuid = uuid;
		this.usuario = usuario;
		this.evento = evento;
	}
}
