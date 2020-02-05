package com.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.entity.Roles;
import com.seguridad.repository.RolesRepository;

@Service
@Transactional(readOnly = true)
public class RolesService {

	private RolesRepository rolRepository;
	
	@Autowired
	RolesService(RolesRepository rolRepository){
		this.rolRepository = rolRepository;
	}

	public Optional<Roles> findById(Long id) {
		return this.rolRepository.findById(id);
	}

	public List<Roles> findAll() {
		return this.rolRepository.findAll();
	}
	
	@Transactional
	public Roles save(Roles rol) {
		return this.rolRepository.save(rol);
	}
	
}
