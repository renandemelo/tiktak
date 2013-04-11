package br.org.tiktak.dashboard.core;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import br.org.tiktak.core.Evento;
import br.org.tiktak.core.GsonFactory;

import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author roberto.rodrigues
 * 
 */
public class Core {
	private String funcionalidade;

	private Integer quantidadeUsadaDaFuncionalidade;

	private final HashMap<String, Integer> funcionalidadePorQuantidade = new HashMap<String, Integer>();

	public String getFuncionaidade() {
		return this.funcionalidade;
	}

	public List<Evento> lerArquivo(final FileReader arquivo) {
		List<Evento> lista = GsonFactory.getGson().fromJson(arquivo, new TypeToken<List<Evento>>() {
		}.getType());

		return lista;
	}

}
