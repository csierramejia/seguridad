package com.seguridad.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * Se debe utiliza este builder para configurar los atributos de una entidad a
 * un DTO para ser enviado al cliente (angular o app movil)
 */
public class Builder<S, T> {

	/** Es el .class del DTO a configurar sus atributos */
	private Class<T> classDestino;

	public Builder(Class<T> classDestino) {
		this.classDestino = classDestino;
	}

	/**
	 * Metodo para copiar solo una instancia de un objeto
	 */
	public T copy(S origen) {
		T destino = null;
		try {
			if (origen != null) {
				destino = classDestino.newInstance();
				BeanUtils.copyProperties(origen, destino);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destino;
	}

	/**
	 * Metodo para copiar una lista de objectos
	 */
	public List<T> copy(List<S> lstOrigen) {
		List<T> lstDestino = null;
		try {
			lstDestino = new ArrayList<>();
			for (S origen : lstOrigen) {
				T destino = classDestino.newInstance();
				BeanUtils.copyProperties(origen, destino);
				lstDestino.add(destino);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstDestino;
	}
}
