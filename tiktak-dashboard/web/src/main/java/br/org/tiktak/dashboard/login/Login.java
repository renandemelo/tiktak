package br.org.tiktak.dashboard.login;

import org.apache.wicket.markup.html.panel.Panel;

public class Login extends jmine.tec.web.wicket.pages.main.Login {
	@Override
	protected Panel getHeaderPanel(String id) {
		return new LoginPanel(id, getSystemName(), getSecurityService());
	}
}
