package com.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

/**
 * Tipo Documento
 */
@Data
@Entity
@Table(name = "tipo_documento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String idTipoDocumento;
    
	private String descripcion;
	private String idEstado;

 }
