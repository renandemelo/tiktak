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
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;

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
		final FileUploadField fileUploadField = new FileUploadField("upload");
		form.add(fileUploadField);
		// FIXME POG para inserir os dados da tabela
		label = new Label("dados");
		label.setEscapeModelStrings(false);
		form.add(label);

		label2 = new Label("dadosTabela");
		label2.setEscapeModelStrings(false);
		form.add(label2);

		carregaArquivo(fileUploadField);

		Button button = new Button("botao") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				carregaArquivo(fileUploadField);
			}

		};
		form.add(button);
		this.add(form);
	}

	private void carregaArquivo(final FileUploadField fileUploadField) {
		FileUpload fileUpload = fileUploadField.getFileUpload();
		if (fileUpload == null) {
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
		// if (fileUpload == null) {
		// warn("é necessário fornecer o arquivo");
		// }
		else {
			try {
				FileReader reader = new FileReader(fileUpload.writeToTempFile());
				processarArquivo(reader);
				fileUpload.writeTo(new File("tik.tak"));
				form.remove(label);
				label = new Label("dados", json);
				label.setEscapeModelStrings(false);
				form.add(label);
				form.remove(label2);
				label2 = new Label("dadosTabela", jsonTabela);
				label2.setEscapeModelStrings(false);
				form.add(label2);
			} catch (IOException e) {
				error("erro ao importar arquivo: " + e.getMessage());
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
