package br.org.tiktak.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.org.tiktak.core.Api;
import br.org.tiktak.core.Evento;
import br.org.tiktak.core.GsonFactory;

import com.google.gson.reflect.TypeToken;

public class testAPI {

	@Test
	public void testPrimeiroEvento() throws FileNotFoundException {

		String sistema = "API-Teste";
		String usuario = "Albert e Renato";
		String funcionalidade = "testPrimeiroEvento()";
		Api api = new Api(sistema);

		File file = new File("../tiktak/tik.tak");
		if (file.exists()) {
			file.delete();
		}
		Date antes = new Date(System.currentTimeMillis() - 1);
		api.registrarEvento(usuario, funcionalidade);

		api.registrarEvento(usuario, funcionalidade);
		Date depois = new Date(System.currentTimeMillis() + 1);
		FileReader reader = new FileReader(file);
		List<Evento> lista = GsonFactory.getGson().fromJson(reader, new TypeToken<List<Evento>>() {
		}.getType());
		Evento ultimo = lista.get(lista.size() - 1);

		assertEquals(usuario, ultimo.getUsuario());
		assertEquals(funcionalidade, ultimo.getFuncionalidade());
		assertNotNull(ultimo.getUuid());
		assertTrue(antes.before(ultimo.getTime()));
		assertTrue(depois.after(ultimo.getTime()));
	}

}
