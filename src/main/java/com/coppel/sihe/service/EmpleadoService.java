package com.coppel.sihe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.error.EmpleadoNotFoundException;

public interface EmpleadoService {

	Page<Empleado> fetchEmpleadoList(Pageable page);
	
	Empleado fetchEmpleadoByIdAndPassword(Long id, String password) throws EmpleadoNotFoundException;

	Empleado createEmpleado(Empleado request);
	
	Empleado actualizacionEmpleado(Long id, Empleado request);
	
	Empleado consultaEmpleado(Long id);
	
	Empleado consultaEmpleadoByCorreo(String correo) throws EmpleadoNotFoundException;
	
	List<Empleado> consultaAllEmpleado();
	
	ResponseEntity<?> borrarEmpleado(Long id);
	
	public boolean existsById(Long id);
	
	public boolean existsByCorreo(String correo);
	
	List<Empleado> consultaEmpleadoByPerfilAndCentro(Long idPerfil, Long idCentro);
	
	List<Empleado> consultaEmpleadoByPerfil(Long idPerfil);
	
	List<Empleado> consultaEmpleadoByCentro(Long idCentro);
	
	public void actualizaStatus(Long id, boolean isActivo);
}
