package com.seguridad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.dto.RolDTO;
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

	public RolDTO findById(Long id) {
		Optional<Roles> roles = this.rolRepository.findById(id);
		RolDTO rolDTO = null;
		
		if(roles.isPresent()) {
			rolDTO = new RolDTO();
		    BeanUtils.copyProperties(roles.get(), rolDTO);
		}
		
		return rolDTO;
	}

	public List<RolDTO> findAll() {
		List<Roles> lstRoles = this.rolRepository.findAll();
		List<RolDTO> lstRolesDTO = null;
		
		if(!lstRoles.isEmpty()) {
			lstRolesDTO = new ArrayList<RolDTO>();
			
		    for (Roles rol: lstRoles) {
		    	RolDTO rolDTO = new RolDTO();
		        BeanUtils.copyProperties(rol , rolDTO);
		        lstRolesDTO.add((RolDTO) rolDTO);
		     }		    
		}
		
		return lstRolesDTO;
	}
	
	@Transactional
	public Roles save(Roles rol) {
		return this.rolRepository.save(rol);
	}
	
}
