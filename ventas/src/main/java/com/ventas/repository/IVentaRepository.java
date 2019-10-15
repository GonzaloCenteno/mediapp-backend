package com.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventas.model.Venta;

public interface IVentaRepository extends JpaRepository<Venta, Integer>{

}
