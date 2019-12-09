package com.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.entity.TipoDocumento;
import com.seguridad.repository.TipoDocumentoRepository;

@Service
@Transactional(readOnly = true)
public class TipoDocumentoService {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	public Optional<TipoDocumento> findById(String id) {
		return this.tipoDocumentoRepository.findById(id);
	}

	public List<TipoDocumento> findAll() {
		return this.tipoDocumentoRepository.findAll();
	}
	
	@Transactional
	public TipoDocumento save(TipoDocumento tipoDocumento) {
		return this.tipoDocumentoRepository.save(tipoDocumento);
	}
	
}
