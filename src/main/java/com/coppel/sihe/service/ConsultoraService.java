package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.entity.Consultora;

public interface ConsultoraService {

	public ResponseEntity<?>  createConsultora(Consultora request);
	public ResponseEntity<?>  actualizacionConsultora(Long id, Consultora request);
	public ResponseEntity<?>  consultaConsultora(Long id);
	Page<Consultora> consultaAllConsultora(Pageable pageable);
	public boolean existsByIdConsultora(Long idConsultora);
	public ResponseEntity<?> borrarConsultora(Long id);
	public ResponseEntity<Mensaje>  actualizaStatusConsultora(Long id, boolean isActivo);
}
