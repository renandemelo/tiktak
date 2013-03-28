package br.com.maps.simulacaoCliente.pages.simulacao;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.Api;
import jmine.tec.web.wicket.pages.Template;

public class Simulacao extends Template{

	String usuario;
	String funcionalidade;
	
	@Override
	protected MessageCreator getHelpTextCreator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<Object> form = new Form<Object>("form");
		add(form);
		form.add(new TextField<String>("txt_usuario", new PropertyModel<String>(this, "usuario")));
		form.add(new TextField<String>("txt_funcionalidade", new PropertyModel<String>(this, "funcionalidade")));
		form.add(new Button("ok"){
			@Override
			public void onSubmit() {
				Api.registrarEvento(usuario, funcionalidade);
				super.onSubmit();
			}
		});
		
	}

}
