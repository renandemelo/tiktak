package br.org.tiktak.core;

import com.google.gson.Gson;

public class Api {
	public static void registrarEvento(String usuario, String evento){
		Dados dados = new Dados(usuario, evento);
		Gson gson = new Gson();
		//gson.toJson(dados);
		System.out.println(gson.toJson(dados));
		//new GsonBuilder().setPrettyPrinting().create();
		//gson.
		//Writer data = new OutputStreamWriter(
		//	        Main.class.getResourceAsStream("books.json"), "UTF-8");
		// Api a = new Api('usuario', 'evento');
	}
	
	public static void main(String[] args){
		//Api a = new Api();
		Api.registrarEvento("albert", "cadastro");
	}
}
