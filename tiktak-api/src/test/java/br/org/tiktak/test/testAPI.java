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
import br.org.tiktak.core.Dados;
import br.org.tiktak.core.GsonFactory;

import com.google.gson.reflect.TypeToken;

public class testAPI {

	@Test
	public void testPrimeiroEvento() throws FileNotFoundException {		
		
		String usuario = "Claynon";
		String funcionalidade = "teste";
		
		File file = new File("../tiktak/tik.tak");
		if (file.exists())
			file.delete();
		Date antes = new Date(System.currentTimeMillis()-1);
		Api.registrarEvento(usuario, funcionalidade);
		Api.registrarEvento(usuario, funcionalidade);
		Date depois = new Date(System.currentTimeMillis()+1);
		FileReader reader = new FileReader(file);
		List<Dados> lista = GsonFactory.getGson().fromJson(reader,new TypeToken<List<Dados>>() {}.getType());
		Dados ultimo = lista.get(lista.size() - 1);
		
		assertEquals(usuario,ultimo.getUsuario());
		assertEquals(funcionalidade,ultimo.getEvento());
		assertNotNull(ultimo.getUuid());		
		assertTrue(antes.before(ultimo.getTime()));
		assertTrue(depois.after(ultimo.getTime()));
	}

}
