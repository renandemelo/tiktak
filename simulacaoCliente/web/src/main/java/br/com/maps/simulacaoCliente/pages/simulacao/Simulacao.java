package br.com.maps.simulacaoCliente.pages.simulacao;

import jmine.tec.web.wicket.pages.Template;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import bancosys.tec.exception.MessageCreator;
import br.org.tiktak.core.TikTak;

public class Simulacao extends Template {
	
	String sistema;
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
		form.add(new TextField<String>("txt_sistema", new PropertyModel<String>(this, "sistema")));
		form.add(new TextField<String>("txt_usuario", new PropertyModel<String>(this, "usuario")));
		form.add(new TextField<String>("txt_funcionalidade", new PropertyModel<String>(this, "funcionalidade")));
		form.add(new Button("ok") {
			@Override
			public void onSubmit() {
				TikTak tiktak = new TikTak("Simulacao");
				tiktak.log(usuario, funcionalidade);
				super.onSubmit();
			}
		});
	}
}
