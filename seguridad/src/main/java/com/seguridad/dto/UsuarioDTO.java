package com.seguridad.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * Usuario
 */
@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
	private String nombreUsuario;
	private String clave;
	
 }
