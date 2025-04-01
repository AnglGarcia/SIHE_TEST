package com.coppel.sihe.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class Email implements EmailService{
	
	@Autowired
    private JavaMailSender javaMailSender;

    @Value("${default.folder.path}")
    private String defaultPath;

    public void sendMailMultipart(String toEmail, String subject, String message, File file) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(Constants.FROM_EMAIL_ADDRESS);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(message);

        if(file != null){
            helper.addAttachment(file.getName(), file);
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendMail(String toEmail, String subject, String message) throws MessagingException {
       sendMailMultipart(toEmail, subject, message, null);
    }

    public void sendMail(String toEmail, String subject, String message, File file) throws MessagingException {
        sendMailMultipart(toEmail, subject, message, file);
    }
    
    public void sendEmailTemplate(String toEmail, String subject, String template, Map<String, String> params)throws MessagingException, IOException{
    	MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(Constants.FROM_EMAIL_ADDRESS);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(emailBody(template, params),true);
        javaMailSender.send(mimeMessage);
    }
    
    public String emailBody(String template,  Map<String, String> params) throws IOException{
    	Path path = Paths.get(defaultPath + "email-templates/"+ template+".html");
    	Charset charset = StandardCharsets.UTF_8;
		String content = new String(Files.readAllBytes(path), charset);
		return setEmailBodyVal(content, params);
    }
    
    
    public String setEmailBodyVal(final String emailBody, final Map<String, String> parametros) {
        String body = emailBody;
        if(parametros!=null) {
          for (String key : parametros.keySet()) {
            body = body.replace("${" + key + "}", parametros.get(key));
          }
        }
        return body;
      }
	
}
