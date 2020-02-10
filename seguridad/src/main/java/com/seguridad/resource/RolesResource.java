package com.seguridad.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.dto.RolDTO;
import com.seguridad.service.RolesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase que contiene todos los recursos para la entidad ROL
 * localhost:puerto/roles/
 */
@RestController
@RequestMapping("/roles")
public class RolesResource {

	/** Service que contiene los procesos de negocio para la entida ROL */
	@Autowired
	private RolesService rolService;

	@GetMapping("/roles")
	@ApiOperation(value = "Buscar Roles", notes = "Operacion para consultar roles")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Roles encontrados"),
			@ApiResponse(code = 404, message = "Roles no encontrados") })
	public List<RolDTO> findAll() {
		return rolService.findAll();
	}

	@GetMapping("/roles/{id}")
	@ApiOperation(value = "Buscar Rol", notes = "Operacion para consultar rol por Id")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Rol encontrado"),
			@ApiResponse(code = 404, message = "Rol no encontrado") })
	public RolDTO findById(@PathVariable Long id) {
		return rolService.findById(id);
	}
}
