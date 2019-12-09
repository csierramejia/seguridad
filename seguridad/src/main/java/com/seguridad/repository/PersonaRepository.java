package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.Persona;

/**
 * Spring Data repository for the Persona entity.
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
}