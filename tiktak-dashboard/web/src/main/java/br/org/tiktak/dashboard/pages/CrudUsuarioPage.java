package br.org.tiktak.dashboard.pages;

import java.util.ArrayList;
import java.util.List;

import jmine.tec.report.impl.table.ReportTableBuilder;
import jmine.tec.web.wicket.component.command.img.EditCommand;
import jmine.tec.web.wicket.component.command.img.ImageCommand;
import jmine.tec.web.wicket.component.command.img.ViewCommand;
import jmine.tec.web.wicket.pages.form.CrudModelPage;
import jmine.tec.web.wicket.pages.form.FormType;

import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.org.tiktak.dashboard.HelpMessages;
import br.org.tiktak.dashboard.pages.vo.CrudUsuarioVO;
import bancosys.tec.exception.MessageCreator;
import bancosys.tec.persist.dao.BaseDAOFactory;
import bancosys.tec.security.annotation.Secure;
import bancosys.tec.security.authorization.UrlPermission;
import bancosys.tec.security.impl.dao.UserDAO;
import bancosys.tec.security.impl.domain.User;

/**
 * Tela de pesquisa e listagem de usuários cadastrados no sistema.
 * 
 * @author jrenaut
 */
@Secure(id = "URL_CONTROLE_ACESSO", permissionType = UrlPermission.class)
public class CrudUsuarioPage extends CrudModelPage<CrudUsuarioVO, User> {

    @SpringBean
    private UserDAO dao;

    /**
     * {@inheritDoc}
     */
    public Page createNewPage() {
        return new NovoUsuarioPage(new PageParameters(), this);
    }

    /**
     * {@inheritDoc}
     */
    public List<User> search(BaseDAOFactory daoFactory) {
        return this.dao.findByLikeNaturalKey(this.getModel().getUsername());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Page createFormPage(User entity, FormType formType) {
        return new NovoUsuarioPage(this, new PageParameters(), entity, formType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CrudUsuarioVO createModel() {
        return new CrudUsuarioVO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addResultTableColumns(ReportTableBuilder<User> table) {
        table.addStringColumn("username", "Nome de usuario", "username");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ImageCommand> getTableCommands(User entity) {
        if (entity.getId() == 1L) { // validação simplista para o usuário administrador
            List<ImageCommand> commands = new ArrayList<ImageCommand>();
            commands.add(new ViewCommand(this));
            commands.add(new EditCommand(this));
            return commands;
        }
        return super.getTableCommands(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MessageCreator getHelpTextCreator() {
        return HelpMessages.CONTROLE_ACESSO;
    }

}
