package br.org.tiktak.core;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Api {
	public static void registrarEvento(String usuario, String evento){
		Dados dados = new Dados(usuario, evento);
		Gson gson = new Gson();
		//gson.toJson(dados);
		String json = gson.toJson(dados);
		System.out.println(json);
		
        try {
            FileWriter writer = new FileWriter("tik.tak");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		//new GsonBuilder().setPrettyPrinting().create();
		//gson.
//		Writer data = new OutputStreamWriter(
//			        Main.class.getResourceAsStream("books.json"), "UTF-8");
		// Api a = new Api('usuario', 'evento');
	}
	
	public static void main(String[] args){
		//Api a = new Api();
		Api.registrarEvento("albert", "cadastro");
	}
}
