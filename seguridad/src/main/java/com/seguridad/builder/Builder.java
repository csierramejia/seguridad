package com.seguridad.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 
 * @author 
 *
 * @param <S>
 * @param <T>
 */
public class Builder<S, T> {

    private Class<T> classDestino;
    
    public Builder(Class<T> classDestino) {
        this.classDestino = classDestino;
    }
    
    /**
     * 
     * @param origen
     * @return
     */
    public T copy(S origen)  {
    	T destino = null;
    	try {
        
    		if (origen != null) {
    			destino = classDestino.newInstance();
    			BeanUtils.copyProperties(origen, destino);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return destino;
    }
    
    /**
     * 
     * @param lstOrigen
     * @return
     */
    public List<T> copy(List<S> lstOrigen)  {
    	List<T> lstDestino = null;
    	try {
        
    		lstDestino = new ArrayList<>();
	        for (S origen : lstOrigen) {
	        	T destino = classDestino.newInstance();
	            BeanUtils.copyProperties(origen, destino);
	            lstDestino.add(destino);
	        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return lstDestino;
    }
    
}
