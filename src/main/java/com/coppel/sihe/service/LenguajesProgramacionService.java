package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.LenguajeProgramacion;

public interface LenguajesProgramacionService {

	LenguajeProgramacion createLenguajeProgramacion(LenguajeProgramacion request);
	LenguajeProgramacion actualizacionLenguajeProgramacion(Long id, LenguajeProgramacion request);
	LenguajeProgramacion consultaLenguajeProgramacion(Long id);
	Page<LenguajeProgramacion> consultaAllLenguajeProgramacion(Pageable pageable);
	ResponseEntity<?> borrarLenguajeProgramacion(Long id);
	public boolean existsByIdLenguaje(Long idLenguaje);
	public void actualizaStatusLenguajeProgramacion(Long id, boolean isActivo);
}
