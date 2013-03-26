package br.org.tiktak.dashboard.pages;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import bancosys.tec.exception.MessageCreator;
import bancosys.tec.persist.dao.BeanNotFoundException;
import bancosys.tec.security.SecurityService;
import bancosys.tec.security.impl.dao.UserDAO;
import bancosys.tec.security.impl.domain.User;
import bancosys.tec.utils.md5sum.MD5SumTool;
import br.org.tiktak.dashboard.HelpMessages;
import br.org.tiktak.dashboard.pages.validation.AlterarSenhaFormValidator;

/**
 * Tela de alteracao de senha
 * 
 * @author renan.oliveira
 */
public class AlterarSenhaPage extends Template{

    @SpringBean
    private UserDAO dao;
    
    @SpringBean
    private SecurityService securityService;
	
    /**
     * Construtor da pagina
     */
	public AlterarSenhaPage() {
		
		Form form = new Form("changePasswordForm");
		add(form);
		
		final PasswordTextField currentPassword = new PasswordTextField("currentPassword", Model.of(""));
		form.add(currentPassword);
		
		final PasswordTextField password = new PasswordTextField("password", Model.of(""));
		form.add(password);
		final PasswordTextField retypePassword = new PasswordTextField("retypePassword", Model.of(""));
		form.add(retypePassword);

		final Label successMessage = new Label("successMessage","Password changed!");
		successMessage.setVisible(false);
		form.add(successMessage);
		
		Button changePassword = new Button("changePassword") {
	        public void onSubmit() {
	        	User user = getCurrentUser();
	        	user.setPassword(MD5SumTool.md5Sum(password.getInput()));
	        	dao.save(user);
	        	successMessage.setVisible(true);
	        }
	    };
	    form.add(changePassword);
	    form.add(new FeedbackPanel("feedback"));	    
	    form.add(new AlterarSenhaFormValidator(currentPassword, password,retypePassword,getCurrentUser()));	    
	}

    /**
     * Pega o usuario corrente
     */
	private User getCurrentUser() {
		try {
			return dao.findByNaturalKey(securityService.getCurrentUser());
		} catch (BeanNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

    /**
     * help da pagina
     */
	@Override
	protected MessageCreator getHelpTextCreator() {
        return HelpMessages.ALTERAR_SENHA;
	}

}
