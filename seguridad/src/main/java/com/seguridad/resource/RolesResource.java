package com.seguridad.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.entity.Roles;
import com.seguridad.service.RolesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/roles")
public class RolesResource {
	
	@Autowired
	private RolesService rolService;
	
	@GetMapping("/roles")	
	@ApiOperation(value = "Buscar Roles", notes = "Operacion para consultar roles")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Roles encontrados"),
			@ApiResponse(code = 404, message = "Roles no encontrados") })	
	public ResponseEntity<List<Roles>> findAll() {
		return ResponseEntity.ok(rolService.findAll());
	}

	@GetMapping("/roles/{id}")
	@ApiOperation(value = "Buscar Rol", notes = "Operacion para consultar rol por Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Rol encontrado"),
			@ApiResponse(code = 404, message = "Rol no encontrado") })	
	public ResponseEntity<Optional<Roles>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(rolService.findById(id));
	} 
    
}
