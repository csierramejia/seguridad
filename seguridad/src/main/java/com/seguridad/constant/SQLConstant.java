package com.seguridad.constant;

/**
 * Clase constante que contiene los DMLs Y DDLs para las consultas nativas
 */
public class SQLConstant {

	/** SQL para obtener los datos personales del usuario con base a sus credenciales*/
	public static final String GET_USER_AUTH =
		  "SELECT "
			+ "U.ID_USUARIO,"
			+ "P.PRIMER_NOMBRE,"
			+ "P.SEGUNDO_NOMBRE,"
			+ "P.PRIMER_APELLIDO,"
			+ "P.SEGUNDO_APELLIDO "
		+ "FROM PERSONAS P "
		+ "JOIN USUARIOS U ON(U.ID_USUARIO=P.ID_PERSONA)WHERE U.CLAVE=? AND U.NOMBRE_USUARIO=?";
}
