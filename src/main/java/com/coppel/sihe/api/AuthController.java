package com.coppel.sihe.api;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.sihe.api.dto.AuthenticationEmailRequest;
import com.coppel.sihe.api.dto.AuthenticationRequest;
import com.coppel.sihe.api.dto.AuthenticationResetCorreoResponse;
import com.coppel.sihe.api.dto.AuthenticationResponse;
import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.repository.UserRepository;
import com.coppel.sihe.security.JwtService;
import com.coppel.sihe.service.EmailService;
import com.coppel.sihe.service.EmpleadoService;
import com.coppel.sihe.util.Cifrado;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;
import jakarta.mail.MessagingException;



@RestController
@RequestMapping(value = Constants.BASE_PATH + "/" + Constants.MAPPING_EMPLEADOS)
public class AuthController {

    @Autowired
    private UserRepository empServDetaImp;
    
    @Autowired
	private EmpleadoService empleadoService; 

    @Autowired
    private JwtService jwtService;
    
    
    @Autowired
    private EmailService emailService;
    
    @ApiOperation(value="Autenticación de empleado", notes="Permite iniciar sesión usando el No. de empleado y su contrasenia")
    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
        	Log.log("Validando inicio de sesión");
        	String jwt = "";
        	if(request.getTipoLogin() == 1) {
	            UserDetails userDetails = empServDetaImp.findById(request.getIdEmpleado().toString())
	            						.orElseThrow(() ->	new EmpleadoNotFoundException("Empleado no disponible"));
	            if(Cifrado.encriptar(request.getPassword()).equals(userDetails.getPassword())) {
	            	 jwt = jwtService.getToken(userDetails);
	            }
            }else if(request.getTipoLogin() == 2) {
            	UserDetails userDetails = (UserDetails) empServDetaImp.consultaUsuarioByUserName(request.getIdEmpleado().toString())
            								.orElseThrow(() ->	new EmpleadoNotFoundException("Empleado no disponible"));
	            if(Cifrado.encriptar(request.getPassword()).equals(userDetails.getPassword())) {
	            	 jwt = jwtService.getToken(userDetails);
	            }
            }
            if(!jwt.isEmpty())
            	return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
            else
            	return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            	
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch(EmpleadoNotFoundException e) {
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    
    
    
    @ApiOperation(value="Recuperar contrasenia empleado", notes="Permite obtener un enlace para recuperar contraseña, recibe el correo del empleado"
    															+ "asi como el path de la pantalla para reestablecer contraseña")
    @PostMapping(path = "/recuperaConstrasenia")
    public ResponseEntity<?> resetContrasenia(@RequestBody AuthenticationEmailRequest request) {
        try {
        	String jwt = "";
            UserDetails userDetails = empServDetaImp.consultaUsuarioByUserName(request.getCorreo()).
            						orElseThrow(() -> new EmpleadoNotFoundException("Empleado no disponible"));
            jwt = jwtService.getToken(userDetails);
            
            String url= "";
            url+= request.getPath();
            url+=jwt;
            if(!jwt.isEmpty()) {
            	try {
            		 Empleado empAux = empleadoService.consultaEmpleadoByCorreo(request.getCorreo());
            		 
                     Map<String, String> emailParams = new HashMap<String, String>();
                     emailParams.put("urlReset", url);
                     emailParams.put("userName", empAux.getPrimerNombre() + " "+empAux.getApellidoPaterno());
                     emailParams.put("mailUser", empAux.getCorreo());
                     emailParams.put("solicDate", new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime()));
    				emailService.sendEmailTemplate(request.getCorreo(), Constants.EMAIL_RESET_PASSWORD_SUBJECT, Constants.EMAIL_RESET_PASSWORD_TEMPLATE, emailParams);
    			} catch (MessagingException | IOException | EmpleadoNotFoundException e) {
    				e.printStackTrace();
    				return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    			}
            	Log.log("Correo enviado a " + request.getCorreo() + " para recuperar la contraseña.");
            	return new ResponseEntity<>( HttpStatus.OK);
            }else
            	return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);	
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch(EmpleadoNotFoundException e) {
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
    }
    
    @ApiOperation(value="Cambiar contrasenia empleado", notes="Permite cambiar la contrasenia del empleado una vez que se autentico"
    														+ "la existencia del correo y se genero token")
    @PostMapping(path = "/resetConstrasenia")
	public ResponseEntity<AuthenticationResetCorreoResponse> restauraContrasenia(@RequestBody AuthenticationResponse token ) {
    	boolean flag = false;
		String username = "";
		UserDetails userDetails = null;
		if (token != null) {
			try { 
				username = jwtService.getUsernameFromToken(token.getToken());
				if (username != null) {
					userDetails = empServDetaImp.findById(username.toString())
							.orElseThrow(() -> new EmpleadoNotFoundException("No se encontro el usuario"));
	            } 
				if (jwtService.isTokenValid(token.getToken(), userDetails)) {
	                flag= true;
	            }

    		}catch(Exception e) {
    			return  new ResponseEntity<>( HttpStatus.NON_AUTHORITATIVE_INFORMATION);  
    		}
		}
		if(flag) {
		  Empleado auxEmp = empleadoService.consultaEmpleado(Long.parseLong(username));
		  Log.log("Contraseña reinicada para usuario " + username);
		  return  new ResponseEntity<>(new AuthenticationResetCorreoResponse( auxEmp ,token.getToken()), HttpStatus.OK);
		}else {
		  return  new ResponseEntity<>( HttpStatus.NON_AUTHORITATIVE_INFORMATION);  
		}
	}
    
}
