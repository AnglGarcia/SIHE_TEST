package com.coppel.sihe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	boolean existsPerfilByIdPerfil(long idPerfil);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_PERFIL, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
}
