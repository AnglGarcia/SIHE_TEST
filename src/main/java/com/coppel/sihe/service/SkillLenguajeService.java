package com.coppel.sihe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.SkillLenguaje;

public interface SkillLenguajeService {

	public int createSkillLenguaje(SkillLenguaje request);
	SkillLenguaje actualizacionSkillLenguaje(Long id, SkillLenguaje request);
	List<SkillLenguaje> consultaSkillLenguajeByIdEmpleado(Long idEmpleado);
	SkillLenguaje consultaSkillLenguaje(Long id);
	Page<SkillLenguaje> consultaAllSkillLenguajes(Pageable pageable);
	ResponseEntity<?> borrarSkillLenguaje(Long id);
	public void actualizaStatus(Long id, boolean isActivo);
}
