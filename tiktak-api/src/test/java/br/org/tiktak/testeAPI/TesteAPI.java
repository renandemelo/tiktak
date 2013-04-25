package br.org.tiktak.testeAPI;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
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
	
	/////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testeVerificarUsuarioPadrao() {
		String conteudoArquivo = resultadoDaChamadaDoAPIPadrao("tik.tak");
		assertTrue(conteudoArquivo.contains(this.usuario));
	}
	
	@Test
	public void testeVerificarEventoPadrao() {
		String conteudoArquivo = resultadoDaChamadaDoAPIPadrao("tik.tak");
		assertTrue(conteudoArquivo.contains(this.evento));
	}

	@Test
	public void testeVerificarUsuarioEventoSetDir() {
		setUsuario();
		setEvento();

		String conteudoArquivo;
		String diretorio = "tiktakdir";
		System.out.println("SetDir: " + diretorio);
		tiktak.setDir(diretorio);
		System.out.println("log: " + diretorio + "tik.tak");
		tiktak.log(this.usuario, this.evento);

		System.out.println("path: " + diretorio + "tik.tak");
		conteudoArquivo = carregarConteudoArquivo(diretorio + "tik.tak");
		assertTrue(conteudoArquivo.contains(this.evento));
	}
}