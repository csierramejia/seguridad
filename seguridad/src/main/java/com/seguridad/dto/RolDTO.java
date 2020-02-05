/**
 * 
 */
package com.seguridad.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author
 *
 */
@Data
public class RolDTO implements Serializable {

	private Long idRol;
	private String nombre;
	private String descripcion;
	private String idEstado;

}
