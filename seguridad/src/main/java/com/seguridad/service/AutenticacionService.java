package com.seguridad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.dto.AutenticacionRequestDTO;
import com.seguridad.dto.AutenticacionResponseDTO;

/**
 * Service para que contiene los procesos de negocio para la autenticacion
 */
@Service
@Transactional(readOnly = true)
public class AutenticacionService {

	/**
	 * Servicio que soporta el proceso de negocio para la autenticacion en el sistema
	 *
	 * @param credenciales DTO que contiene los datos de las credenciales
	 * @return DTO con los datos del response para la autenticacion en el sistema
	 */
	public AutenticacionResponseDTO iniciarSesion(AutenticacionRequestDTO credenciales) throws Exception {
		return null;
	}
}
