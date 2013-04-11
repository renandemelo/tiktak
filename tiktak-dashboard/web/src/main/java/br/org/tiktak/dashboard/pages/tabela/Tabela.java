package br.org.tiktak.dashboard.pages.tabela;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.Evento;

public class Tabela extends Template {

	String usuario;
	String funcionalidade;

	List<Evento> tabela = new ArrayList<Evento>();

	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		tabela.add(new Evento("Astolfo", "Tela 1"));
		for (Evento e : tabela) {
			add(new Label("funcionalidade", e.getFuncionalidade()));
			add(new Label("quantidade", "1"));
			add(new Label("porcentagem", "100%"));
		}
	}
}
