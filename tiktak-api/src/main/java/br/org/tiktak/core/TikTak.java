package br.org.tiktak.core;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TikTak {
	private String dir = "";
	private String sistema;

	public TikTak(String sistema){
		dir = null;
		this.sistema = sistema;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public void log(String usuario, String evento) {
		Event dados = new Event(usuario, evento);

		String json = GsonFactory.getGson().toJson(dados) + "\n";

		try {
			File arquivo = criarArquivoLog();
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

	private File criarArquivoLog() throws IOException {
		criarPastaLog();
		String caminhoDoArquivo;
		if(this.dir == null){
			caminhoDoArquivo = "tik.tak";
		}else{
		caminhoDoArquivo = this.dir + "tik.tak";
		}
		File arquivo = new File(caminhoDoArquivo);
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