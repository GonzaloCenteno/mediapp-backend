package com.ventas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ventas.model.Producto;
import com.ventas.repository.IProductoRepository;
import com.ventas.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepository producto;

	@Override
	public Producto registrar(Producto obj) {
		return producto.save(obj);
	}

	@Override
	public Producto modificar(Producto obj) {
		return producto.save(obj);
	}

	@Override
	public List<Producto> listar() {
		return producto.findAll();
	}

	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> op = producto.findById(id);
		return op.isPresent() ? op.get() : new Producto();
	}

	@Override
	public boolean eliminar(Integer id) {
		producto.deleteById(id);
		return true;
	}

	@Override
	public Page<Producto> listarProductos(Pageable pageable) {
		return producto.findAll(pageable);
	}

}
