package com.coppel.sihe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.RequerimientoAsignado;
public interface RequerimientoAsignadoRepository extends JpaRepository<RequerimientoAsignado, Long>{

	@Modifying
	@Transactional
	@Query(value = Consultas.INSERT_REQ_ASIG_EMPL, nativeQuery = true)
	public int createRequerimientoAsignado(@Param("idRequerimiento")Long idRequerimiento,
											  @Param("idEmpleado")Long idEmpleado,
												@Param("idCentro")int idCentro);
	

	@Query(value = Consultas.CONSULTA_REQ_ASIG_X_EMPL, nativeQuery = true)
	public List<RequerimientoAsignado> consultaRequerimientosAsignadosByIdEmpleado(@Param("idEmpleado")Long idEmpleado);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_REQ_ASIG, nativeQuery = true)
	void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
	
}
