package br.com.maps.simulacaoCliente;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Testa se o conteudo do arquivo coincide com os dados colocados na tela em um determinado evento.
 * obs: supõe que a aplicação simulacaoCliente esteja "em pé".
 * @author leo.oliveira
 *
 */
public class TesteConteudoSimulacaoCliente {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8888";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testeVerificaConteudoDoArquivoNoDiretorioPadrao() throws Exception {
		String usuario = "ALBERT" + System.currentTimeMillis();
		String funcionalidade = "TESTE-API-NOVO" + System.currentTimeMillis();
		driver.get(baseUrl + "/login?1");
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("botaoLogin")).click();
		driver.findElement(By.id("menuLista")).click();
		driver.findElement(By.name("txt_usuario")).clear();
		driver.findElement(By.name("txt_usuario")).sendKeys(usuario);
		driver.findElement(By.name("txt_funcionalidade")).clear();
		driver.findElement(By.name("txt_funcionalidade")).sendKeys(funcionalidade);
		driver.findElement(By.xpath("//input[@value='Enviar']")).click();
		String conteudoArquivo = carrega("tik.tak");
		Boolean resultado = conteudoArquivo.contains(usuario);
		assertTrue(conteudoArquivo.contains(usuario));
		assertTrue(conteudoArquivo.contains(funcionalidade));
	}

	private String carrega(String arquivo) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader( new FileReader (arquivo));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null ) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		return stringBuilder.toString();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}