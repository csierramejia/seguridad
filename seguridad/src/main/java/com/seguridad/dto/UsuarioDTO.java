package com.seguridad.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO que se utiliza para configurar los atributos del USUARIO
 */
@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del Usuario */
	private Long idUsuario;

	/** Nombre completo del usuario */
	private String nombreUsuario;

	/** Clave de ingreso del usuario */
	private String clave;

	/** Estado en la que se encuentra el USUARIO */
	private String idEstado;
}
