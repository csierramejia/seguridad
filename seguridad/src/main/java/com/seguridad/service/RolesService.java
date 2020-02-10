package com.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.builder.Builder;
import com.seguridad.dto.RolDTO;
import com.seguridad.entity.Roles;
import com.seguridad.repository.RolesRepository;

/**
 * Service para que contiene los procesos de negocio para los ROLES
 */
@Service
@Transactional(readOnly = true)
public class RolesService {

	/** Repository que contiene los metodos utilitarios para la persistencia de la entidad ROL */
	@Autowired
	private RolesRepository rolRepository;


	public RolDTO findById(Long id) {
		Builder<Roles, RolDTO> builder = new Builder<Roles, RolDTO>(RolDTO.class);
		Optional<Roles> roles = this.rolRepository.findById(id);
		RolDTO rolDTO = null;
		if (roles.isPresent()) {
			rolDTO = builder.copy(roles.get());
		}
		return rolDTO;
	}

	public List<RolDTO> findAll() {
		List<RolDTO> lstRolesDTO = null;
		Builder<Roles, RolDTO> builder = new Builder<Roles, RolDTO>(RolDTO.class);
		List<Roles> lstRoles = this.rolRepository.findAll();
		if (!lstRoles.isEmpty()) {
			lstRolesDTO = builder.copy(lstRoles);
		}
		return lstRolesDTO;
	}

	@Transactional
	public Roles save(Roles rol) {
		return this.rolRepository.save(rol);
	}
}
