package br.com.emailproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.PreencodedMimeBodyPart;

import br.com.emailproject.model.Email;
import br.com.emailproject.util.LogUtil;

@Stateless
/*
 * Utiliando EJB no projeto Enterprise JavaBeans é um componente da plataforma
 * JEE que roda em um container de um servidor de aplicação. Seu principal
 * objetivo consiste em fornecer um desenvolvimento rápido e simplificado de
 * aplicações Java, com base em componentes distribuídos, transacionais, seguros
 * e portáveis
 */

//Class onde irá ter a regra de negócio 
public class EmailService extends Thread {

	private List<Email> emails;

	// contante
	public static final String HEADER_CONTEXT = "text/html; charset=utf-8";

	public void enviar(Email email) {
		emails = new ArrayList<>();
		emails.add(email);
		send();
	}

	public void enviar(List<Email> emails) {
		this.emails = emails;
		send();
	}

	private EmailService copy() {
		EmailService emailService = new EmailService();
		emailService.emails = emails;
		return emailService;
	}

	private void send() {
		new Thread(this.copy()).start();
	}

	@Override
	public void run() {

		Properties props = new Properties();

		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", System.getProperty("email-project.mail.smtp.host"));
		props.put("mail.smtp.port", System.getProperty("email-project.mail.smtp.port"));

		Session session = Session.getInstance(props);
		session.setDebug(false);

		for (Email email : emails) {

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(System.getProperty("email-project.mail.from")));

				if (email.getDestinatario().contains("/")) {
					List<InternetAddress> emailsLocal = new ArrayList<>();
					for (String e : email.getDestinatario().split("/")) {
						emailsLocal.add(new InternetAddress(e));
					}
					message.addRecipients(Message.RecipientType.TO, emailsLocal.toArray(new InternetAddress[0]));
				} else {
					InternetAddress para = new InternetAddress(email.getDestinatario());
					message.addRecipient(Message.RecipientType.TO, para);
				}

				message.setSubject(email.getAssunto());
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setHeader("Content-Type", HEADER_CONTEXT);
				textPart.setContent(email.getTexto(), HEADER_CONTEXT);
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(textPart);
				message.setContent(mp);
				Transport.send(message);

			} catch (MessagingException e) {
				LogUtil.getLogger(EmailService.class).error("Erro ao enviar e-mail: " + e.getMessage());
			}

		}
	}

}