package com.seguridad.dto.bienvenida;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO que contiene los datos del REQUEST de bienvenida
 */
@Data
public class BienvenidaRequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del Usuario autenticado en el sistema */
	private Long idUsuario;
}
