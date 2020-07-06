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
	
	/** Es el identificador de la app logueada */
	private Integer idAplicacion;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Integer idAplicacion) {
		this.idAplicacion = idAplicacion;
	}
}
