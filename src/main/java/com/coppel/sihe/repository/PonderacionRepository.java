package com.coppel.sihe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.Ponderacion;

public interface PonderacionRepository extends JpaRepository<Ponderacion, Long>{

	boolean existsLenguajeByIdPonderacion(long idPonderacion);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_PONDERACION, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
	
}
