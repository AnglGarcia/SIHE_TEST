package com.coppel.sihe.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationRequest {
	private String idEmpleado;
    private String password;
    private int tipoLogin;
}
