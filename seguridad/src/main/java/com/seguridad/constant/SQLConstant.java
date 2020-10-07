package com.seguridad.constant;

/**
 * Clase constante que contiene los DMLs Y DDLs para las consultas nativas
 */
public class SQLConstant {

	/** SQL para obtener los datos personales del usuario con base a sus credenciales*/
	public static final String GET_USER_AUTH =
		"SELECT "
			  + "U.ID_USUARIO,"
			  + "CONCAT(P.PRIMER_NOMBRE,' ',P.SEGUNDO_NOMBRE,' ',P.PRIMER_APELLIDO,' ',P.SEGUNDO_APELLIDO)AS NOMBRE,"
			  + "STRING_AGG(DISTINCT RO.NOMBRE, ', ')AS ROLES,"
			  + "U.PRIMER_INGRESO,"
			  + "U.CLAVE,"
			  + "STRING_AGG(DISTINCT RO.ID_ROL\\:\\:VARCHAR, ',')AS ID_ROLES "
		+ "FROM PERSONAS P "
		+ "JOIN USUARIOS U ON(U.ID_USUARIO=P.ID_PERSONA)"
		+ "JOIN USUARIOS_ROLES_EMPRESAS UR ON(UR.ID_USUARIO=U.ID_USUARIO)"
		+ "JOIN ROLES RO ON(RO.ID_ROL=UR.ID_ROL)"
		+ "WHERE U.NOMBRE_USUARIO=? "
		+ "AND U.ID_ESTADO='" + Estado.ACTIVO
		+ "'AND UR.ID_ESTADO='" + Estado.ACTIVO
		+ "'AND RO.ID_ESTADO='" + Estado.ACTIVO
		+ "'GROUP BY 1,2";

	/** SQL para obtener los datos personales del usuario con base a sus credenciales*/
	public static final String GET_USER_AUTH_MOBILE =
		"SELECT "
			  + "U.ID_USUARIO,"
			  + "CONCAT(P.PRIMER_NOMBRE,' ',P.SEGUNDO_NOMBRE,' ',P.PRIMER_APELLIDO,' ',P.SEGUNDO_APELLIDO)AS NOMBRE,"
			  + " U.PRIMER_INGRESO, U.CLAVE, U.NUMERO_TELEFONO "
		+ "FROM PERSONAS P "
		+ "JOIN USUARIOS U ON(U.ID_USUARIO=P.ID_PERSONA)"
		+ "WHERE U.numero_telefono=? "
		+ "AND U.ID_ESTADO='" + Estado.ACTIVO + "' GROUP BY 1,2";

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
			  + "ACC.NOMBRE AS NOMBRE_ACCION, "
			  + "RE.ICONO AS ICONO_HIJO, "
			  + "REP.ICONO AS ICONO_PADRE "
		+ "FROM ROLES_RECURSOS_ACCIONES RRA "
		+ "JOIN ROLES RO ON(RO.ID_ROL=RRA.ID_ROL)"
		+ "JOIN RECURSOS RE ON(RE.ID_RECURSO=RRA.ID_RECURSO)"
		+ "JOIN ACCIONES ACC ON(ACC.ID_ACCION=RRA.ID_ACCION)"
		+ "JOIN RECURSOS REP ON(REP.ID_RECURSO=RE.ID_RECURSO_PADRE)"
		+ "WHERE RO.ID_ROL IN(SELECT URE.ID_ROL FROM USUARIOS_ROLES_EMPRESAS URE WHERE URE.ID_USUARIO=? AND URE.ID_ESTADO='" + Estado.ACTIVO + "')"
		+ "AND RE.ID_APLICACION=? "
		+ "AND RO.ID_ESTADO='" + Estado.ACTIVO
		+ "'AND RRA.ID_ESTADO='" + Estado.ACTIVO
		+ "'AND RE.ID_ESTADO='"+ Estado.ACTIVO
		+ "'AND REP.ID_ESTADO='"+ Estado.ACTIVO
		+ "'ORDER BY RE.NOMBRE ASC,REP.NOMBRE ASC";

	/** SQL para obtener los modulos del menu*/
	public static final String GET_MODULOS = "SELECT ID_RECURSO,NOMBRE,DESCRIPCION,ICONO,ID_RECURSO_PADRE FROM RECURSOS WHERE ID_ESTADO='" + Estado.ACTIVO + "' AND (ID_RECURSO_PADRE IS NULL OR URL IS NULL) AND ID_APLICACION=? ORDER BY NOMBRE";

	public static final String SELECT_MAIL_ID_PERSON = "select P.correo_electronico from personas p where p.id_persona=:id";
}
