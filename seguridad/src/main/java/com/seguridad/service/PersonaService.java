package com.seguridad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.entity.Persona;
import com.seguridad.repository.PersonaRepository;

@Service
@Transactional(readOnly = true)
public class PersonaService {

	private final PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	public Optional<Persona> findById(Long id) {
		return this.personaRepository.findById(id);
	}

	public List<Persona> findAll() {
		return this.personaRepository.findAll();
	}
	
	@Transactional
	public Persona save(Persona persona) {
		return this.personaRepository.save(persona);
	}
	
}
