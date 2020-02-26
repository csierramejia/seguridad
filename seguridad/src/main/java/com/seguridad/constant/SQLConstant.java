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
		+ "JOIN USUARIOS U ON(U.ID_USUARIO=P.ID_PERSONA)"
		+ "WHERE U.CLAVE=? "
		+ "AND U.NOMBRE_USUARIO=? "
		+ "AND U.ID_ESTADO=?";

	/** SQL para obtener los items del menu parametrizados en el sistema*/
	public static final String GET_ITEMS_MENU =
			"SELECT "
			  + "REP.ID_RECURSO AS ID_ITEM_PADRE,"
			  + "REP.NOMBRE AS NOMBRE_ITEM_PADRE,"
			  + "REP.DESCRIPCION AS DESC_ITEM_PADRE,"
			  + "RE.ID_RECURSO AS ID_ITEM,"
			  + "RE.NOMBRE AS NOMBRE_ITEM,"
			  + "RE.DESCRIPCION AS DESC_ITEM,"
			  + "RE.URL AS URL_ITEM,"
			  + "ACC.ID_ESTADO AS ESTA_ACCION, "
			  + "ACC.ID_ACCION AS ID_ACCION,"
			  + "ACC.NOMBRE AS NOMBRE_ACCION "
		+ "FROM ROLES_RECURSOS_ACCIONES RRA "
		+ "JOIN ROLES RO ON(RO.ID_ROL=RRA.ID_ROL)"
		+ "JOIN RECURSOS RE ON(RE.ID_RECURSO=RRA.ID_RECURSO)"
		+ "JOIN ACCIONES ACC ON(ACC.ID_ACCION=RRA.ID_ACCION)"
		+ "JOIN RECURSOS REP ON(REP.ID_RECURSO=RE.ID_RECURSO_PADRE)"
		+ "WHERE RO.ID_ROL IN(SELECT URE.ID_ROL FROM USUARIOS_ROLES_EMPRESAS URE WHERE URE.ID_USUARIO=? AND URE.ID_ESTADO=?)"
		+ "AND RE.ID_APLICACION=? "
		+ "AND RO.ID_ESTADO=? "
		+ "AND RRA.ID_ESTADO=? "
		+ "ORDER BY REP.NOMBRE";
}
