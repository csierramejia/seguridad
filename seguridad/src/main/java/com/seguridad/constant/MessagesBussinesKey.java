package com.seguridad.constant;

/**
 * Se debe mandar el codigo del business messages al cliente (angular, movil)
 */
public enum MessagesBussinesKey {

	/** 400 - Credenciales incorrectas. Inténtalo de nuevo. */
	KEY_AUTENTICACION_FALLIDA("security-0001");

	public final String value;
	private MessagesBussinesKey(String value) {
		this.value = value;
	}
}
