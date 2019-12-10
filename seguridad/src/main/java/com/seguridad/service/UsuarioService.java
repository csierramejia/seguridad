package com.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.entity.Usuario;
import com.seguridad.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}

	public Optional<Usuario> findById(Long id) {
		return this.usuarioRepository.findById(id);
	}

	public List<Usuario> findAll() {
		return this.usuarioRepository.findAll();
	}
	
	@Transactional
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
}
