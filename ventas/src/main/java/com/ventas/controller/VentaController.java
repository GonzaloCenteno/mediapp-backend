package com.ventas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.model.Venta;
import com.ventas.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService ventaService;
	
	@PostMapping
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta venta) {
		Venta ven = ventaService.registrar(venta);
		return new ResponseEntity<Venta>(ven, HttpStatus.CREATED);
	}

}
