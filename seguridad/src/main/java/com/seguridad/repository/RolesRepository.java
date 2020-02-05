package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.Roles;

/**
 * Spring Data repository for the Rol entity.
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
	
}