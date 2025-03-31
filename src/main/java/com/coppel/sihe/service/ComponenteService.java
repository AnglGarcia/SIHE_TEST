package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.entity.Componente;

public interface ComponenteService {

	public ResponseEntity<?> createComponente(Componente request);
	public ResponseEntity<?> actualizacionComponente(Long id, Componente request);
	public ResponseEntity<?> consultaComponente(Long id);
	Page<Componente> consultaAllComponente(Pageable pageable);
	ResponseEntity<?> borrarComponente(Long id);
	public boolean existsByIdComponente(Long idComponente);
	public ResponseEntity<Mensaje> actualizaStatus(Long id, boolean isActivo);
}
