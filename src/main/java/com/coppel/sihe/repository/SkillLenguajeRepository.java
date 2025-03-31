package com.coppel.sihe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.SkillLenguaje;

public interface SkillLenguajeRepository extends JpaRepository<SkillLenguaje, Long>{

	@Modifying
	@Transactional
	@Query(value = Consultas.INSERT_SK_LENG_EMPL, nativeQuery = true)
	public int createSkillLenguaje(@Param("idEmpleado")Long idEmpleado,
											  @Param("idLenguaje")Long idLenguaje,
												@Param("idPonderacion")Long idPonderacion);
	

	@Query(value = Consultas.CONSULTA_SK_LENG_X_EMPLEADO, nativeQuery = true)
	public List<SkillLenguaje> consultaSkillLenguajeByIdEmpleado(@Param("idEmpleado")Long idEmpleado);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_SK_LENG, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);	
}
