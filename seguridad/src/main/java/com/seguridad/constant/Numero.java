package com.seguridad.constant;

/**
 * Enums que contiene los valores númericos del sistema
 *
 */
public enum Numero {

	ZERO(0,0l), UNO(1,1l), DOS(2,2l),
	TRES(3,3l), CUATRO(4,4l), CINCO(5,5l),
	SEIS(6,6l), SIETE(7,7l), OCHO(8,8l),
	NUEVE(9,9l), DIEZ(10,10l), ONCE(11,11l),
	DOCE(12,12l), TRECE(13,13l), CATORCE(14,14l),
	QUINCE(15,15l), DIECISEIS(16,16l), DIECISIETE(17,17l),
	DIECIOCHO(18,18l), DIECINUEVE(19,19l), VEINTE(20,20l);

	public final Integer valueI;
	public final Long valueL;

	private Numero(Integer valueI, Long valueL) {
		this.valueI = valueI;
		this.valueL = valueL;
	}
}
