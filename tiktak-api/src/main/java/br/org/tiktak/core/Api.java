package br.org.tiktak.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.google.gson.Gson;

public class Api {
	
	
	public static void registrarEvento(String usuario, String evento){
		Dados dados = new Dados(usuario, evento);
		Gson gson = new Gson();
		String json = gson.toJson(dados) + "\n";
		
        try {
            File arquivo = criarArquivo();
            RandomAccessFile raf = new RandomAccessFile(arquivo,"rw");
            raf.seek(4);
            //fix me
            boolean estaVazio = raf.readChar() == ']';
            if(estaVazio){
            	raf.writeChars(json);
            }else{
            	raf.seek(raf.length()-6);
            	raf.writeChars(",\n" + json);
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private static File criarArquivo() throws IOException {
		
		File pasta = new File("../tiktak");
		if(!pasta.exists()){
			pasta.mkdir();
		}
		File arquivo = new File("../tiktak/tik.tak");
		if(!arquivo.exists()){
			arquivo.createNewFile();
			FileWriter writer = new FileWriter(arquivo);
			writer.write("[\n]");
			writer.close();
		}
		return arquivo;
	}
}
