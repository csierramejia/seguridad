package com.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguridad.entity.Usuarios;

/**
 * Spring data repository para la entidad de USUARIO
 */
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {}
