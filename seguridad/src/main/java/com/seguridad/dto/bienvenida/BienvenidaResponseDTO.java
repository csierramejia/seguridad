package com.seguridad.dto.bienvenida;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.seguridad.dto.menu.MenuItemDTO;

import lombok.Data;

/**
 * DTO que contiene los datos del RESPONSE de bienvenida
 */
@Data
public class BienvenidaResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** son los items del menu */
	private List<MenuItemDTO> itemsMenu;

	/**
	 * Metodo que permite agregar un item para el menu de la app
	 */
	public void agregarItem(MenuItemDTO item) {
		if (this.itemsMenu == null) {
			this.itemsMenu = new ArrayList<>();
		}
		this.itemsMenu.add(item);
	}
}
