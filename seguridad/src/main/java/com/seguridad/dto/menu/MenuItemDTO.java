package com.seguridad.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
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

	/**
	 * Metodo que permite agregar un item para este item menu
	 */
	public void agregarItem(MenuItemDTO item) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		this.items.add(item);
	}

	/**
	 * Metodo que permite agregar una accion para este item
	 */
	public void agregarAccion(MenuItemAccionDTO accion) {
		if (this.acciones == null) {
			this.acciones = new ArrayList<>();
		}
		this.acciones.add(accion);
	}

	/**
	 * Metodo que permite buscar un item a partir de su identificador
	 */
	public MenuItemDTO getItemMenu(Long id) {
		MenuItemDTO item = null;
		if (this.items != null && !this.items.isEmpty()) {
			for (MenuItemDTO menuItem: this.items) {
				if (menuItem.getIdItem().equals(id)) {
					item = menuItem;
					break;
				}
			}
		}
		return item;
	}
}
