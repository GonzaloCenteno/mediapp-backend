package com.ventas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ventas.dto.FiltroPersonasDTO;
import com.ventas.model.Venta;
import com.ventas.repository.IVentaRepository;
import com.ventas.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService{
	
	@Autowired
	private IVentaRepository venta;
	
	@Transactional
	@Override
	public Venta registrar(Venta obj) {
		obj.getDetalleventa().forEach(detalle -> {
			detalle.setVenta(obj);
		});
		return venta.save(obj);
	}

	@Override
	public Venta modificar(Venta obj) {
		return venta.save(obj);
	}

	@Override
	public List<Venta> listar() {
		return venta.findAll();
	}

	@Override
	public Venta leerPorId(Integer id) {
		Optional<Venta> op = venta.findById(id);
		return op.isPresent() ? op.get() : new Venta();
	}

	@Override
	public boolean eliminar(Integer id) {
		venta.deleteById(id);
		return true;
	}

	@Override
	public List<Venta> buscar(FiltroPersonasDTO persona) {
		return venta.buscar(persona.getNombreCompleto());
	}

}
