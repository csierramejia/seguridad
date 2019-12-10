package com.seguridad.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.dto.PersonaDTO;
import com.seguridad.entity.Persona;
import com.seguridad.service.PersonaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/persona")
public class PersonaResource {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/personas")	
	@ApiOperation(value = "Buscar Personas", notes = "Operacion para consultar personas")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Personas encontradas"),
			@ApiResponse(code = 404, message = "Personas no encontradas") })	
	public ResponseEntity<List<Persona>> findAll() {
		return ResponseEntity.ok(personaService.findAll());
	}

	@GetMapping("/personas/{id}")
	@ApiOperation(value = "Buscar Persona", notes = "Operacion para consultar persona por Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Persona encontrada"),
			@ApiResponse(code = 404, message = "Persona no encontrada") })	
	public ResponseEntity<Optional<Persona>> findById(@PathVariable Long id) {
		return ResponseEntity.ok(personaService.findById(id));
	} 

    @PostMapping("/personas")
	@ApiOperation(value="Crear persona", notes="Operacion para crear una persona")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Persona creada correctamente"),
			@ApiResponse(code=400, message="Solicitud invalida")})    
    public ResponseEntity<Persona> crear(@RequestBody PersonaDTO personaDTO) throws Exception {
    	Persona persona = new Persona();
    	BeanUtils.copyProperties(personaDTO, persona);
    	//if (persona.getId() != null) {
        //    throw new Exception("Una nueva persona no puede tener Id");
        //}
        return new ResponseEntity<>(this.personaService.save(persona), HttpStatus.CREATED);
    }
    
}
