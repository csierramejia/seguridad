package com.seguridad.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.seguridad.constant.Constants;
import com.seguridad.constant.Estado;
import com.seguridad.constant.MessagesBussinesKey;
import com.seguridad.constant.Numero;
import com.seguridad.constant.SQLConstant;
import com.seguridad.dto.autenticacion.AutenticacionMobileDTO;
import com.seguridad.dto.autenticacion.AutenticacionRequestDTO;
import com.seguridad.dto.autenticacion.AutenticacionResponseDTO;
import com.seguridad.dto.autenticacion.UbicacionDTO;
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
@SuppressWarnings("unchecked")
public class AutenticacionService {

	/** Contexto de la persistencia del sistema */
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Servicio que soporta el proceso de negocio para la autenticacion en el
	 * sistema
	 *
	 * @param credenciales DTO que contiene los datos de las credenciales
	 * @return DTO con los datos del response para la autenticacion en el sistema
	 */
	public AutenticacionResponseDTO iniciarSesion(AutenticacionRequestDTO credenciales) throws Exception {
		if (credenciales != null && !Util.isNull(credenciales.getClaveIngreso())
				&& !Util.isNull(credenciales.getUsuarioIngreso())) {

			// se consulta el identificador del usuario que coincida con la clave-usuario
			Query q = this.em.createNativeQuery(SQLConstant.GET_USER_AUTH);
			q.setParameter(Numero.UNO.valueI, credenciales.getUsuarioIngreso());
			List<Object> result = q.getResultList();

			// se verifica que si exista el usuario
			if (result != null && !result.isEmpty()) {
				Object[] data = (Object[]) result.get(Numero.ZERO.valueI);
				if (passwordEncoder.matches(credenciales.getClaveIngreso(),
						Util.getValue(data, Numero.CUATRO.valueI))) {
					Long idUsuario = Long.valueOf(Util.getValue(data, Numero.ZERO.valueI));
					if (idUsuario != null && !idUsuario.equals(Numero.ZERO.valueL)) {

						// se construye el DTO con los datos personales del usuario
						UsuarioDTO usuario = new UsuarioDTO();
						usuario.setIdUsuario(idUsuario);
						usuario.setNombreCompleto(Util.getValue(data, Numero.UNO.valueI));
						usuario.setRoles(Util.getValue(data, Numero.DOS.valueI));
						usuario.setPrimerIngreso(Long.valueOf(Util.getValue(data, Numero.TRES.valueI)));
						usuario.setClave(Util.getValue(data, Numero.CUATRO.valueI));
						usuario.setIdRoles(Util.getValue(data, Numero.CINCO.valueI));

						// se verifica si el usuario tiene ROL administrador
						verificarRolAdministrador(usuario);
						UbicacionDTO programacion = obtenerProgramacionUsuario(idUsuario);
						usuario.setIdOficina(programacion.getIdOficina());
						usuario.setIdPuntoVenta(programacion.getIdPuntoVenta());

						// se construye el response con los datos configurados
						AutenticacionResponseDTO response = new AutenticacionResponseDTO();
						response.setUsuario(usuario);
						return response;
					}
				}
				throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
			}
			throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
		}
		throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
	}

