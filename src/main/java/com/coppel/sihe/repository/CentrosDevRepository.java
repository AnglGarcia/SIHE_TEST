package com.coppel.sihe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.CentroDevEntity;

public interface CentrosDevRepository extends JpaRepository<CentroDevEntity, Integer> {
	
	boolean existsCentroDevById(int id);
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_CDEV, nativeQuery = true)
	void actualizaIsActivo(@Param("id")int id, @Param("isActivo")Boolean isActivo);	
}
