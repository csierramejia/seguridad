package com.seguridad.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.constant.MessagesBussinesKey;
import com.seguridad.constant.Numero;
import com.seguridad.constant.SQLConstant;
import com.seguridad.dto.autenticacion.AutenticacionRequestDTO;
import com.seguridad.dto.autenticacion.AutenticacionResponseDTO;
import com.seguridad.dto.autenticacion.UsuarioDTO;
import com.seguridad.dto.bienvenida.BienvenidaRequestDTO;
import com.seguridad.dto.bienvenida.BienvenidaResponseDTO;
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
		return null;
	}
}
