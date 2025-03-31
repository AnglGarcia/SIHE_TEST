package com.coppel.sihe.error;

public class EmpleadoNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoNotFoundException(String msn) {
		super(msn);
	}	
}
