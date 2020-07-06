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

	/** Es el nombre completo del usuario */
	private String nombreCompleto;

	/** Son los nombres de los roles asignados al usuario */
	private String roles;
	
	/** contraseña del usuario */
	private String clave;
	
	/** Identifica si es el primer ingreso en el sistema */
	private Long primerIngreso;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long getPrimerIngreso() {
		return primerIngreso;
	}

	public void setPrimerIngreso(Long primerIngreso) {
		this.primerIngreso = primerIngreso;
	}
}
