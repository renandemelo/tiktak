package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {
	String dir;

	public TikTak(){
		dir = null;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public void log(String sistema, String usuario, String evento) {
		Event dados = new Event(sistema, usuario, evento);

		String json = GsonFactory.getGson().toJson(dados) + "\n";

		try {
			File arquivo = criarArquivoLogVersion1();
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

	private String criarPastaLog() throws IOException {
		String diretorio = "";
		String parametroSetDir, parametroGetProperty;
		
		parametroSetDir = this.dir;
		parametroGetProperty = System.getProperty("tiktak.dir");
		if (parametroSetDir != null){
			diretorio = parametroSetDir;
		} else if (parametroGetProperty != null) {
			diretorio = parametroGetProperty;
		}
		if ((diretorio != "") && (!diretorio.endsWith("/"))){
			System.out.println("diretorio não é null! =D " + diretorio);
			diretorio += "/";
			
			File diretorioFisico = new File(diretorio);
			
			if (!diretorioFisico.exists()) {
				System.out.println("diretorio criado! o/");
				diretorioFisico.mkdir();
			}
		}
		return diretorio;
	}

	
	private File criarArquivoLogVersion1() throws IOException {
		String diretorio = criarPastaLog();
		String caminhoDoArquivo = diretorio + "tik.tak";
		
		File arquivo = new File(caminhoDoArquivo);
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo, "rw");
			writer.write("[\n]".getBytes());
			writer.close();
		}
		return arquivo;
	}
	
	private File criarArquivoLogVersion2() throws IOException {

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