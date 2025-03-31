package com.coppel.sihe.api;

import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.util.Log;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class SkillsController {

	
	@GetMapping(value = Constants.MAPPING+"/statusapi")
	public String run() {
		Log.log("Servicio operando correctamente.");
		return "Servicio Operando Correctamente "+new String(Character.toChars(0x1F60E));
	}
	
	
}
