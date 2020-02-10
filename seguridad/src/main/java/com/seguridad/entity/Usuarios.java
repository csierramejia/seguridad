package com.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

/**
 * Entidad que representa la tabla ROLES
 */
@Data
@Entity
@Table(name = "usuarios")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long idUsuario;
	private String nombreUsuario;
	private String clave;
	private String idEstado;
}
