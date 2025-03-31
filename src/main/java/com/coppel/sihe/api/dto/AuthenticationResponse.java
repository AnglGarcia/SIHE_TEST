package com.coppel.sihe.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse {
	private String token;

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}
	
	

}
