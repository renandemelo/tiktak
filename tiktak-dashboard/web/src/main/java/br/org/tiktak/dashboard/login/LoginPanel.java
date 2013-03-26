package br.org.tiktak.dashboard.login;

import jmine.tec.persist.web.pages.auth.Authorization;

import org.apache.wicket.Page;

import bancosys.tec.security.SecurityService;

public class LoginPanel extends jmine.tec.web.wicket.component.panel.LoginPanel {

	public LoginPanel(String id, String systemName,
			SecurityService securityService) {
		super(id, systemName, securityService);
	}
	
	@Override
	protected Class<? extends Page> getResponsePage() {
		return Authorization.class;
	}

}
