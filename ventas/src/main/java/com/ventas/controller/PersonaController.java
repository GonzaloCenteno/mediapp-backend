package com.ventas.controller;

//import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Object> registrar(@Valid @RequestBody Persona persona, BindingResult result, Model model) {
		if (result.hasErrors()) {
	        List<String> errors = result.getAllErrors().stream()
	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
	          .collect(Collectors.toList());
	        throw new ModeloNotFoundException(errors.toString());
	    }
		Persona per = personaService.registrar(persona);
		return new ResponseEntity<>(per, HttpStatus.CREATED);
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
	
	/*@GetMapping("/pageable") 
	@ResponseStatus(HttpStatus.OK) 
	public Page<Persona> findAllPageable(Pageable pageable,@RequestParam String sort) {  
		Pageable sortedByIdDesc = PageRequest .of (pageable.getPageNumber(), pageable.getPageSize(),Sort.by(sort).descending()); 
		return personaService.listarPersonas(sortedByIdDesc); 
	}*/
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Persona>> listarPersonas(Pageable pageable,@RequestParam String sort, @RequestParam String order) {
		Sort data;
		if(order.equals("1")){
			data = Sort.by(sort).ascending();
		}else {
			data = Sort.by(sort).descending();
		}
		Pageable orden = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), data);
		Page<Persona> personas = personaService.listarPersonas(orden);
		return new ResponseEntity<Page<Persona>>(personas,HttpStatus.OK);
	}

}
