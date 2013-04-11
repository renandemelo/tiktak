package br.org.tiktak.dashboard.pages.tabela;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.Evento;
import br.org.tiktak.dashboard.core.BDfuncionalidades;

public class Tabela extends Template {

	String usuario;
	String funcionalidade;

	List<BDfuncionalidades> tabela = new ArrayList<BDfuncionalidades>();

	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		tabela.add(new BDfuncionalidades("Tela 1", 1, 100));
		for (BDfuncionalidades e : tabela) {
			add(new Label("funcionalidade", e.getFuncionalidade()));
			add(new Label("quantidade", "1"));
			add(new Label("porcentagem", "100%"));
		}
	}
}
