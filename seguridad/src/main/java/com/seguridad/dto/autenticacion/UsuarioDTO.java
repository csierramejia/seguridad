package com.seguridad.dto.autenticacion;

import java.io.Serializable;

import lombok.Data;

/**
 * DTO para encapsular los datos personales del usuario autenticado
 */
@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** identificador del Usuario */
	private Long idUsuario;

	/** Es el nombre completo del usuario */
	private String nombreCompleto;

	/** Son los nombres de los roles asignados al usuario */
	private String roles;

	/** Son los identificadores de cada rol que tiene el usuario */
	private String idRoles;

	/** contraseña del usuario */
	private String clave;

	/** Identifica si es el primer ingreso en el sistema */
	private Long primerIngreso;

	/** Nro de telefeno del usaurio autenticado */
	private String numeroTelefono;

	/** correo del usaurio autenticado */
	private String correo;

	/** Indica si el usuario autenticado tiene rol de administrador */
	private boolean administrador;
	
	/** identificador de la oficina a la que pertenece el usuario */
	private Long idOficina;
	
	/** identificador del punto de venta al que pertenece el usuario */
	private Long idPuntoVenta;
	
	/** Almacena la hora final de programación*/ 
	private String horaFinal;

}
