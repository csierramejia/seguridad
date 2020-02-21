package com.seguridad.dto.autenticacion;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos que se envia desde el cliente
 * (angular, app movil) al momento de la autenticacion del sistema
 */
@Data
public class AutenticacionRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** Es la clave de ingreso al sistema */
	private String claveIngreso;

	/** Es el usuario de ingreso al sistema */
	private String usuarioIngreso;
}
