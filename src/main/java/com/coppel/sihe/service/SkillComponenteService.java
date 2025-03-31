package com.coppel.sihe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.SkillComponente;

public interface SkillComponenteService {

	public int createSkillComponente(SkillComponente request);
	SkillComponente actualizacionSkillComponente(Long id, SkillComponente request);
	SkillComponente consultaSkillComponente(Long id);
	List<SkillComponente> consultaSkillComponenteByIdEmpleado(Long idEmpleado);
	Page<SkillComponente> consultaAllSkillComponentes(Pageable pageable);
	ResponseEntity<?> borrarSkillComponente(Long id);
	public void actualizaStatusSkillComponente(Long id, boolean isActivo);
}
