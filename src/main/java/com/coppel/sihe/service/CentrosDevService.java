package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.entity.CentroDevEntity;

public interface CentrosDevService {
	
	public ResponseEntity<?> createCentrosDesarrollo(CentroDevEntity request);
	public ResponseEntity<?> actualizacionCentrosDesarrollo(int id, CentroDevEntity request);
	public ResponseEntity<?> consultaCentroDesarrollo(int id);
	Page<CentroDevEntity> consultaAllCentrosDesarrollo(Pageable pageable);
	ResponseEntity<?> borrarCentrosDev(int id);
	public boolean existsById(int id);
	public ResponseEntity<Mensaje> actualizaStatus(int id, boolean isActivo);

}
