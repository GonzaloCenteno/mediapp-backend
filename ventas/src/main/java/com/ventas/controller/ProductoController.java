package com.ventas.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ventas.exception.ModeloNotFoundException;
import com.ventas.model.Producto;
import com.ventas.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> lista = productoService.listar();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> ListarPorId(@PathVariable("id") Integer id) {
		Producto pro = productoService.leerPorId(id);
		if(pro.getIdProducto() == null)
		{
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<Producto>(pro, HttpStatus.OK);
	}
	
	/*@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto Producto) {
		Producto pro = productoService.registrar(Producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto persona, BindingResult result, Model model) {
		if (result.hasErrors()) {
	        List<String> errors = result.getAllErrors().stream()
	          .map(DefaultMessageSourceResolvable::getDefaultMessage)
	          .collect(Collectors.toList());
	        throw new ModeloNotFoundException(errors.toString());
	    }
		Producto productos = productoService.registrar(persona);
		return new ResponseEntity<>(productos, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto Producto) {
		Producto pro = productoService.modificar(Producto);
		return new ResponseEntity<Producto>(pro, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Producto pro = productoService.leerPorId(id);
		if(pro.getIdProducto() == null)
		{
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		productoService.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Producto>> listarProductos(Pageable pageable,@RequestParam String sort, @RequestParam String order) {
		Sort data;
		if(order.equals("1")){
			data = Sort.by(sort).ascending();
		}else {
			data = Sort.by(sort).descending();
		}
		Pageable orden = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), data);
		Page<Producto> productos = productoService.listarProductos(orden);
		return new ResponseEntity<Page<Producto>>(productos,HttpStatus.OK);
	}

}
