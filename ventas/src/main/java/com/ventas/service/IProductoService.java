package com.ventas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ventas.model.Producto;

public interface IProductoService extends ICRUD<Producto>{

	Page<Producto> listarProductos(Pageable pageable);
	
}
