package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Api {
	private Log log;
	private File arquivo;
	
	public Api(String sistema){
		log = new Log(sistema);
		try {
			this.arquivo = criarArquivo(sistema);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void registrarEvento(String usuario, String evento){
		log.registrarEvento(usuario, evento);
		
		String json = GsonFactory.getGson().toJson(log) + "\n";
		
        try {
            RandomAccessFile raf = new RandomAccessFile(this.arquivo,"rw");
            raf.readLine();
            boolean estaVazio = raf.readLine().equals("]");
//            if(estaVazio){
//            	raf.seek(2);
//            	raf.write(json.getBytes());
//            } else {
            raf.seek(raf.length() - 4);
            raf.write(("," + json).getBytes());
//            }
            raf.write("\n]\n}".getBytes());
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private File criarArquivo(String sistema) throws IOException {
		
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
