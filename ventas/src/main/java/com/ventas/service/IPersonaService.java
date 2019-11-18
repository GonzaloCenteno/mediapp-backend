package com.ventas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ventas.model.Persona;

public interface IPersonaService extends ICRUD<Persona>{
	
	Page<Persona> listarPersonas(Pageable pageable);
	
}
