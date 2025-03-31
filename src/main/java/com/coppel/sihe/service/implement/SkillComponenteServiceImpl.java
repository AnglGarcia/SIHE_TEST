package com.coppel.sihe.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Componente;
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.entity.Ponderacion;
import com.coppel.sihe.entity.SkillComponente;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.ComponenteRepository;
import com.coppel.sihe.repository.EmpleadoRepository;
import com.coppel.sihe.repository.PonderacionRepository;
import com.coppel.sihe.repository.SkillComponenteRepository;
import com.coppel.sihe.service.SkillComponenteService;

@Service
public class SkillComponenteServiceImpl implements SkillComponenteService{

	@Autowired
	SkillComponenteRepository sCompRepository;
	
	@Autowired
	EmpleadoRepository empRepository;
	
	@Autowired
	ComponenteRepository compRepository;
	
	@Autowired
	PonderacionRepository pondRepository;

	
	@Override
	public int createSkillComponente(SkillComponente req) {	
		//SkillComponente mapperSklAsig = new ModelMapper().map(req, SkillComponente.class);
		return sCompRepository.createSkillComponente(req.getIdEmpleado().getId(), req.getIdComponente().getIdComponente(), req.getIdPonderacion().getIdPonderacion());
	}

	@Override
	public SkillComponente actualizacionSkillComponente(Long id, SkillComponente request) {
		SkillComponente skCompEntity = sCompRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("SkillComponente", "id", id));
		Componente compEntity = compRepository.findById(request.getIdComponente().getIdComponente())
				  .orElseThrow(() -> new ResourceNotFoundException("SkillComp-Componente", "id", id));
		Empleado empEntity = empRepository.findById(request.getIdEmpleado().getId())
				  .orElseThrow(() -> new ResourceNotFoundException("SkillComp-Empleado", "id", id));
		Ponderacion pondEntity = pondRepository.findById(request.getIdPonderacion().getIdPonderacion())
				  .orElseThrow(() -> new ResourceNotFoundException("SkillComp-Ponderacion", "id", id));
		skCompEntity.setId(skCompEntity.getId());
		skCompEntity.setIdComponente(compEntity);
		skCompEntity.setIdEmpleado(empEntity);
		skCompEntity.setIdPonderacion(pondEntity);
		SkillComponente updatedSkComp = sCompRepository.save(skCompEntity);
		return updatedSkComp;

	}

	@Override
	public SkillComponente consultaSkillComponente(Long id) {
		SkillComponente skCompEntity = sCompRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SkillComponente", "id", id));		
		return skCompEntity;
	}

	@Override
	public Page<SkillComponente> consultaAllSkillComponentes(Pageable pageable) {
		Page<SkillComponente> listSkCompEntity = sCompRepository.findAll(pageable);
		if(listSkCompEntity.isEmpty()) {
			new ResourceNotFoundException("SkillComponente", "id", 0);
		}
		return listSkCompEntity;
	}

	@Override
	public ResponseEntity<?> borrarSkillComponente(Long id) {
		SkillComponente skCompEntity = sCompRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("SkillComponente", "id", id));
		sCompRepository.delete(skCompEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<SkillComponente> consultaSkillComponenteByIdEmpleado(Long idEmpleado) {
		List<SkillComponente> listSkCompEntityByIdEmpleado = sCompRepository.consultaSkillComponenteByIdEmpleado(idEmpleado);
		if(listSkCompEntityByIdEmpleado.size() == 0) {
			new ResourceNotFoundException("SkillComponente", "idEmpleado", 0);
		}
		return listSkCompEntityByIdEmpleado;
	}
	
	public void actualizaStatusSkillComponente(Long id, boolean isActivo) {
		sCompRepository.actualizaIsActivo(id, isActivo);
	}
	
	
}
