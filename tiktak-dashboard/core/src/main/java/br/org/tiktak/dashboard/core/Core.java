package br.org.tiktak.dashboard.core;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import br.org.tiktak.core.Dados;
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
	
	private HashMap<String, Integer> funcionalidadePorQuantidade = new HashMap<String, Integer>();
	
	public String getFuncionaidade(){
		return this.funcionalidade;
	}
	
	public List<Dados> lerArquivo(FileReader arquivo ){
		List<Dados> lista = GsonFactory.getGson().fromJson(arquivo, new TypeToken<List<Dados>>() {}.getType());

		return lista;
	}
	
}
