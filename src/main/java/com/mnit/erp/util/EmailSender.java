package com.mnit.erp.util;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailSender{
    @Value("${app.smtp.host.name}")
    private String SMTP_HOST_NAME; // host name
    @Value("${app.smtp.host.port}")
    private String SMTP_HOST_PORT; //port number
    @Value("${app.smtp.auth.user}")
    private String SMTP_AUTH_USER; //email_id of sender
    @Value("${app.smtp.auth.pwd}")
    private String SMTP_AUTH_PWD; //password of sender email_id
    @Value("${app.smtp.mail.from}")
    private String SMTP_MAIL_FROM; //send from email_id

	public String sentEmailToReceipent(String sRecepentTo,String sRecepentCC,String subject, String sText, String sAttachFilePath){
		String result = "";
		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", SMTP_HOST_PORT);
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.starttls.enable",true); 
			
			Session mailSession = Session.getInstance(props, new Authenticator() {
			    @Override
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
			    }
			});
			mailSession.setDebug(false);
			final Transport transport = mailSession.getTransport("smtp");
			final MimeMessage message = new MimeMessage(mailSession);

			message.setSubject(subject);
			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			// Now set the actual message
			messageBodyPart.setText(sText,"US-ASCII", "html");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();
			// Set text message part
			multipart.addBodyPart(messageBodyPart);
			// Part two is attachment
			//logger.info(sAttachFilePath);
			// checking for attachment attachment
			if(Objects.nonNull(sAttachFilePath) && !sAttachFilePath.isEmpty()){
				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(sAttachFilePath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(new File(sAttachFilePath).getName());
				multipart.addBodyPart(messageBodyPart);
			}
			// Send the complete message parts
			message.setContent(multipart);
			Address[] from = InternetAddress.parse(SMTP_MAIL_FROM);
			message.addFrom(from);
			int iCount = 0;
			boolean isAddressFound = false;
			if(Objects.nonNull(sRecepentTo) && !sRecepentTo.isEmpty()) {
				InternetAddress[] addressTo = new InternetAddress[sRecepentTo.split("\\,").length];
				for(String recipient : sRecepentTo.split("\\,"))
					addressTo[iCount++] = new InternetAddress(recipient);
				message.setRecipients(Message.RecipientType.TO, addressTo);
				isAddressFound = true;
			}

			if(Objects.nonNull(sRecepentCC) && !sRecepentCC.isEmpty()) {
				InternetAddress[] addressCC = new InternetAddress[sRecepentCC.split("\\,").length];
				iCount = 0;
				for(String recipient : sRecepentCC.split("\\,"))
					addressCC[iCount++] = new InternetAddress(recipient);
				message.setRecipients(Message.RecipientType.CC, addressCC);
				isAddressFound = true;
			}
			if(isAddressFound) Transport.send(message);
			result = "email sent";
		} catch (Exception e) {
			log.error("",e);
			result = "email not sent [Error]";
		}
		return result;
	}
	
	public static void main(String[] args){
		sendEmail("nandkishornama@gmail.com", "82300008", "Test User", "AJMER", "9856325698", "I am trying to send an Email from my Java Application to any particular email address. I am using Java Mail API but Unfortunately i am getting SMTPSendFailedException error. Can any body tell me where i am doing a mistake. Here is my code");
	}
	
	public static void sendEmail(String sRecepent, String reqId, String name, String district, String mobile, String issue){
		String sMailHtml = "Body OTP from Funder#"+reqId;
		String subject = "OTP from Funder#"+reqId;
                EmailSender es = new EmailSender();
		String result = es.sentEmailToReceipent(sRecepent, "",subject, sMailHtml,"");
                log.info(result);
	}
}
