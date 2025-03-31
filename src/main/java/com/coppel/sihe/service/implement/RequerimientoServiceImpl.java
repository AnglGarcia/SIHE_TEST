package com.coppel.sihe.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Requerimiento;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.RequerimientoRepository;
import com.coppel.sihe.service.RequerimientoService;

@Service
public class RequerimientoServiceImpl implements RequerimientoService {

	@Autowired
	RequerimientoRepository requerimientoRepository;
	
	@Override
	public Requerimiento createRequerimiento(Requerimiento req) {	
		Requerimiento mapperReq = new ModelMapper().map(req, Requerimiento.class);
		return requerimientoRepository.save(mapperReq);
	}

	@Override
	public Requerimiento actualizacionRequerimiento(Long id, Requerimiento request) {
		Requerimiento reqEntity = requerimientoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Requerimiento", "id", id));
		reqEntity.setIdRequerimiento(reqEntity.getIdRequerimiento());
		reqEntity.setDescripcion(request.getDescripcion());
		Requerimiento updatedReq= requerimientoRepository.save(reqEntity);
		return updatedReq;
	}

	@Override
	public Requerimiento consultaRequerimiento(Long id) {
		Requerimiento reqEntity = requerimientoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Requerimiento", "id", id));
		return reqEntity;
	}

	@Override
	public Page<Requerimiento> consultaAllRequerimiento(Pageable pageable) {
		Page<Requerimiento> listReqEntity = requerimientoRepository.findAll(pageable);
		if(listReqEntity.isEmpty()) {
			new ResourceNotFoundException("Requerimiento", "id", 0);
		}
		return listReqEntity;
	}

	@Override
	public ResponseEntity<?> borrarRequerimiento(Long id) {
		Requerimiento reqEntity = requerimientoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Requerimiento", "id", id));
		requerimientoRepository.delete(reqEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public boolean existsByIdRequerimiento(Long idRequerimiento) {
		return requerimientoRepository.existsLenguajeByIdRequerimiento(idRequerimiento);
	}
	
	public void actualizaStatusRequerimiento(Long id, boolean isActivo) {
		requerimientoRepository.actualizaIsActivo(id, isActivo);
	}
	
}
