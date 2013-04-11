package br.org.tiktak.dashboard.pages.tabela;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.dashboard.core.BDfuncionalidades;

public class Tabela extends Template {

	String usuario;
	String funcionalidade;

	List<BDfuncionalidades> funcionalidades = new ArrayList<BDfuncionalidades>();

	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<Void> form = new Form<Void>("form");
		final FileUploadField fileUploadField = new FileUploadField("upload");
		form.add(fileUploadField);
		Button button = new Button("botao"){
			@Override
			public void onSubmit() {
				super.onSubmit();
				System.out.println(fileUploadField.getFileUpload().getClientFileName());
			}
		};
		form.add(button);
		preencheListaFake();
		ListView<BDfuncionalidades> listView = new ListView<BDfuncionalidades>("lvFuncionalidades") {

			@Override
			protected void populateItem(ListItem<BDfuncionalidades> item) {
				item.add(new Label("funcionalidade", item.getModelObject().getFuncionalidade()));				
				item.add(new Label("quantidade", item.getModelObject().getQuantidade() + ""));				
				item.add(new Label("porcentagem", item.getModelObject().getPorcetagem() + "%"));				
			}
			
		};
		
		listView.setList(funcionalidades);
		form.add(listView);
		this.add(form);
	}
	
	private void preencheListaFake(){
		funcionalidades.add(new BDfuncionalidades("Tela 1", 2, 20));
		funcionalidades.add(new BDfuncionalidades("Tela 2", 5, 50));
		funcionalidades.add(new BDfuncionalidades("Tela 3", 3, 30));
	}
	
}
