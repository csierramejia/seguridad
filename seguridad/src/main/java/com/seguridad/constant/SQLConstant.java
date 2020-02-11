package com.seguridad.constant;

/**
 * Clase constante que contiene los DMLs Y DDLs para las consultas nativas
 */
public class SQLConstant {

	/** SQL para obtener el usuario a partir de su clave y usuario*/
	public static final String GET_USER_CLAVE = "SELECT ID_USUARIO FROM USUARIOS WHERE CLAVE=? AND NOMBRE_USUARIO=?";
}
