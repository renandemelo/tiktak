package br.org.tiktak.dashboard.pages.tabela;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class PainelDeSistema extends Panel {

	private final String sistema;

	public PainelDeSistema(final String id, final String sistema) {
		super(id);
		this.sistema = sistema;
		this.add(new Label("mensagem", new Model<String>("Conteúdo do " + sistema)));
	}
}
