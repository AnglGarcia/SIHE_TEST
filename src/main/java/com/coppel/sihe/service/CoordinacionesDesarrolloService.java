package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.entity.CoordinacionDesarrollo;

public interface CoordinacionesDesarrolloService {

	public ResponseEntity<?> createCoordinacionesDesarrollo(CoordinacionDesarrollo request);
	public ResponseEntity<?> actualizacionCoordinacionesDesarrollo(Long id, CoordinacionDesarrollo request);
	public ResponseEntity<?> consultaCoordinacionDesarrollo(Long id);
	Page<CoordinacionDesarrollo> consultaAllCoordinacionesDesarrollo(Pageable pageable);
	public  ResponseEntity<?> borrarCoordinacionesDesarrollo(Long id);
	public boolean existsById(Long id);
	public ResponseEntity<Mensaje> actualizaStatus(Long id, boolean isActivo);
}
