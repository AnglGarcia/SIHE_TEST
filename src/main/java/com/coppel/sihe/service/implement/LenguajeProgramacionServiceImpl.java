package com.coppel.sihe.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.LenguajeProgramacion;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.LenguajeProgramacionRepository;
import com.coppel.sihe.service.LenguajesProgramacionService;

@Service
public class LenguajeProgramacionServiceImpl implements LenguajesProgramacionService {

	@Autowired
	LenguajeProgramacionRepository lengProgRepository;
	
	
	@Override
	public LenguajeProgramacion createLenguajeProgramacion(LenguajeProgramacion req) {	
		LenguajeProgramacion mapperLegProg = new ModelMapper().map(req, LenguajeProgramacion.class);
		return lengProgRepository.save(mapperLegProg);
	}

	@Override
	public LenguajeProgramacion actualizacionLenguajeProgramacion(Long id, LenguajeProgramacion request) {
		LenguajeProgramacion lengProgEntity = lengProgRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("LenguajeProgramacion", "id", id));
		lengProgEntity.setIdLenguaje(lengProgEntity.getIdLenguaje());
		lengProgEntity.setDescripcion(request.getDescripcion());
		LenguajeProgramacion updatedLengProg = lengProgRepository.save(lengProgEntity);
		return updatedLengProg;
	}

	@Override
	public LenguajeProgramacion consultaLenguajeProgramacion(Long id) {
		LenguajeProgramacion lengProgEntity = lengProgRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("LenguajeProgramacion", "id", id));
		return lengProgEntity;
	}

	@Override
	public Page<LenguajeProgramacion> consultaAllLenguajeProgramacion(Pageable pageable) {
		Page<LenguajeProgramacion> listLenguajesEntity = lengProgRepository.findAll(pageable);
		if(listLenguajesEntity.isEmpty()) {
			new ResourceNotFoundException("LenguajeProgramacion", "id", 0);
		}
		return listLenguajesEntity;
	}

	@Override
	public ResponseEntity<?> borrarLenguajeProgramacion(Long id) {
		LenguajeProgramacion lenguajeEntity = lengProgRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("LenguajeProgramacion", "id", id));
		lengProgRepository.delete(lenguajeEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	 public boolean existsByIdLenguaje(Long idLenguaje){
        return lengProgRepository.existsLenguajeByIdLenguaje(idLenguaje);
    }
	
	public void actualizaStatusLenguajeProgramacion(Long id, boolean isActivo) {
	    lengProgRepository.actualizaIsActivo(id, isActivo);
	}
	
}
