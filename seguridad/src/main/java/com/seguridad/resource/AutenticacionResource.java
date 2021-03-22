package com.seguridad.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.dto.autenticacion.AutenticacionMobileDTO;
import com.seguridad.dto.autenticacion.AutenticacionRequestDTO;
import com.seguridad.dto.bienvenida.BienvenidaRequestDTO;
import com.seguridad.service.AutenticacionService;
import com.seguridad.util.BusinessException;
import com.seguridad.util.Util;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Servicio que contiene todos los procesos de negocio para la autenticacion
 * localhost:puerto/auth/
 */
@RestController
@RequestMapping("/seguridad")
public class AutenticacionResource {

	/** Service para que contiene los procesos de negocio para la autenticacion */
	@Autowired
	private AutenticacionService autenticacionService;

	/**
	 * Servicio que soporta el proceso de negocio para la autenticacion en el sistema
	 *
	 * @param credenciales DTO que contiene los datos de las credenciales
	 * @return DTO con los datos del response para la autenticacion en el sistema
	 */
	@PostMapping(path = "/login",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Iniciar Sesion", notes = "Operación para iniciar sesión en el sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
			@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> iniciarSesion(@RequestBody AutenticacionRequestDTO credenciales) {
		try {
			return Util.getResponseSuccessful(this.autenticacionService.iniciarSesion(credenciales));
		} catch (BusinessException e) {
			e.printStackTrace();
			return Util.getResponseBadRequest(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Util.getResponseError(AutenticacionResource.class.getSimpleName() + ".iniciarSesion ", e.getMessage());
		}
	}
	
	
	/**
	 * Servicio que soporta el proceso de negocio para la autenticacion en el sistema
	 *
	 * @param credenciales DTO que contiene los datos de las credenciales
	 * @return DTO con los datos del response para la autenticacion en el sistema
	 */
	@PostMapping(path = "/loginMobile",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Iniciar Sesion", notes = "Operación para iniciar sesión en el sistema")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
			@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> iniciarSesionMobile(@RequestBody AutenticacionMobileDTO credenciales) {
		try {
			return Util.getResponseSuccessful(this.autenticacionService.iniciarSesionMobile(credenciales));
		} catch (BusinessException e) {
			return Util.getResponseBadRequest(e.getMessage());
		} catch (Exception e) {
			return Util.getResponseError(AutenticacionResource.class.getSimpleName() + ".loginMobile ", e.getMessage());
		}
	}

	/**
	 * Servicio para obtener los datos necesarios de bienvenida de la app
	 * cuando la autenticacion es OK
	 *
	 * @param data, parametros necesarios para obtener los datos de bienvenida
	 * @return DTO con los datos configurados para la bienvenida de la app
	 */
	@PostMapping(path = "/bienvenida",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Bienvenida", notes = "Operación para obtener los datos necesarios de bienvenida de la app")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Proceso ejecutado satisfactoriamente"),
			@ApiResponse(code = 400, message = "Se presentó una exception de negocio"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> getDatosBienvenida(@RequestBody BienvenidaRequestDTO data) {
		try {
			return Util.getResponseSuccessful(this.autenticacionService.getDatosBienvenida(data));
		} catch (Exception e) {
			return Util.getResponseError(AutenticacionResource.class.getSimpleName() + ".getDatosBienvenida ", e.getMessage());
		}
	}
}
