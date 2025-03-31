package com.coppel.sihe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {

	boolean existsComponenteByIdComponente(long idComponente);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_COMP, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
}
