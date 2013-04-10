package br.org.tiktak.dashboard.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.org.tiktak.core.Api;
//import br.org.tiktak.core.Dados;
import br.org.tiktak.core.GsonFactory;

import com.google.gson.reflect.TypeToken;

public class CoreTest {

	@Test
	public void testeLerArquivo() throws FileNotFoundException {		
		Core core = new Core();
		FileReader arquivo = new FileReader("../../modeloDeArquivo/tik.tak");
//		List<Dados> listaDados = core.lerArquivo(arquivo);
		
//		assertEquals("ROBERTO", listaDados.get(3).getUsuario());
	}
	

}
