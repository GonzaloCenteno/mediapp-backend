package com.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ventas.model.Venta;

public interface IVentaRepository extends JpaRepository<Venta, Integer>{
	
	@Query("FROM Venta v WHERE LOWER(v.persona.nombres) like %:nombres% or LOWER(v.persona.apellidos) like %:nombres%")
	List<Venta> buscar(@Param("nombres") String nombreCompleto);

}
