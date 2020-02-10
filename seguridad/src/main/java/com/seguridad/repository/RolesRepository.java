package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.Roles;

/**
 * Spring data repository para la entidad de ROL
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {}