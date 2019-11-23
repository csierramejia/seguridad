package com.seguridad.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * Usuario
 */
@Data
public class UsuarioDTO implements Serializable {

	private Long id;
	private String nombreUsuario;
	private String clave;
	
 }
