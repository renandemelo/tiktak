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

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.springframework.web.util.JavaScriptUtils;

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
	Label label;
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
		//FIXME
		label = new Label("dados");
		label.setEscapeModelStrings(false);
		form.add(label);
		
		
		Button button = new Button("botao"){
			@Override
			public void onSubmit() {
				super.onSubmit();
				FileUpload fileUpload = fileUploadField.getFileUpload();
				if(fileUpload == null){
					warn("é necessário fornecer o arquivo");
				}
				else{
					try {
						processarArquivo(fileUpload);
						form.remove(label);
						label = new Label("dados",json);
						label.setEscapeModelStrings(false);
						form.add(label);
					} catch (IOException e) {
						error("erro ao importar arquivo: "+e.getMessage());
					}
				}
			}
		};
		form.add(button);
		ListView<BDfuncionalidades> listView = new ListView<BDfuncionalidades>("lvFuncionalidades") {

			@Override
			protected void populateItem(ListItem<BDfuncionalidades> item) {
				item.add(new Label("funcionalidade", item.getModelObject().getFuncionalidade()));				
				item.add(new Label("quantidade", item.getModelObject().getQuantidade() + ""));				
				item.add(new Label("porcentagem", item.getModelObject().getPorcetagem() + "%"));				
			}
			
		};
		
		listView.setList(listaFuncionalidades);
		form.add(listView);
		this.add(form);
	}
	
	private void processarArquivo(FileUpload file) throws IOException{
		FileReader reader = new FileReader(file.writeToTempFile());
		List<Event> lista = GsonFactory.getGson().fromJson(reader, new TypeToken<List<Event>>() {
		}.getType());
		for (Event evento : lista) {
			if(!listaDeIds.contains(evento.getUuid())) {
				listaDeIds.add(evento.getUuid());
				totalDeEventos++;
				String funcionalidade = evento.getFuncionalidade();
				int count = mapa.containsKey(funcionalidade) ? mapa.get(funcionalidade) : 0;
				mapa.put(funcionalidade, count + 1);
			}
		}
		listaFuncionalidades.clear();
        Set<String> setFuncionalidades = mapa.keySet(); 
        boolean naoPrimeiraLinha = false ;
        json = "[";
        for (String f : setFuncionalidades) {  
        	Integer quantidade = mapa.get(f);
        	Float porcentagem = 100 * (quantidade.floatValue() / totalDeEventos);
        	String porcentagemFormatada = String.format("%.2f", porcentagem);
            BDfuncionalidades bdfuncionalidade = new BDfuncionalidades(f, quantidade, porcentagemFormatada);
            listaFuncionalidades.add(bdfuncionalidade);  
            if(naoPrimeiraLinha) this.json += ", ";
            this.json += "['" + f + "', " + quantidade + "]";
			naoPrimeiraLinha = true;

        }
        this.json += "]";
	}	
}
