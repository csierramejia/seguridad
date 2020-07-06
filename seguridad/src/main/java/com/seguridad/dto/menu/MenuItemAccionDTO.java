package com.seguridad.dto.menu;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular las acciones que tiene cada item del menu
 */
@Data
public class MenuItemAccionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** nombre del accion del item */
	private String nombre;

	/** identificador del accion del item */
	private Long idAccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Long idAccion) {
		this.idAccion = idAccion;
	}
}
