package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

//Class irá ter um método para montar o e-mail
public class EmailLayout {

	// Constante "Constantes são como variáveis, só que com o ponto de que seu valor
	// não pode ser modificado"
	private static final String QUEBRA_DE_LINHA_DUPLA = "<br><br>";
	private static final String QUEBRA_DE_LINHA_UNICA = "<br>";

	// Método que retorna email estático
	public Email montarEmailAdministrador(String destinataria, String assunto) {

		StringBuilder texto = new StringBuilder();

		texto.append("A/C Administrador")// Assunto email
				.append(QUEBRA_DE_LINHA_DUPLA);

		texto.append("Solicito alteração de senha do sistema!")// Corpo email
				.append(QUEBRA_DE_LINHA_DUPLA);

		gerarAssinatura(texto);// Gerar assinatura email

		gerarRodape(texto);// Gerar Rodapé email

		return new Email(destinataria, assunto, texto.toString());
	}

	public Email montarEmailSecretaria(String destinataria, String assunto) {

		StringBuilder texto = new StringBuilder();

		texto.append("A/C Secretário")// Assunto email
				.append(QUEBRA_DE_LINHA_DUPLA);

		texto.append("Texto personalizado para secretário!")// Corpo email
				.append(QUEBRA_DE_LINHA_DUPLA);

		gerarAssinatura(texto);// Gerar assinatura email

		gerarRodape(texto);// Gerar Rodapé email

		return new Email(destinataria, assunto, texto.toString());
	}

	private String gerarAssinatura(StringBuilder texto) {
		return texto.append("Att.:").append(QUEBRA_DE_LINHA_UNICA).append("Operador de Caixa")
				.append(QUEBRA_DE_LINHA_DUPLA).toString();
	}

	// Método privado para gerar rodapé
	private String gerarRodape(StringBuilder texto) {
		return texto.append("E-mail automático. Favor não responder esse e-mail!").toString();
	}

}
