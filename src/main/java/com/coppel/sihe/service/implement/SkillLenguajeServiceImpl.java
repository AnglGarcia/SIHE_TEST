package com.coppel.sihe.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.entity.LenguajeProgramacion;
import com.coppel.sihe.entity.Ponderacion;
import com.coppel.sihe.entity.SkillLenguaje;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.EmpleadoRepository;
import com.coppel.sihe.repository.LenguajeProgramacionRepository;
import com.coppel.sihe.repository.PonderacionRepository;
import com.coppel.sihe.repository.SkillLenguajeRepository;
import com.coppel.sihe.service.SkillLenguajeService;

@Service
public class SkillLenguajeServiceImpl implements SkillLenguajeService{

	@Autowired
	SkillLenguajeRepository sLengRepository;
	
	@Autowired
	EmpleadoRepository empRepository;
	
	@Autowired
	LenguajeProgramacionRepository lengRepository;
	
	@Autowired
	PonderacionRepository pondRepository;
	
	
	@Override
	public int createSkillLenguaje(SkillLenguaje req) {		
		return sLengRepository.createSkillLenguaje(req.getIdEmpleado().getId(), req.getIdLenguaje().getIdLenguaje(), req.getIdPonderacion().getIdPonderacion());
	}

	@Override
	public SkillLenguaje actualizacionSkillLenguaje(Long id, SkillLenguaje request) {
		SkillLenguaje skLengEntity = sLengRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("SkillLenguaje", "id", id));
		LenguajeProgramacion lengEntity = lengRepository.findById(request.getIdLenguaje().getIdLenguaje())
				  .orElseThrow(() -> new ResourceNotFoundException("skLenguaje-Lenguaje", "id", id));
		Empleado empEntity = empRepository.findById(request.getIdEmpleado().getId())
				  .orElseThrow(() -> new ResourceNotFoundException("skLenguaje-Empleado", "id", id));
		Ponderacion pondEntity = pondRepository.findById(request.getIdPonderacion().getIdPonderacion())
				  .orElseThrow(() -> new ResourceNotFoundException("skLenguaje-Ponderacion", "id", id));
		skLengEntity.setId(skLengEntity.getId());
		skLengEntity.setIdLenguaje(lengEntity);
		skLengEntity.setIdEmpleado(empEntity);
		skLengEntity.setIdPonderacion(pondEntity);
		SkillLenguaje updatedskLeng = sLengRepository.save(skLengEntity);
		return updatedskLeng;

	}

	@Override
	public SkillLenguaje consultaSkillLenguaje(Long id) {
		SkillLenguaje skLengEntity = sLengRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SkillLenguaje", "id", id));		
		return skLengEntity;
	}

	@Override
	public Page<SkillLenguaje> consultaAllSkillLenguajes(Pageable pageable) {
		Page<SkillLenguaje> listSkLengEntity = sLengRepository.findAll(pageable);
		if(listSkLengEntity.isEmpty()) {
			new ResourceNotFoundException("SkillLenguaje", "id", 0);
		}
		return listSkLengEntity;
	}

	@Override
	public ResponseEntity<?> borrarSkillLenguaje(Long id) {
		SkillLenguaje skLengEntity = sLengRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("SkillLenguaje", "id", id));
		sLengRepository.delete(skLengEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<SkillLenguaje> consultaSkillLenguajeByIdEmpleado(Long idEmpleado) {
		List<SkillLenguaje> listSkLengEntityByIdEmpleado = sLengRepository.consultaSkillLenguajeByIdEmpleado(idEmpleado);
				if(listSkLengEntityByIdEmpleado.size() == 0) {
					new ResourceNotFoundException("SkillLenguaje", "idEmpleado", 0);
				}
				return listSkLengEntityByIdEmpleado;
	}
	
	@Override
	public void actualizaStatus(Long id, boolean isActivo) {
		sLengRepository.actualizaIsActivo(id, isActivo);
	}
	
}
