package com.seguridad.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.constant.AplicacionConstant;
import com.seguridad.constant.Estado;
import com.seguridad.constant.MessagesBussinesKey;
import com.seguridad.constant.Numero;
import com.seguridad.constant.SQLConstant;
import com.seguridad.dto.autenticacion.AutenticacionRequestDTO;
import com.seguridad.dto.autenticacion.AutenticacionResponseDTO;
import com.seguridad.dto.autenticacion.UsuarioDTO;
import com.seguridad.dto.bienvenida.BienvenidaRequestDTO;
import com.seguridad.dto.bienvenida.BienvenidaResponseDTO;
import com.seguridad.dto.menu.MenuItemAccionDTO;
import com.seguridad.dto.menu.MenuItemDTO;
import com.seguridad.util.BusinessException;
import com.seguridad.util.Util;

/**
 * Service para que contiene los procesos de negocio para la autenticacion
 */
@Service
@Transactional(readOnly = true)
@SuppressWarnings("unchecked")
public class AutenticacionService {

	/** Contexto de la persistencia del sistema */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Servicio que soporta el proceso de negocio para la autenticacion en el sistema
	 *
	 * @param credenciales DTO que contiene los datos de las credenciales
	 * @return DTO con los datos del response para la autenticacion en el sistema
	 */
	public AutenticacionResponseDTO iniciarSesion(AutenticacionRequestDTO credenciales) throws Exception {
		if (credenciales != null &&
			!Util.isNull(credenciales.getClaveIngreso()) &&
			!Util.isNull(credenciales.getUsuarioIngreso())) {

			// se consulta el identificador del usuario que coincida con la clave-usuario
			Query q = this.em.createNativeQuery(SQLConstant.GET_USER_AUTH);
			q.setParameter(Numero.UNO.valueI, credenciales.getClaveIngreso());
			q.setParameter(Numero.DOS.valueI, credenciales.getUsuarioIngreso());
			q.setParameter(Numero.TRES.valueI, Estado.ACTIVO);
			List<Object> result = q.getResultList();

			// se verifica que si exista el usuario
			if (result != null && !result.isEmpty()) {
				Object[] data = (Object[]) result.get(Numero.ZERO.valueI);
				Long idUsuario = Long.valueOf(Util.getValue(data, Numero.ZERO.valueI));
				if (!idUsuario.equals(Numero.ZERO.valueL)) {

					// se construye el DTO con los datos personales del usuario
					UsuarioDTO usuario = new UsuarioDTO();
					usuario.setIdUsuario(idUsuario);
					usuario.setPrimerNombre(Util.getValue(data, Numero.UNO.valueI));
					usuario.setSegundoNombre(Util.getValue(data, Numero.DOS.valueI));
					usuario.setPrimerApellido(Util.getValue(data, Numero.TRES.valueI));
					usuario.setSegundoApellido(Util.getValue(data, Numero.CUATRO.valueI));

					// se construye el response con los datos configurados
					AutenticacionResponseDTO response = new AutenticacionResponseDTO();
					response.setUsuario(usuario);
					return response;
				}
			}
			throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
		}
		throw new BusinessException(MessagesBussinesKey.KEY_CREDENCIALES_INCORRECTOS.value);
	}

	/**
	 * Servicio para obtener los datos necesarios de bienvenida de la app
	 * cuando la autenticacion es OK
	 *
	 * @param data, parametros necesarios para obtener los datos de bienvenida
	 * @return DTO con los datos configurados para la bienvenida de la app
	 */
	public BienvenidaResponseDTO getDatosBienvenida(BienvenidaRequestDTO data) throws Exception {
		if (data != null && data.getIdUsuario() != null) {

			// se construye el response a retornar
			BienvenidaResponseDTO response = new BienvenidaResponseDTO();
			response.setItemsMenu(new ArrayList<>());

			// se construye el query para obtener todos los items del menu asociado al user
			Query q = this.em.createNativeQuery(SQLConstant.GET_ITEMS_MENU);
			q.setParameter(Numero.UNO.valueI, data.getIdUsuario());
			q.setParameter(Numero.DOS.valueI, Estado.ACTIVO);
			q.setParameter(Numero.TRES.valueI, AplicacionConstant.ID_APLICACION_BACKOFFICE);
			q.setParameter(Numero.CUATRO.valueI, Estado.ACTIVO);
			q.setParameter(Numero.CINCO.valueI, Estado.ACTIVO);
			List<Object[]> result = q.getResultList();

			// se verifica si el usuario tiene roles
			if (result != null && !result.isEmpty()) {

				// se recorre cada item a configurar en el response
				Long idItemPadre;
				Long idItemHijo;
				MenuItemDTO itemPadre;
				MenuItemDTO itemHijo;
				String estadoAccion;
				MenuItemAccionDTO accion;
				for (Object[] item : result) {

					// se obtiene el identificador del item padre y el hijo
					idItemPadre = Long.valueOf(Util.getValue(item, Numero.ZERO.valueI));
					idItemHijo = Long.valueOf(Util.getValue(item, Numero.TRES.valueI));
					itemHijo = null;

					// se procede a buscar recursivamente los datos del item padre
					itemPadre = findItemPrincipal(response.getItemsMenu(), idItemPadre);

					// si no existe el item padre se debe crear
					if (itemPadre == null) {
						itemPadre = new MenuItemDTO();
						itemPadre.setIdItem(idItemPadre);
						itemPadre.setLabel(Util.getValue(item, Numero.UNO.valueI));
						itemPadre.setDescripcion(Util.getValue(item, Numero.DOS.valueI));

						// se agrega el item padre al response
						response.agregarItem(itemPadre);
					} else {
						// si existe el item padre se debe validar si ya tiene el hijo
						itemHijo = itemPadre.getItemMenu(idItemHijo);
					}

					// se construye los datos del item hijo si no existe
					if (itemHijo == null) {
						itemHijo = new MenuItemDTO();
						itemHijo.setIdItem(Long.valueOf(idItemHijo));
						itemHijo.setLabel(Util.getValue(item, Numero.CUATRO.valueI));
						itemHijo.setDescripcion(Util.getValue(item, Numero.CINCO.valueI));
						itemHijo.setUrl(Util.getValue(item, Numero.SEIS.valueI));
						itemPadre.agregarItem(itemHijo);
					}

					// se obtiene el estado del accion esto para validar si se debe agregar
					estadoAccion = Util.getValue(item, Numero.SIETE.valueI);
					if (Estado.ACTIVO.equals(estadoAccion)) {
						accion = new MenuItemAccionDTO();
						accion.setIdAccion(Long.valueOf(Util.getValue(item, Numero.OCHO.valueI)));
						accion.setNombre(Util.getValue(item, Numero.NUEVE.valueI));
						itemHijo.agregarAccion(accion);
					}
				}
			}
			return response;
		}
		return null;
	}

	/**
	 * Metodo recursivo para obtener el item del menu principal
	 *
	 * @param items, contiene todos los items del menu
	 * @param id, identificador del item principal a buscar
	 */
	private MenuItemDTO findItemPrincipal(List<MenuItemDTO> items, Long id) {
		MenuItemDTO itemPadre = null;
		for (MenuItemDTO item : items) {
			if (item.getItems() != null && !item.getItems().isEmpty()) {
				itemPadre = findItemPrincipal(item.getItems(), id);
				if (itemPadre != null) {
					break;
				}
			}
			if (item.getIdItem().equals(id)) {
				itemPadre = item;
				break;
			}
		}
		return itemPadre;
	}
}
