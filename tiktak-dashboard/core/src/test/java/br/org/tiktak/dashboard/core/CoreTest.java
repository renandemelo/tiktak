package br.org.tiktak.dashboard.core;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Test;

import br.org.tiktak.core.Event;

public class CoreTest {

	@Test
	public void testeLerArquivo() throws FileNotFoundException {
		Core core = new Core();
		FileReader arquivo = new FileReader("../../modeloDeArquivo/tik.tak");
		List<Event> listaEventos = core.lerArquivo(arquivo);

		assertEquals("ROBERTO", listaEventos.get(3).getUser());
	}

}
