package com.seguridad.dto.autenticacion;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos de la ubicacion del usuario
 */
@Data
public class UbicacionDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** identificador de la oficina a la que pertenece el usuario */
	private Long idOficina;
	
	/** identificador del punto de venta al que pertenece el usuario */
	private Long idPuntoVenta;

}
