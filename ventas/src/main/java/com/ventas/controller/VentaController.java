package com.ventas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.dto.FiltroPersonasDTO;
import com.ventas.exception.ModeloNotFoundException;
import com.ventas.model.Venta;
import com.ventas.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService ventaService;
	
	@PostMapping
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta venta,BindingResult result, Model model) {
		if (result.hasErrors()) {
	        List<String> errors = result.getAllErrors().stream()
	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
	          .collect(Collectors.toList());
	        throw new ModeloNotFoundException(errors.toString());
	    }
		Venta ven = ventaService.registrar(venta);
		return new ResponseEntity<Venta>(ven, HttpStatus.CREATED);
	}
	
	@PostMapping("/buscar")
	public ResponseEntity<List<Venta>> buscar(@RequestBody FiltroPersonasDTO filtro) {
		List<Venta> consultas = new ArrayList<>();
		System.out.println(filtro);
		consultas = ventaService.buscar(filtro);
		return new ResponseEntity<List<Venta>>(consultas, HttpStatus.OK);
	}

}
