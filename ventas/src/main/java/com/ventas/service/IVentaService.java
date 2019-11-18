package com.ventas.service;

import java.util.List;

import com.ventas.dto.FiltroPersonasDTO;
import com.ventas.model.Venta;

public interface IVentaService extends ICRUD<Venta>{
	
	List<Venta> buscar(FiltroPersonasDTO persona);

}
