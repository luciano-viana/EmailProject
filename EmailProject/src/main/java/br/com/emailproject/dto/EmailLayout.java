package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

//Class irá ter um método para montar o e-mail
public class EmailLayout {
	
	//Método que retorna email estático
	public Email montarEmailAdministrador(String destinataria, String assunto) {
		
		StringBuilder texto = new StringBuilder();
		
		return new Email(destinataria, assunto, texto.toString());
	}

}
