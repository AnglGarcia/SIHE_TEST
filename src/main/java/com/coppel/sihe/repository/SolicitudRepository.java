package com.coppel.sihe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.sihe.constants.Consultas;
import com.coppel.sihe.entity.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

	public Optional<Solicitud> findById(Long id);
	
	public Optional<Solicitud> findSolicitudByCorreo(String correo);
	
	public Optional<Solicitud> findSolicitudByIdAndPassword(Long id, String password);
	
	public Optional<Solicitud> findByCorreo(String correo);
	
	boolean existsById(long id);
	
	@Query(value = Consultas.VALIDA_EMPLEADO_X_EMAIL, nativeQuery = true)
	public boolean existsByCorreo(@Param("correo")String correo);
	
	@Query(value = Consultas.CONSULTA_SOL_X_PERFIL_CDEV, nativeQuery = true)
	public List<Solicitud> consultaSolicitudByPerfilAndCentro(@Param("perfil")String perfil, @Param("centro")String centro);
	
	@Query(value = Consultas.CONSULTA_SOL_X_PERFIL, nativeQuery = true)
	public List<Solicitud> consultaSolicitudByPerfil(@Param("perfil")String perfil);
	
	@Query(value = Consultas.CONSULTA_SOL_X_CDEV, nativeQuery = true)
	public List<Solicitud> consultaSolicitudByCentro(@Param("centro")String centro);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_SOL_APROBADA, nativeQuery = true)
	void actualizaIsAprobada(@Param("id")Long id, @Param("isAprobada")Boolean isAprobada);
}
