package com.coppel.sihe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coppel.sihe.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findById(String username);

	@Query(value = "select u from empleados where correo = :cadena and activo = true", nativeQuery = true)
	public Optional<Usuario> consultaUsuarioByUserName(@Param("cadena") String usuario);

}
