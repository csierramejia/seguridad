package com.seguridad.dto.autenticacion;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * DTO para encapsular los datos de un item del menu
 */
@Data
public class MenuItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** identificador del item */
	private Long id;
	
	/** nombre del item a visualizar en pantalla */
	private String nombre;
	
	/** es el icono a visualizar al lado del item */
	private String icono;
	
	/** es el link a redireccionar cuando le dan click al item */
	private String routerLink;
	
	/** son los items de este item o modulo */
	private List<MenuItemDTO> items;
}
