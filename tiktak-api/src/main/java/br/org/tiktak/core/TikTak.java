package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {
	private String caminhoDaPasta;
	private String caminhoDoArquivo;
	private String nomeDoSistema;
	private File arquivo;

	public TikTak(String sistema){
		caminhoDaPasta = "";
		this.nomeDoSistema = sistema;
	}
	
	public void setDir(String nomeDaPasta) {
		if (!nomeDaPasta.endsWith("/"))
			nomeDaPasta += "/";
		this.caminhoDaPasta = nomeDaPasta;
	}
	
	public void log(String usuario, String nomeDoEvento) {
		Event evento = new Event(usuario, nomeDoEvento);
		try {
			criarPastaLog();
			obterCaminhoDoArquivo();
			criarArquivoLog();
			String json = GsonFactory.getGson().toJson(evento) + "\n";
			concatenarJson(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void concatenarJson(String json) {
		try{
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
		
		parametroSetDir = this.caminhoDaPasta;
		parametroGetProperty = System.getProperty("tiktak.dir");
		if (parametroSetDir != null){
			diretorio = parametroSetDir;
		} else if (parametroGetProperty != null) {
			diretorio = parametroGetProperty;
		}
		if (diretorio != "") {
			System.out.println("diretorio não é null! =D " + diretorio);
			
			File diretorioFisico = new File(diretorio);
			
			if (!diretorioFisico.exists()) {
				diretorioFisico.mkdir();
				System.out.println("diretorio criado! o/");
			}
		}
		return diretorio;
	}
	
	private String obterCaminhoDoArquivo() throws IOException {
		String arquivo = "";
		String parametroSetArquivo, parametroGetProperty;
		
		parametroSetArquivo = this.nomeDoSistema;
		parametroGetProperty = System.getProperty("tiktak.system");
		if (parametroSetArquivo != null){
			nomeDoSistema = this.caminhoDaPasta + parametroSetArquivo;
		} else if (parametroGetProperty != null) {
			nomeDoSistema = this.caminhoDaPasta + parametroGetProperty;
		} else {
			nomeDoSistema = this.caminhoDaPasta + "DefaultSystem";
		}
		arquivo = nomeDoSistema + ".tak";
		this.caminhoDoArquivo = arquivo;
		return arquivo;
	}

	private File criarArquivoLog() throws IOException {		
		arquivo = new File(this.caminhoDoArquivo);
		System.out.println("1caminhoDoArquivo: " + caminhoDoArquivo);
		System.out.println("1arquivo: " + arquivo.getAbsolutePath());
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo, "rw");
			writer.write("[\n]".getBytes());
			writer.close();
		}		
		return arquivo;
	}
}