package br.org.tiktak.tests.testeAPI;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import br.org.tiktak.core.TikTak;

public class TesteAPI {
	private TikTak tiktak;
	private String usuario;
	private String evento;

	@Before
	public void setUp() throws Exception {
		this.tiktak = new TikTak("testes");
	}
	
	public void setUsuario() {
		this.usuario = "ALBERT" + System.currentTimeMillis();
	}
	
	public void setEvento() {
		this.evento = "TESTE-API-NOVO" + System.currentTimeMillis();
	}

	public String resultadoDaChamadaDoAPIPadrao(String arquivo) {
		setUsuario();
		setEvento();

		tiktak.log(this.usuario, this.evento);

		return carregarConteudoArquivo(arquivo);
	}
	
	private String carregarConteudoArquivo(String arquivo) {
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(arquivo));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void excluiArquivoCriadoParaTeste( String nomeArquivo) {
		File arquivo = new File(nomeArquivo);
		arquivo.delete();
	}
	
	private void excluiDiretorioCriadoParaTeste( String nomeDiretorio) {
		File diretorio = new File(nomeDiretorio);
		String nomeDiretorioAbsoluto = diretorio.getAbsolutePath();
		File diretorioAbsoluto= new File(nomeDiretorioAbsoluto);
		for (File arquivo : diretorioAbsoluto.listFiles()) {
			arquivo.delete();
		}
		if (diretorio.isDirectory())
			diretorioAbsoluto.delete();
		
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testeVerificarUsuarioPadrao() {
		String nomeDoArquivo = "tik.tak";
		String conteudoArquivo = resultadoDaChamadaDoAPIPadrao(nomeDoArquivo);
		assertTrue(conteudoArquivo.contains(this.usuario));
		excluiArquivoCriadoParaTeste( nomeDoArquivo);
	}
	
	@Test
	public void testeVerificarEventoPadrao() {
		String nomeDoArquivo = "tik.tak";
		String conteudoArquivo = resultadoDaChamadaDoAPIPadrao(nomeDoArquivo);
		assertTrue(conteudoArquivo.contains(this.evento));
		excluiArquivoCriadoParaTeste( nomeDoArquivo);
	}

	@Test
	public void testeVerificarUsuarioEventoSetDir() {
		setUsuario();
		setEvento();
		String nomeDoArquivo = "tik.tak";
		String conteudoArquivo;		
		String diretorio = "tiktakdir/";	
		
		System.out.println("SetDir: " + diretorio);
		tiktak.setDir(diretorio);		
		System.out.println("log: " + diretorio + nomeDoArquivo);
		tiktak.log(this.usuario, this.evento);
		System.out.println("path: " + diretorio + nomeDoArquivo);
		
		conteudoArquivo = carregarConteudoArquivo(diretorio + nomeDoArquivo);
		assertTrue(conteudoArquivo.contains(this.evento));
		
		excluiArquivoCriadoParaTeste( diretorio + nomeDoArquivo);
		excluiDiretorioCriadoParaTeste(diretorio);		
	}
	
	@Test
	public void testeVerificarArquivov2() {
		setUsuario();
		setEvento();
		String nomeDoArquivo = "Testes.tak";
		String conteudoArquivo;		
		String diretorio = "tiktakdir/";	
		
		System.out.println("SetDir: " + diretorio);
		tiktak.setDir(diretorio);		
		System.out.println("log: " + diretorio + nomeDoArquivo);
		tiktak.log(this.usuario, this.evento);
		System.out.println("path: " + diretorio + nomeDoArquivo);
		
		conteudoArquivo = carregarConteudoArquivo(diretorio + nomeDoArquivo);
		assertTrue(conteudoArquivo.contains("Testes"));
		assertTrue(conteudoArquivo.contains(this.evento));
		
		//excluiArquivoCriadoParaTeste(diretorio + nomeDoArquivo);
		//excluiDiretorioCriadoParaTeste(diretorio);		
	}
}