package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.Ponderacion;



public interface PonderacionService {

	Ponderacion createPonderacion(Ponderacion request);
	Ponderacion actualizacionPonderacion(Long id, Ponderacion request);
	Ponderacion consultaPonderacion(Long id);
	Page<Ponderacion> consultaAllPonderacion(Pageable pageable);
	ResponseEntity<?> borrarPonderacion(Long id);
	public boolean existsByIdPonderacion(Long idPonderacion);
	
	public void actualizaStatusPonderacion(Long id, boolean isActivo);
}
