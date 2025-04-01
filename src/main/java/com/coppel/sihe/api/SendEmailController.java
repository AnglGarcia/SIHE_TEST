package com.coppel.sihe.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.service.EmailService;
import com.coppel.sihe.service.EmpleadoService;

/**
 * Created by abburi on 6/18/17.
 */

@RestController
@RequestMapping(value = Constants.BASE_PATH)
public class SendEmailController {

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private EmpleadoService empleadoService;

    
    @GetMapping(path = Constants.MAPPING_EMPLEADOS+"/testSendEmail")
    public void sendEmail(){
        try {
        	@SuppressWarnings("deprecation")
			String msgBody ="Esto es una prueba de correo desde aplicación SIHE :"+Calendar.getInstance().getTime().getMonth();
            emailService.sendMail("luis.tasf@coppel.com", "Prueba de correo ", msgBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    	/*try {
   		 Empleado empAux = empleadoService.consultaEmpleadoByCorreo("martinez@example.com");
   		 
            Map<String, String> emailParams = new HashMap<String, String>();
            emailParams.put("urlReset", "https://www.google.com/");
            emailParams.put("userName", empAux.getPrimerNombre() + " "+empAux.getApellidoPaterno());
            emailParams.put("mailUser", empAux.getCorreo());
            emailParams.put("solicDate", new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            
			emailService.sendEmailTemplate("luis.tasf@coppel.com", Constants.EMAIL_RESET_PASSWORD_SUBJECT, Constants.EMAIL_RESET_PASSWORD_TEMPLATE, emailParams);
    	}catch(EmpleadoNotFoundException | MessagingException | IOException e) {
    		e.printStackTrace();
    	}*/
    }
	
    
	/*
    @GetMapping("/testSendEmail")
    public void sendEmail(){
        try {
        	String msgBody ="Esto es una prueba de correo desde aplicación SIHE :"+Calendar.getInstance().getTime().getMonth();
            emailService.sendMail("alan.tasf@coppel.com", "Prueba de correo ", msgBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    */
    
}
