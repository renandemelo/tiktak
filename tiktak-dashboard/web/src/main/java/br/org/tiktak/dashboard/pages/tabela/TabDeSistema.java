package br.org.tiktak.dashboard.pages.tabela;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TabDeSistema extends AbstractTab {

	private final String sistema;

	public TabDeSistema(final String sistema) {
		super(new Model<String>(sistema));
		this.sistema = sistema;
	}

	@Override
	public Panel getPanel(final String panelId) {
		return new PainelDeSistema(panelId, sistema);
	}
}