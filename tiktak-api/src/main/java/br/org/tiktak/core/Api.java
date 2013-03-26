package br.org.tiktak.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Api {
	String usuario;
	String evento;

	public Api(String usuario, String evento){
		this.usuario = usuario;
		this.evento = evento;
		
		Gson gson = new Gson();
		gson.toJson(usuario);
		gson.toJson(evento);
		System.out.println(gson.toJson());
		//new GsonBuilder().setPrettyPrinting().create();
		//gson.
		//Writer data = new OutputStreamWriter(
	}
	
//	public static void registrarEvento(String usuario, String evento){
//		Gson gson = new Gson();
//		gson.
//		new GsonBuilder().setPrettyPrinting().create();
//		//gson.
//		//Writer data = new OutputStreamWriter(
//		//	        Main.class.getResourceAsStream("books.json"), "UTF-8");
//		// Api a = new Api('usuario', 'evento');
//	}
	public static void main(String[] args){
		Api a = new Api("usuario", "evento");
	}
}