	/**
	 * Servicio para obtener los datos necesarios de bienvenida de la app cuando la
	 * autenticacion es OK
	 *
	 * @param data, parametros necesarios para obtener los datos de bienvenida
	 * @return DTO con los datos configurados para la bienvenida de la app
	 */
	public BienvenidaResponseDTO getDatosBienvenida(BienvenidaRequestDTO data) throws Exception {
		if (data != null && data.getIdUsuario() != null) {

			// se construye el response a retornar
			BienvenidaResponseDTO response = getModulos(data.getIdAplicacion(), data.getIdUsuario());

			// se construye el query para obtener todos los items del menu asociado al user
			Query q = this.em.createNativeQuery(SQLConstant.GET_ITEMS_MENU);
			q.setParameter(Numero.UNO.valueI, data.getIdUsuario());
			q.setParameter(Numero.DOS.valueI, data.getIdAplicacion());
			List<Object[]> result = q.getResultList();

			// se verifica si el usuario tiene roles
			if (result != null && !result.isEmpty()) {

				// se recorre cada item a configurar en el response
				String idItemPadre;
				String idItemHijo;
				MenuItemDTO itemPadre;
				MenuItemDTO itemHijo;
				String estadoAccion;
				MenuItemAccionDTO accion;
				for (Object[] item : result) {

					// se obtiene el identificador del item padre y el hijo
					idItemPadre = Util.getValue(item, Numero.ZERO.valueI);
					idItemHijo = Util.getValue(item, Numero.TRES.valueI);
					itemHijo = null;

					// se procede a buscar recursivamente los datos del item padre
					itemPadre = findItemPrincipal(response.getItemsMenu(), idItemPadre);
					if (itemPadre != null) {
						itemHijo = itemPadre.getItemMenu(idItemHijo);
						if (itemHijo == null) {
							itemHijo = new MenuItemDTO();
							itemHijo.setId(idItemHijo);
							itemHijo.setLabel(Util.getValue(item, Numero.CUATRO.valueI));
							itemHijo.setTitle(Util.getValue(item, Numero.CINCO.valueI));
							itemHijo.setRouterLink(Util.getValue(item, Numero.SEIS.valueI));
							itemHijo.setIcon(Util.getValue(item, Numero.DIEZ.valueI));
							itemHijo.setExpanded(true);
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
			}
			return response;
		}
		return null;
	}

	/**
	 * Metodo que permite consultar los modulos con sus submodulos
	 */
	private BienvenidaResponseDTO getModulos(Integer idAplicacion, Long idUsuario) throws Exception {

		// es el response con los datos de los modulos a retornar
		BienvenidaResponseDTO response = new BienvenidaResponseDTO();
		response.setItemsMenu(new ArrayList<>());

		// se procede a consultar los modulos del menu
		Query q = this.em.createNativeQuery(SQLConstant.GET_MODULOS);
		q.setParameter(Numero.UNO.valueI, idUsuario);
		q.setParameter(Numero.DOS.valueI, idAplicacion);
		List<Object[]> result = q.getResultList();
		if (result != null && !result.isEmpty()) {

			// se configura cada modulo
			String idItemPadre;
			MenuItemDTO modulo;
			boolean exist;
			for (Object[] item : result) {
				exist = (boolean) item[Numero.CINCO.valueI];
				if (exist) {
					modulo = new MenuItemDTO();
					modulo.setId(Util.getValue(item, Numero.ZERO.valueI));
					modulo.setLabel(Util.getValue(item, Numero.UNO.valueI));
					modulo.setTitle(Util.getValue(item, Numero.DOS.valueI));
					modulo.setIcon(Util.getValue(item, Numero.TRES.valueI));
					modulo.setExpanded(true);
					idItemPadre = Util.getValue(item, Numero.CUATRO.valueI);
					if (idItemPadre == null) {
						response.agregarItem(modulo);
					} else {
						MenuItemDTO itemModulo = findItemPrincipal(response.getItemsMenu(), idItemPadre);
						if (itemModulo != null) {
							itemModulo.agregarItem(modulo);
						}
					}
				}
			}
		}
		return response;
	}

	/**
	 * Metodo recursivo para obtener el item del menu principal
	 *
	 * @param items, contiene todos los items del menu
	 * @param id,    identificador del item principal a buscar
	 */
	private MenuItemDTO findItemPrincipal(List<MenuItemDTO> items, String id) {
		MenuItemDTO itemPadre = null;
		for (MenuItemDTO item : items) {
			if (item.getItems() != null && !item.getItems().isEmpty()) {
				itemPadre = findItemPrincipal(item.getItems(), id);
				if (itemPadre != null) {
					break;
				}
			}
			if (item.getId().equals(id)) {
				itemPadre = item;
				break;
			}
		}
		return itemPadre;
	}

	public AutenticacionResponseDTO iniciarSesionMobile(AutenticacionMobileDTO credenciales) throws Exception {
		if (credenciales != null && !Util.isNull(credenciales.getCodigoIngreso())
				&& !Util.isNull(credenciales.getNumeroTelefono())) {

			// se consulta el identificador del usuario que coincida con la clave-usuario
			Query q = this.em.createNativeQuery(SQLConstant.GET_USER_AUTH_MOBILE);
			q.setParameter(Numero.UNO.valueI, credenciales.getNumeroTelefono());
			List<Object> result = q.getResultList();

			// se verifica que si exista el usuario
			if (result != null && !result.isEmpty()) {
				Object[] data = (Object[]) result.get(Numero.ZERO.valueI);
				if (passwordEncoder.matches(credenciales.getCodigoIngreso(),
						Util.getValue(data, Numero.TRES.valueI))) {
					Long idUsuario = Long.valueOf(Util.getValue(data, Numero.ZERO.valueI));
					if (idUsuario != null && !idUsuario.equals(Numero.ZERO.valueL)) {

						// se construye el DTO con los datos personales del usuario
						UsuarioDTO usuario = new UsuarioDTO();
						usuario.setIdUsuario(idUsuario);
						usuario.setNombreCompleto(Util.getValue(data, Numero.UNO.valueI));
						usuario.setRoles(null);
						usuario.setPrimerIngreso(Long.valueOf(Util.getValue(data, Numero.DOS.valueI)));
						usuario.setClave(Util.getValue(data, Numero.TRES.valueI));
						usuario.setNumeroTelefono(Util.getValue(data, Numero.CUATRO.valueI));
						usuario.setCorreo(consultarCorreoPorPersona(usuario.getIdUsuario()));
						// se construye el response con los datos configurados
						UbicacionDTO programacion = obtenerProgramacionUsuario(idUsuario);
						usuario.setIdOficina(programacion.getIdOficina());
						usuario.setIdPuntoVenta(programacion.getIdPuntoVenta());
                		AutenticacionResponseDTO response = new AutenticacionResponseDTO();
						response.setUsuario(usuario);
						return response;
					}
				}
				throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
			}
			throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
		}
		throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);

	}

	private String consultarCorreoPorPersona(Long idUsuario) {
		try {
			String correo=null;
			Query q=em.createNativeQuery(SQLConstant.SELECT_MAIL_ID_PERSON).setParameter("id", idUsuario);
			correo=(String) q.getSingleResult();
			return correo;
			
		}catch (Exception e) {
			return null;
		}
	}

	/**
	 * Metodo que permite verificar si el usuario tiene rol administrador
	 */
	private void verificarRolAdministrador(UsuarioDTO usuario) {
		String idRoles = usuario.getIdRoles();
		if (!Util.isNull(idRoles)) {
			String[] roles = usuario.getIdRoles().split(Constants.COMA);
			for (String idROL : roles) {
				if (Constants.ID_ADMINISTRADOR.equals(idROL)) {
					usuario.setAdministrador(true);
					return;
				}
			}
		}
	}
	
	
	/**
	 * Metodo que permite consultar la ubicacion del usuario
	 */
	private UbicacionDTO obtenerProgramacionUsuario(Long idUsuario) throws Exception {

		UbicacionDTO programacion = new UbicacionDTO();
		try {
		Query q = this.em.createNativeQuery(SQLConstant.GET_PROGRAMACION_VENDEDOR);
		q.setParameter("idUsuario", idUsuario);
		Object[] result = (Object[]) q.getSingleResult();
	     if (result != null) {
				programacion.setIdOficina((Util.getValue(result, Numero.ZERO.valueI) != null ? Long.valueOf(Util.getValue(result, Numero.ZERO.valueI)): 0));
				programacion.setIdPuntoVenta((Util.getValue(result, Numero.UNO.valueI) != null ? Long.valueOf(Util.getValue(result, Numero.UNO.valueI)): 0));
			}
		}catch(NoResultException e) {
			
			throw new BusinessException(MessagesBussinesKey.KEY_PROGRAMACION_NULA.value);

		}
		
		return programacion;
	}

}
