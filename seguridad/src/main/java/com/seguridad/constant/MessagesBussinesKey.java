package com.seguridad.constant;

import com.seguridad.util.Numero;

/**
 * Se debe mandar el codigo del business messages al cliente (angular, movil)
 */
public enum MessagesBussinesKey {

	/** 400 - El Usuario y la Contraseña que ingresó no ha sido reconocido.*/
	KEY_AUTENTICACION_FALLIDA(Numero.UNO.valueI.toString());

	public final String value;
	private MessagesBussinesKey(String value) {
		this.value = value;
	}
}
