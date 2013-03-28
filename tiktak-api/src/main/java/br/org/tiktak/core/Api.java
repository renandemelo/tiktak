package br.org.tiktak.core;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Api {
	public static void registrarEvento(String usuario, String evento){
		Dados dados = new Dados(usuario, evento);
		Gson gson = new Gson();
		String json = gson.toJson(dados);
		
        try {
            FileWriter writer = new FileWriter("tik.tak", true);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
