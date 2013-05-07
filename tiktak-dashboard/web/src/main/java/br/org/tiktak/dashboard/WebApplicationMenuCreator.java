package br.org.tiktak.dashboard;

import br.org.tiktak.dashboard.pages.AlterarSenhaPage;
import br.org.tiktak.dashboard.pages.CrudUsuarioPage;
import br.org.tiktak.dashboard.pages.importacao.UploadDeArquivo;
import br.org.tiktak.dashboard.pages.tabela.Tabela;
import jmine.tec.datadigester.web.pages.importacao.Importacao;
import jmine.tec.persist.web.pages.audit.ConsultaTrilhaAuditoria;
import jmine.tec.persist.web.pages.auth.Authorization;
import jmine.tec.rtm.impl.web.pages.ConsultaExceptionRecord;
import jmine.tec.rtm.impl.web.pages.diagnosticador.ConsultaDiagnosticador;

import jmine.tec.web.wicket.component.menu.cfg.AbstractMenuConfigFactoryBean;
import jmine.tec.web.wicket.component.menu.cfg.MenuConfig;

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
        config.addPage(Authorization.class, "Autorização", "Autorizar");
        config.addPage(Tabela.class, "Tabela", "Tabela");
        config.addPage(UploadDeArquivo.class, "Upload", "Importar Arquivo");
        config.addPage(ConsultaTrilhaAuditoria.class, "Autorização", "Auditoria");
        config.addPage(ConsultaDiagnosticador.class, "Infra", "Diagnosticador");
        config.addPage(ConsultaExceptionRecord.class, "Infra", "Exceptions");
        config.addPage(Importacao.class, "Infra", "Importacao");
        config.addPage(AlterarSenhaPage.class, "Infra", "Alterar senha");
        config.addPage(CrudUsuarioPage.class, "Infra", "Controle de acesso");
        
        return config;
    }

}
