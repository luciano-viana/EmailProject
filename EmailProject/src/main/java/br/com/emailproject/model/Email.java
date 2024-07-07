package br.com.emailproject.model;

//Representa o e-mail "objeto email"
public class Email {

	private String destinatario;
	private String assunto;
	private String texto;

	/*
	 * Construtor com todos os atributos Um construtor em Java é um bloco de código
	 * especial dentro de uma classe, designado para inicializar novos objetos
	 * Caracteriza-se por ter o mesmo nome da classe e por não especificar um tipo
	 * de retorno, nem mesmo void
	 */
	public Email(String destinatario, String assunto, String texto) {
		this.destinatario = destinatario;
		this.assunto = assunto;
		this.texto = texto;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
