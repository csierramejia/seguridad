package com.seguridad.dto.menu;

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
	private Long idItem;

	/** Es el label que se visualiza en pantalla */
	private String label;

	/** Es la descripcion que se visualiza en el tooltip del menu */
	private String descripcion;

	/** es el icono a visualizar al lado del item */
	private String icono;

	/** es la url a redireccionar cuando le dan click al item */
	private String url;

	/** son los items de este item o modulo */
	private List<MenuItemDTO> items;

	/** son las acciones que tiene este item */
	private List<MenuItemAccionDTO> acciones;
}
