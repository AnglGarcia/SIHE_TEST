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
import com.coppel.sihe.entity.Empleado;




@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>   {
    
	public Optional<Empleado> findById(Long id);
	
	public Optional<Empleado> findEmpleadoByCorreo(String correo);
	
	public Optional<Empleado> findEmpleadoByIdAndPassword(Long id, String password);
	
	public Optional<Empleado> findByCorreo(String correo);
	
	boolean existsById(long id);
	
	@Query(value = Consultas.VALIDA_EMPLEADO_X_EMAIL, nativeQuery = true)
	public boolean existsByCorreo(@Param("correo")String correo);
	
	@Query(value = Consultas.CONSULTA_EMP_X_PERFIL_CDEV, nativeQuery = true)
	public List<Empleado> consultaEmpleadoByPerfilAndCentro(@Param("idPerfil")Long idPerfil, @Param("idCentro")Long idCentro);
	
	@Query(value = Consultas.CONSULTA_EMPLEADOS_X_PERFIL, nativeQuery = true)
	public List<Empleado> consultaEmpleadoByPerfil(@Param("idPerfil")Long idPerfil);
	
	@Query(value = Consultas.CONSULTA_EMPLEADOS_X_CDEV, nativeQuery = true)
	public List<Empleado> consultaEmpleadoByCentro(@Param("idCentro")Long idCentro);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_ACTIVO_EMPLEADO, nativeQuery = true)
	public void actualizaIsActivo(@Param("id")Long id, @Param("isActivo")Boolean isActivo);
	
	@Transactional
	@Modifying
	@Query(value = Consultas.UPDATE_PASSWORD_EMPLEADO, nativeQuery = true)
	public void actualizaPassword(@Param("id")Long id, @Param("pass")String password);

}