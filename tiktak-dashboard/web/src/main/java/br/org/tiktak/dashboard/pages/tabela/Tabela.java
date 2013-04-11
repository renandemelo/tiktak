package br.org.tiktak.dashboard.pages.tabela;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.Api;

public class Tabela extends Template {

	String usuario;
	String funcionalidade;

	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Label("funcionalidade","Tela 1"));
		add(new Label("quantidade","1000"));
		add(new Label("porcentagem","100%"));
	}
}

