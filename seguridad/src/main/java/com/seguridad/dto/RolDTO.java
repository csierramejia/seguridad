package com.seguridad.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * DTO que se utiliza para configurar los atributos del ROL
 */
@Data
public class RolDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del ROL */
	private Long idRol;

	/** Nombre del ROL */
	private String nombre;

	/** Descripcion del ROL */
	private String descripcion;

	/** Estado en la que se encuentra el ROL */
	private String idEstado;
}
