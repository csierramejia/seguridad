package com.seguridad.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos que se envía cuando inician sesión ante al sistema
 */
@Data
public class AutenticacionResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del Usuario */
	private Long idUsuario;
}
