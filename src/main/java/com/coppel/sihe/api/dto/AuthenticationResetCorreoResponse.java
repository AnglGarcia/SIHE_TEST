package com.coppel.sihe.api.dto;

import com.coppel.sihe.entity.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResetCorreoResponse {
	private Empleado empleado;
	private String token;

	
	
	
}
