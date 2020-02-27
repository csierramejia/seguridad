package com.seguridad.dto.autenticacion;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos personales del usuario autenticado
 */
@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del Usuario */
	private Long idUsuario;

	/** Es el primer nombre del usuario */
	private String primerNombre;

	/** Es el segundo nombre del usuario */
	private String segundoNombre;

	/** Es el primer apellido del usuario */
	private String primerApellido;

	/** Es el segundo apellido del usuario */
	private String segundoApellido;
}
