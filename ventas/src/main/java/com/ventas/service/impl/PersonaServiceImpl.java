package com.ventas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.model.Persona;
import com.ventas.repository.IPersonaRepository;
import com.ventas.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired
	private IPersonaRepository persona;

	@Override
	public Persona registrar(Persona obj) {
		return persona.save(obj);
	}

	@Override
	public Persona modificar(Persona obj) {
		return persona.save(obj);
	}

	@Override
	public List<Persona> listar() {
		return persona.findAll();
	}

	@Override
	public Persona leerPorId(Integer id) {
		Optional<Persona> op = persona.findById(id);
		return op.isPresent() ? op.get() : new Persona();
	}

	@Override
	public boolean eliminar(Integer id) {
		persona.deleteById(id);
		return true;
	}

}
