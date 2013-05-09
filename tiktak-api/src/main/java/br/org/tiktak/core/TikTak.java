package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {
	private String caminhoDoDiretorio;
	private String caminhoDoArquivo;
	private String nomeDoSistema;
	private File arquivo;
	private Eventv2 eventov2;

	public TikTak(String sistema){
		caminhoDoDiretorio = "";
		this.nomeDoSistema = sistema;
		this.eventov2 = Eventv2.getInstance();
		this.eventov2.Init(sistema);
		try {
			criarDiretorioLog();
			obterCaminhoDoArquivo();
			criarArquivoLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getCaminhoDoArquivo() {
		return caminhoDoArquivo;
	}
	
	public void setDir(String nomeDoDiretorio) {
		if (!nomeDoDiretorio.endsWith("/"))
			nomeDoDiretorio += "/";
		this.caminhoDoDiretorio = nomeDoDiretorio;
	}
	
	public void log(String usuario, String nomeDoEvento) {
		Event evento = new Event(usuario, nomeDoEvento);
		String json = GsonFactory.getGson().toJson(evento) + "\n";
		concatenarJson(json);
	}
	
	public void logv2(String usuario, String nomeDoEvento) {
		Event evento = new Event(usuario, nomeDoEvento);
		eventov2.getEvent().add(evento);
		String json = GsonFactory.getGson().toJson(eventov2) + "\n";
		System.out.println(json);
		concatenarJson(json);
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
	
	private String obterCaminhoDoDiretorio() throws IOException {
		String diretorio = "";
		String parametroSetDir, parametroGetProperty;
		
		parametroSetDir = this.caminhoDoDiretorio;
		parametroGetProperty = System.getProperty("tiktak.dir");
		if (parametroSetDir != null){
			diretorio = parametroSetDir;
		} else if (parametroGetProperty != null) {
			diretorio = parametroGetProperty;
		}
		return diretorio;
	}

	private String criarDiretorioLog() throws IOException {
		String diretorio = obterCaminhoDoDiretorio();
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
			nomeDoSistema = this.caminhoDoDiretorio + parametroSetArquivo;
		} else if (parametroGetProperty != null) {
			nomeDoSistema = this.caminhoDoDiretorio + parametroGetProperty;
		} else {
			nomeDoSistema = this.caminhoDoDiretorio + "DefaultSystem";
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
	
	private File criarArquivoLogv2() throws IOException {		
		arquivo = new File(this.caminhoDoArquivo);
		System.out.println("1caminhoDoArquivo: " + caminhoDoArquivo);
		System.out.println("1arquivo: " + arquivo.getAbsolutePath());
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			RandomAccessFile writer = new RandomAccessFile(arquivo, "rw");
			writer.write("{\n}".getBytes());
			writer.close();
		}		
		return arquivo;
	}
}