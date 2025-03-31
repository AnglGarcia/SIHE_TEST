package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.Requerimiento;


public interface RequerimientoService {

	Requerimiento createRequerimiento(Requerimiento request);
	Requerimiento actualizacionRequerimiento(Long id, Requerimiento request);
	Requerimiento consultaRequerimiento(Long id);
	Page<Requerimiento> consultaAllRequerimiento(Pageable pageable);
	ResponseEntity<?> borrarRequerimiento(Long id);
	public boolean existsByIdRequerimiento(Long idRequerimiento);
	public void actualizaStatusRequerimiento(Long id, boolean isActivo);
}
