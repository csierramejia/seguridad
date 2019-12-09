package com.seguridad.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Persona
 */
@Data
public class PersonaDTO implements Serializable {

	private Long idPersona;
	private String idTipoDocumento;
	private String numeroDocumento;
	private String primerNombre; 
	private String segundoNombre; 
	private String primerApellido; 
	private String segundoApellido; 
	private String direccion; 
	private String telefono; 
	private String celular; 
	
 }
