package com.coppel.sihe.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class Cifrado{
	
	private static String clave;
	
	public static String getClave() {
		return clave;
	}

	public static void setClave(String clave) {
		Cifrado.clave = clave;
	}

	private static SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");
        return secretKey;
    }
	
	public static String encriptar(String datos) {
        try {
			SecretKeySpec secretKey = crearClave(clave);
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");        
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	
	        byte[] datosEncriptar = datos.getBytes("UTF-8");
	        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
	        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);
	        return encriptado;
        }catch(UnsupportedEncodingException | NoSuchAlgorithmException
        		| InvalidKeyException | NoSuchPaddingException 
        		| IllegalBlockSizeException | BadPaddingException e) {
        	Log.log(e.getMessage());
        	return "";
        }
    }
	
	public static String desencriptar(String datosEncriptados) {
        try {
			SecretKeySpec secretKey = crearClave(clave);
	
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        
	        byte[] bytesEncriptados = Base64.getDecoder().decode(datosEncriptados);
	        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
	        String datos = new String(datosDesencriptados);
	        return datos;
        }catch(UnsupportedEncodingException | NoSuchAlgorithmException
        	   | InvalidKeyException | NoSuchPaddingException 
        	   | IllegalBlockSizeException | BadPaddingException e) {
        	Log.log(e.getMessage());
        	return "";
        }
    }
}