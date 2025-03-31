package com.coppel.sihe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.Solicitud;
import com.coppel.sihe.error.EmpleadoNotFoundException;

public interface SolicitudService {

	Page<Solicitud> fetchSolicitudList(Pageable page);
	
	Solicitud fetchSolicitudByIdAndPassword(Long id, String password) throws EmpleadoNotFoundException;

	Solicitud createSolicitud(Solicitud request);
	
	Solicitud actualizacionSolicitud(Long id, Solicitud request);
	
	Solicitud consultaSolicitud(Long id);
	
	Solicitud consultaSolicitudByCorreo(String correo) throws EmpleadoNotFoundException;
	
	List<Solicitud> consultaAllSolicitud();
	
	ResponseEntity<?> borrarSolicitud(Long id);
	
	public boolean existsById(Long id);
	
	public boolean existsByCorreo(String correo);
	
	List<Solicitud> consultaSolicitudByPerfilAndCentro(String perfil, String centro);
	
	List<Solicitud> consultaSolicitudByPerfil(String perfil);
	
	List<Solicitud> consultaSolicitudByCentro(String idCentro);
	
	public void actualizaIsAprobada(Long id, boolean isAprobada);
}
