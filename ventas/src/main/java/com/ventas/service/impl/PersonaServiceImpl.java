package com.ventas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ventas.model.Persona;
import com.ventas.repository.IPersonaRepository;
import com.ventas.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired
	private IPersonaRepository personaRepository;

	@Override
	public Persona registrar(Persona obj) {
		return personaRepository.save(obj);
	}

	@Override
	public Persona modificar(Persona obj) {
		return personaRepository.save(obj);
	}

	@Override
	public List<Persona> listar() {
		return personaRepository.findAll();
	}

	@Override
	public Persona leerPorId(Integer id) {
		Optional<Persona> op = personaRepository.findById(id);
		return op.isPresent() ? op.get() : new Persona();
	}

	@Override
	public boolean eliminar(Integer id) {
		personaRepository.deleteById(id);
		return true;
	}

	@Override
	public Page<Persona> listarPersonas(Pageable pageable) {
		return personaRepository.findAll(pageable);
	}

}
