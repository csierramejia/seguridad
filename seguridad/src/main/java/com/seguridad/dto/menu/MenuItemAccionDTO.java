package com.seguridad.dto.menu;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular las acciones que tiene cada item del menu
 */
@Data
public class MenuItemAccionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del accion del item */
	private Long idAccion;
}
