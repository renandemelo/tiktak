package br.org.tiktak.dashboard;

import jmine.tec.web.wicket.component.menu.cfg.AbstractMenuConfigFactoryBean;
import jmine.tec.web.wicket.component.menu.cfg.MenuConfig;
import br.org.tiktak.dashboard.pages.AlterarSenhaPage;
import br.org.tiktak.dashboard.pages.CrudUsuarioPage;
import br.org.tiktak.dashboard.pages.importacao.UploadDeArquivo;
import br.org.tiktak.dashboard.pages.tabela.Tabela;

/**
 * Starting point menu creator
 * 
 * @author takeshi
 */
public class WebApplicationMenuCreator extends AbstractMenuConfigFactoryBean {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MenuConfig createMenuConfig() {
		MenuConfig config = new MenuConfig();

		// add menu here
		// config.addPage(Authorization.class, "Autorização", "Autorizar");
		config.addPage(Tabela.class, "tiktak", "Tabela");
		config.addPage(UploadDeArquivo.class, "tiktak", "Importar Arquivo");
		// config.addPage(ConsultaTrilhaAuditoria.class, "Autorização",
		// "Auditoria");
		// config.addPage(ConsultaDiagnosticador.class, "Infra",
		// "Diagnosticador");
		// config.addPage(ConsultaExceptionRecord.class, "Infra", "Exceptions");
		// config.addPage(Importacao.class, "Infra", "Importacao");
		config.addPage(AlterarSenhaPage.class, "Infra", "Alterar senha");
		config.addPage(CrudUsuarioPage.class, "Infra", "Controle de acesso");

		return config;
	}
}
