package com.coppel.sihe.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Perfil;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.PerfilRepository;
import com.coppel.sihe.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{

	@Autowired
	PerfilRepository perfilRepository;
	
	@Override
	public Perfil createPerfil(Perfil req) {	
		Perfil mapperPerfil = new ModelMapper().map(req, Perfil.class);
	return perfilRepository.save(mapperPerfil);
	}

	@Override
	public Perfil actualizacionPerfil(Long id, Perfil request) {
		Perfil perfilEntity = perfilRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Perfil", "id", id));
		perfilEntity.setIdPerfil(perfilEntity.getIdPerfil());
		perfilEntity.setDescripcion(request.getDescripcion());
		perfilEntity.setSu(request.isSu());
		Perfil updatedPerfil= perfilRepository.save(perfilEntity);
		return updatedPerfil;
	}

	@Override
	public Perfil consultaPerfil(Long id) {
		Perfil perfilEntity = perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil", "id", id));
		return perfilEntity;
	}

	@Override
	public Page<Perfil> consultaAllPerfil(Pageable pageable) {
		Page<Perfil> listPerfilEntity = perfilRepository.findAll(pageable);
		if(listPerfilEntity.isEmpty()) {
			new ResourceNotFoundException("Perfil", "id", 0);
		}
		return listPerfilEntity;
	}

	@Override
	public ResponseEntity<?> borrarPerfil(Long id) {
		Perfil perfilEntity = perfilRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Perfil", "id", id));
		perfilRepository.delete(perfilEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public boolean existsByIdPerfil(Long idPerfil) {
		return perfilRepository.existsPerfilByIdPerfil(idPerfil);
	}
	public void actualizaStatusPerfil(Long id, boolean isActivo) {
		perfilRepository.actualizaIsActivo(id, isActivo);
	}
}
