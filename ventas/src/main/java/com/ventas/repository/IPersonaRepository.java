package com.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventas.model.Persona;

public interface IPersonaRepository extends JpaRepository<Persona, Integer>{

}
