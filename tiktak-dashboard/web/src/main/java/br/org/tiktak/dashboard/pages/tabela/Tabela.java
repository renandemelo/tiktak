package br.org.tiktak.dashboard.pages.tabela;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.Event;
import br.org.tiktak.core.GsonFactory;
import br.org.tiktak.dashboard.core.BDfuncionalidades;

import com.google.gson.reflect.TypeToken;

public class Tabela extends Template {

	List<BDfuncionalidades> listaFuncionalidades = new ArrayList<BDfuncionalidades>();
	Set<UUID> listaDeIds = new HashSet<UUID>();
	HashMap<String, Integer> mapa = new HashMap<String, Integer>();
	Integer totalDeEventos = 0;
	String json = "";
	String jsonTabela = "";
	Label label;
	Label label2;
	Form<Void> form = new Form<Void>("form");

	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		// FIXME POG para inserir os dados da tabela
		label = new Label("dados");
		label.setEscapeModelStrings(false);
		form.add(label);

		label2 = new Label("dadosTabela");
		label2.setEscapeModelStrings(false);
		form.add(label2);

		carregaArquivo();

		this.add(form);
	}

	private void carregaArquivo() {
		File file = new File("tik.tak");
		if (file.exists()) {
			try {
				processarArquivo(new FileReader(file));
				form.remove(label);
				label = new Label("dados", json);
				label.setEscapeModelStrings(false);
				form.add(label);
				form.remove(label2);
				label2 = new Label("dadosTabela", jsonTabela);
				label2.setEscapeModelStrings(false);
				form.add(label2);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processarArquivo(final FileReader reader) throws IOException {
		List<Event> lista = GsonFactory.getGson().fromJson(reader, new TypeToken<List<Event>>() {
		}.getType());
		for (Event evento : lista) {
			if (!listaDeIds.contains(evento.getUuid())) {
				listaDeIds.add(evento.getUuid());
				totalDeEventos++;
				String funcionalidade = evento.getFuncionalidade();
				int count = mapa.containsKey(funcionalidade) ? mapa.get(funcionalidade) : 0;
				mapa.put(funcionalidade, count + 1);
			}
		}
		listaFuncionalidades.clear();
		Set<String> setFuncionalidades = mapa.keySet();
		boolean naoPrimeiraLinha = false;
		json = "[";
		jsonTabela = "[";
		for (String f : setFuncionalidades) {
			Integer quantidade = mapa.get(f);
			Float porcentagem = 100 * (quantidade.floatValue() / totalDeEventos);
			String porcentagemFormatada = String.format("%.2f", porcentagem);
			BDfuncionalidades bdfuncionalidade = new BDfuncionalidades(f, quantidade, porcentagemFormatada);
			listaFuncionalidades.add(bdfuncionalidade);
			if (naoPrimeiraLinha) {
				this.json += ", ";
				this.jsonTabela += ", ";
			}
			this.json += "['" + f + "', " + quantidade + "]";
			this.jsonTabela += "['" + f + "', '" + quantidade + "', '" + porcentagemFormatada + "%']";
			naoPrimeiraLinha = true;

		}
		this.json += "]";
		this.jsonTabela += "]";
	}
}
