package com.seguridad.dto.autenticacion;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos que se envía cuando inician sesión ante al sistema
 */
@Data
public class AutenticacionResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** son los datos del usuario autenticado */
	private UsuarioDTO usuario;

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
