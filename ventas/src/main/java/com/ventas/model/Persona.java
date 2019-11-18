package com.ventas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;
	
	@NotNull(message = "El Campo Nombre no puede estar vacio")
	@Size(min = 3, message = "El Campo Nombre debe tener minimo 3 caracteres")
	@Column(name="nombres", nullable = false, length = 70)
	private String nombres;
	
	@NotNull(message = "El Campo Apellido no puede estar vacio")
	@Size(min = 3, message = "El Campo Apellido debe tener minimo 3 caracteres")
	@Column(name="apellidos", nullable = false, length = 70)
	private String apellidos;
	
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
