package com.coppel.sihe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.LenguajeProgramacion;

public interface LenguajeProgramacionRepository extends JpaRepository<LenguajeProgramacion, Long>{
						
	boolean existsLenguajeByIdLenguaje(long idLenguaje);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_LENG_P, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
}
