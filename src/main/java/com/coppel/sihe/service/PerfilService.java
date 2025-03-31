package com.coppel.sihe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.Perfil;


public interface PerfilService {

	Perfil createPerfil(Perfil request);
	Perfil actualizacionPerfil(Long id, Perfil request);
	Perfil consultaPerfil(Long id);
	Page<Perfil> consultaAllPerfil(Pageable pageable);
	ResponseEntity<?> borrarPerfil(Long id);
	public boolean existsByIdPerfil(Long idPefil);
	public void actualizaStatusPerfil(Long id, boolean isActivo);
}
