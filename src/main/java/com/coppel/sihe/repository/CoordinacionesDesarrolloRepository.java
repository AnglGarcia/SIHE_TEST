package com.coppel.sihe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.CoordinacionDesarrollo;

public interface CoordinacionesDesarrolloRepository extends JpaRepository<CoordinacionDesarrollo, Long>{

	boolean existsCoordinacionById(long id);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_COORDDEV, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
}
