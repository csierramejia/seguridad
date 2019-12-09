package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.TipoDocumento;

/**
 * Spring Data repository for the TipoDocumento entity.
 */
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, String> {
	
}