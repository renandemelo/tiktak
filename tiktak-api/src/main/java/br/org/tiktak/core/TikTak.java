package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {

	public void log(String sistema, String usuario, String evento) {
		Event dados = new Event(sistema, usuario, evento);

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
		String diretorio = System.getProperty("tiktak.dir");
		String caminhoDoArquivo;
		
		if(diretorio == null){
			System.out.println("diretorio é null");
			diretorio = "";
		}
		else if(!diretorio.endsWith("/")){
			System.out.println("diretorio não é null! =D " + diretorio);
			diretorio += "/";
			
			File diretorioFisico = new File(diretorio);
			
			if (!diretorioFisico.exists()) {
				System.out.println("diretorio criado! o/");
				diretorioFisico.mkdir();
			}
		}
		
		caminhoDoArquivo = diretorio + "tik.tak";
		
		File arquivo = new File(caminhoDoArquivo);
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