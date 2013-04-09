package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Api {
	
	
	public static void registrarEvento(String sistema, String usuario, String evento){
		Log log = new Log(sistema);
		log.registrarEvento(usuario, evento);
		
		String json = GsonFactory.getGson().toJson(log) + "\n";
		
        try {
            File arquivo = criarArquivo(sistema);
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

	private static File criarArquivo(String sistema) throws IOException {
		
		File pasta = new File("../tiktakBD");
		if(!pasta.exists()){
			pasta.mkdir();
		}
		File arquivo = new File("../tiktakBD/tik.tak");
		if(!arquivo.exists()){
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo,"rw");
			writer.write(("{\n" + 
						 '"' + "sistema" + '"' + ":" + '"' + sistema + '"' + ",\n" +
						 '"' + "eventos" + '"' + ":" + "[\n]\n}").getBytes());
			writer.close();
		}
		return arquivo;
	}
}
