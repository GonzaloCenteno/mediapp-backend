package com.ventas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ventas.exception.ModeloNotFoundException;
import com.ventas.model.Persona;
import com.ventas.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> lista = personaService.listar();
		return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> ListarPorId(@PathVariable("id") Integer id) {
		Persona per = personaService.leerPorId(id);
		if(per.getIdPersona() == null)
		{
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<Persona>(per, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Persona persona) {
		Persona per = personaService.registrar(persona);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(per.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona persona) {
		Persona per = personaService.modificar(persona);
		return new ResponseEntity<Persona>(per, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Persona per = personaService.leerPorId(id);
		if(per.getIdPersona() == null)
		{
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		personaService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
