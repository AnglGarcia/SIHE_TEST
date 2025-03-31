package com.coppel.sihe.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationEmailRequest {
	private String correo;
	private String path;
}
