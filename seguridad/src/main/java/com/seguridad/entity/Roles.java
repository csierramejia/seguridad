/**
 * 
 */
package com.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

/**
 * @author
 *
 */
@Data
@Entity
@Table(name = "roles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Roles implements Serializable {

	@Id
	private Long idRol;
	private String nombre;
	private String descripcion;
	private String idEstado;

}
