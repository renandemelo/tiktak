package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {

	public void log(String usuario, String evento) {
		Event dados = new Event(usuario, evento);

		String json = GsonFactory.getGson().toJson(dados) + "\n";

		try {
			File arquivo = createLoggerVersion1();
			RandomAccessFile raf = new RandomAccessFile(arquivo, "rw");
			raf.readLine();
			char c = raf.readLine().charAt(0);  
			boolean estaVazio1 = c == ']';
			if (estaVazio1) {
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

	private File createLoggerVersion1() throws IOException {

		File pasta = new File("../tiktakBD");
		if (!pasta.exists()) {
			pasta.mkdir();
		}
		File arquivo = new File("../tiktakBD/tik.tak");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo, "rw");
			writer.write("[\n]".getBytes());
			writer.close();
		}
		return arquivo;
	}
	
	private File createLoggerVersion2() throws IOException {

		File pasta = new File("../tiktakBD");
		if (!pasta.exists()) {
			pasta.mkdir();
		}
		File arquivo = new File("../tiktakBD/tik2.tak");
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo, "rw");
			writer.write("{\n}".getBytes());
			writer.close();
		}
		return arquivo;
	}
}