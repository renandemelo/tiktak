package br.org.tiktak.dashboard.login;

import org.apache.wicket.Page;

import bancosys.tec.security.SecurityService;
import br.org.tiktak.dashboard.pages.tabela.Tabela;

public class LoginPanel extends jmine.tec.web.wicket.component.panel.LoginPanel {

	public LoginPanel(String id, String systemName,
			SecurityService securityService) {
		super(id, systemName, securityService);
	}
	
	@Override
	protected Class<? extends Page> getResponsePage() {
		return Tabela.class;
	}

}
