package com.seguridad.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.constant.MessagesBussinesKey;
import com.seguridad.constant.SQLConstant;
import com.seguridad.dto.AutenticacionRequestDTO;
import com.seguridad.dto.AutenticacionResponseDTO;
import com.seguridad.util.BusinessException;
import com.seguridad.util.Numero;
import com.seguridad.util.Util;

/**
 * Service para que contiene los procesos de negocio para la autenticacion
 */
@Service
@Transactional(readOnly = true)
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
	@SuppressWarnings("unchecked")
	public AutenticacionResponseDTO iniciarSesion(AutenticacionRequestDTO credenciales) throws Exception {
		if (credenciales != null &&
			!Util.isNull(credenciales.getClaveIngreso()) &&
			!Util.isNull(credenciales.getUsuarioIngreso())) {

			// se consulta el identificador del usuario que coincida con la clave-usuario
			Query q = this.em.createNativeQuery(SQLConstant.GET_USER_CLAVE);
			q.setParameter(Numero.UNO.valueI, credenciales.getClaveIngreso());
			q.setParameter(Numero.DOS.valueI, credenciales.getUsuarioIngreso());
			List<Object> result = q.getResultList();

			// se verifica que si exista el usuario
			if (result != null && !result.isEmpty()) {
				Long idUsuario = Long.valueOf(result.get(Numero.ZERO.valueI).toString());
				if (!idUsuario.equals(Numero.ZERO.valueL)) {

					// se construye el response con los datos configurados
					AutenticacionResponseDTO response = new AutenticacionResponseDTO();
					response.setIdUsuario(idUsuario);
					return response;
				}
			}
			throw new BusinessException(MessagesBussinesKey.KEY_AUTENTICACION_FALLIDA.value);
		}
		throw new BusinessException(MessagesBussinesKey.KEY_CREDENCIALES_INCORRECTOS.value);
	}
}
