package com.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventas.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}
