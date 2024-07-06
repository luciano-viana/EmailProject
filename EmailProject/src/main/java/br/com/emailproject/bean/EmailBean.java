package br.com.emailproject.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emailproject.dto.EmailLayout;
import br.com.emailproject.model.Email;
import br.com.emailproject.service.EmailService;

//Servidor WidFly prove as anotações abaixo @..
@Named //para dizer que é uma classe de bin
@RequestScoped //Escopo de requesição

//Será o bin da página index.xhtml
public class EmailBean implements Serializable {

	private static final long serialVersionUID = 4538755582654584073L;
	
	private static final String DESTINATARIO = "lucianoviana.dev@gmail.com";
	private static final String ASSUNTO = "Mudança de senha!!";
	
	@Inject
	private EmailService emailService;

	//método para enviar email
	public String enviarEmail() {
		emailService.enviar(montarEmail());
		return null;
	}
	
	private Email montarEmail() {//Enviar email estático
		EmailLayout layout = new EmailLayout();
		return layout.montarEmailAdministrador(DESTINATARIO, ASSUNTO);
	}
}














