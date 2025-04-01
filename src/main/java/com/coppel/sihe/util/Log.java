package com.coppel.sihe.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Log {
	
	private static String folder_log;

	public static String getFolder_log() {
		return folder_log;
	}

	public static void setFolder_log(String folder_log) {
		Log.folder_log = folder_log;
	}

	public static void log(String mensaje) {
		FileWriter archivo;

		Date dias = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		DateFormat fechaFormat = new SimpleDateFormat("YYYY/MM/dd");
		DateFormat fechaFormat2 = new SimpleDateFormat("YYYY-MM-dd");
		
		try {
			String ruta = getFolder_log() + "SIHE_" + fechaFormat2.format(dias)
					+ ".log";
	
			if (new File(ruta).exists() == false) {
				archivo = new FileWriter(new File(ruta), false);
				
			}
			archivo = new FileWriter(new File(ruta), true);
			archivo.write("[" + fechaFormat.format(dias) + " " + dateFormat.format(dias) + "]" + " " + mensaje + "\r\n");				
			archivo.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
