package com.coppel.sihe.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Ponderacion;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.PonderacionRepository;
import com.coppel.sihe.service.PonderacionService;

@Service
public class PonderacionServiceImpl implements PonderacionService{

	@Autowired
	PonderacionRepository ponderacionRepository;
	
	@Override
	public Ponderacion createPonderacion(Ponderacion req) {	
		Ponderacion mapperPonderacion = new ModelMapper().map(req, Ponderacion.class);
		return ponderacionRepository.save(mapperPonderacion);
	}

	@Override
	public Ponderacion actualizacionPonderacion(Long id, Ponderacion request) {
		Ponderacion ponderacionEntity = ponderacionRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Ponderacion", "id", id));
		ponderacionEntity.setIdPonderacion(ponderacionEntity.getIdPonderacion());
		ponderacionEntity.setDescripcion(request.getDescripcion());
		Ponderacion updatedPonderacion= ponderacionRepository.save(ponderacionEntity);
		return updatedPonderacion;
	}

	@Override
	public Ponderacion consultaPonderacion(Long id) {
		Ponderacion ponderacionEntity = ponderacionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ponderacion", "id", id));
		return ponderacionEntity;
	}

	@Override
	public Page<Ponderacion> consultaAllPonderacion(Pageable pageable) {
		Page<Ponderacion> listPonderacionEntity = ponderacionRepository.findAll(pageable);
		if(listPonderacionEntity.isEmpty()) {
			new ResourceNotFoundException("Ponderacion", "id", 0);
		}
		return listPonderacionEntity;
	}

	@Override
	public ResponseEntity<?> borrarPonderacion(Long id) {
		Ponderacion ponderacionEntity = ponderacionRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Ponderacion", "id", id));
		ponderacionRepository.delete(ponderacionEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public boolean existsByIdPonderacion(Long idPonderacion) {
		return ponderacionRepository.existsLenguajeByIdPonderacion(idPonderacion);
	}
	
	public void actualizaStatusPonderacion(Long id, boolean isActivo) {
		ponderacionRepository.actualizaIsActivo(id, isActivo);
	}

}
