package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Api {
	
	
	public static void registrarEvento(String usuario, String evento){
		Dados dados = new Dados(usuario, evento);
		
		String json = GsonFactory.getGson().toJson(dados) + "\n";
		
        try {
            File arquivo = criarArquivo();
            RandomAccessFile raf = new RandomAccessFile(arquivo,"rw");
            raf.readLine();
            boolean estaVazio = raf.readLine().equals("]");
            if(estaVazio){
            	raf.seek(2);
            	raf.write(json.getBytes());
            } else {
            	raf.seek(raf.length() - 2);
            	raf.write((",\n" + json).getBytes());
            }
            raf.write("]".getBytes());
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
			RandomAccessFile writer = new RandomAccessFile(arquivo,"rw");
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arquivo), "UTF-8"));
			writer.write("[\n]".getBytes());
			writer.close();
		}
		return arquivo;
	}
}
