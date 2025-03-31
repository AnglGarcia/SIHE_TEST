package com.coppel.sihe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.SkillComponente;

public interface SkillComponenteRepository extends JpaRepository<SkillComponente, Long>{

	@Modifying
	@Transactional
	@Query(value = Consultas.INSERT_SK_COMP_EMPL, nativeQuery = true)
	public int createSkillComponente(@Param("idEmpleado")Long idEmpleado,
											  @Param("idComponente")Long idComponente,
												@Param("idPonderacion")Long idPonderacion);
	
	@Query(value = Consultas.CONSULTA_SK_COMP_X_EMPL, nativeQuery = true)
	public List<SkillComponente> consultaSkillComponenteByIdEmpleado(@Param("idEmpleado")Long idEmpleado);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_SK_COMP, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
	
}
