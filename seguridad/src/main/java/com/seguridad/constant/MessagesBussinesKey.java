package com.seguridad.constant;

/**
 * Se debe mandar el codigo del business messages al cliente (angular, movil)
 */
public enum MessagesBussinesKey {

	/** 400 - Credenciales incorrectas. Inténtalo de nuevo. */
	KEY_AUTENTICACION_FALLIDA("security-0001"),
	KEY_PROGRAMACION_NULA("security-0002");
	

	public final String value;
	private MessagesBussinesKey(String value) {
		this.value = value;
	}
}
