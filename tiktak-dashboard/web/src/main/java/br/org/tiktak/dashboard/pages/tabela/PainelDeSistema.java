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

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.org.tiktak.core.Event;
import br.org.tiktak.core.Eventv2;
import br.org.tiktak.core.GsonFactory;
import br.org.tiktak.dashboard.core.BDfuncionalidades;

import com.google.gson.reflect.TypeToken;

public class PainelDeSistema extends Panel {

	private final String sistema;
	private Label label;
	private Label label2;
	private Form<Void> form = new Form<Void>("form");
	private String json = "";
	private String jsonTabela = "";
	private Set<UUID> listaDeIds = new HashSet<UUID>();
	private Integer totalDeEventos = 0;
	private HashMap<String, Integer> mapa = new HashMap<String, Integer>();
	private List<BDfuncionalidades> listaFuncionalidades = new ArrayList<BDfuncionalidades>();

	public PainelDeSistema(final String id, final String sistema) {
		// TA QUEBRADO!!!
		super(id);
		this.sistema = sistema;
		this.add(new Label("mensagem", new Model<String>("Conteúdo do " + sistema)));
		carregaArquivo(sistema);
		this.add(form);
	}
	
	private void carregaArquivo(String nomeDoSistema) {
		File file = new File("dashboard.bd");
		if (file.exists()) {
			try {
				processarArquivo(new FileReader(file), nomeDoSistema);
				form.remove(label);
				label = new Label("dados", json);
				label.setEscapeModelStrings(false);
				form.add(label);
				form.remove(label2);
				label2 = new Label("dadosTabela", jsonTabela);
				label2.setEscapeModelStrings(false);
				form.add(label2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void processarArquivo(final FileReader reader, String nomeDoSistema) throws IOException {
		List<Eventv2> listaDeEventv2 = GsonFactory.getGson().fromJson(reader, new TypeToken<List<Eventv2>>() {
		}.getType());
		for (Eventv2 eventov2 : listaDeEventv2) {
			if (eventov2.getSystem() == nomeDoSistema) {
				for (Event evento : eventov2.getEvent()) {
					if (!listaDeIds.contains(evento.getUuid())) {
						listaDeIds.add(evento.getUuid());
						totalDeEventos++;
						String funcionalidade = evento.getFuncionalidade();
						int count = mapa.containsKey(funcionalidade) ? mapa.get(funcionalidade) : 0;
						mapa.put(funcionalidade, count + 1);
					}
				}
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


