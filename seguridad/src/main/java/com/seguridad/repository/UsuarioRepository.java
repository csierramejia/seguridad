package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.Usuario;

/**
 * Spring Data repository for the Usuario entity.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}