package com.coppel.sihe.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import jakarta.mail.MessagingException;

/**
 * Created by Alan Castro/Angel Garcia on 20/10/2023.
 */


public interface EmailService {
	
    public void sendMailMultipart(String toEmail, String subject, String message, File file) throws MessagingException;
       
    public void sendMail(String toEmail, String subject, String message) throws MessagingException;

    public void sendMail(String toEmail, String subject, String message, File file) throws MessagingException;
    
    public void sendEmailTemplate(String toEmail, String subject, String template, Map<String, String> params)throws MessagingException, IOException;
    
    public String emailBody(String template, Map<String, String> params) throws IOException;
    
    public String setEmailBodyVal(final String emailBody, final Map<String, String> parametros);
    	
    
}
