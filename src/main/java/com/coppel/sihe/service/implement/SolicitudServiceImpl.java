package com.coppel.sihe.service.implement;

import java.util.Calendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.Solicitud;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.SolicitudRepository;
import com.coppel.sihe.service.SolicitudService;
import com.coppel.sihe.util.Log;

@Service
public class SolicitudServiceImpl implements SolicitudService{

	@Autowired
	private SolicitudRepository solicitudRepository;
	
	@Override
	public Page<Solicitud> fetchSolicitudList(Pageable page) {
		return solicitudRepository.findAll(page);
	}
	
	@Override
	public Solicitud fetchSolicitudByIdAndPassword(Long id, String password) throws EmpleadoNotFoundException {
		return solicitudRepository.findSolicitudByIdAndPassword(id, password)
				.orElseThrow(() ->	new EmpleadoNotFoundException("Solicitud no disponible"));
	}
	
	@Override
	public Solicitud createSolicitud(Solicitud req) {	
		Solicitud mapperSolicitud = new ModelMapper().map(req, Solicitud.class);
		Log.log("Solicitud con Id " + req.getId() + " creada.");
		return solicitudRepository.save(mapperSolicitud);
	}
	
	@Override
	public Solicitud actualizacionSolicitud(Long id, Solicitud request) {
		Solicitud solEntity = solicitudRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Solicitud", "id", id));
		solEntity.setId(solEntity.getId());
		solEntity.setPrimerNombre(request.getPrimerNombre());
		solEntity.setSegundoNombre(request.getSegundoNombre());
		solEntity.setApellidoPaterno(request.getApellidoPaterno());
		solEntity.setApellidoMaterno(request.getApellidoMaterno());
		solEntity.setCentro(request.getCentro());
		solEntity.setPerfil(request.getPerfil());
		solEntity.setLugarPosicion(request.getLugarPosicion());
		solEntity.setExterno(request.isExterno());
		solEntity.setCorreo(request.getCorreo());
		solEntity.setPassword(request.getPassword());
		solEntity.setUsuarioInsert(solEntity.getUsuarioInsert());
		solEntity.setUsuarioUpdate(request.getUsuarioUpdate());
		solEntity.setFechaInsert(solEntity.getFechaInsert());
		solEntity.setFechaUpdate(Calendar.getInstance().getTime());
		Solicitud updatedSol = solicitudRepository.save(solEntity);
		Log.log("Solicitud con Id " + id + " actualizada.");
		return updatedSol;
	}
	
	@Override
	public Solicitud consultaSolicitud(Long id) {
		Solicitud solEntity = solicitudRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Solicitud", "id", id));	
		Log.log("Solicitud con Id " + id + " Obtenida.");
		return solEntity;
		
	}

	@Override
	public List<Solicitud> consultaAllSolicitud() {
		List<Solicitud> listSolEntity = solicitudRepository.findAll();
		if(listSolEntity.size() == 0) {
			Log.log("No hay Solicitud para consultar");
			new ResourceNotFoundException("Solicitud", "id", 0);
		}
			
		return listSolEntity;
	}

	@Override
	public ResponseEntity<?> borrarSolicitud(Long id) {
		Solicitud solEntity = solicitudRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Solicitud", "id", id));
		solicitudRepository.delete(solEntity);
		Log.log("Solicitud con Id " + id + " eliminada.");
		return ResponseEntity.ok().build();
	}

	@Override
	public Solicitud consultaSolicitudByCorreo(String correo) throws EmpleadoNotFoundException {
		Log.log("Solicitud con correo " + correo + " Obtenida.");
		return solicitudRepository.findSolicitudByCorreo(correo)
				.orElseThrow(() -> new EmpleadoNotFoundException("Solicitud no disponible"));
	}

	@Override
	public boolean existsById(Long id) {
		return solicitudRepository.existsById(id);
	}

	@Override
	public boolean existsByCorreo(String correo) {
		return solicitudRepository.existsByCorreo(correo);
	}

	@Override
	public List<Solicitud> consultaSolicitudByPerfilAndCentro(String perfil, String centro) {
		List<Solicitud> listSolicitudByPerfilAndCentro = solicitudRepository.consultaSolicitudByPerfilAndCentro(perfil, centro);
		if(listSolicitudByPerfilAndCentro.size() == 0) {
			Log.log("No se encontraron empleados con perfil " + perfil + " y centro " + centro);
			new ResourceNotFoundException("SkillLenguaje", "idSolicitud", 0);
		}
		return listSolicitudByPerfilAndCentro;
	}

	@Override
	public List<Solicitud> consultaSolicitudByPerfil(String perfil) {
		List<Solicitud> listSolicitudByPerfil = solicitudRepository.consultaSolicitudByPerfil(perfil);
		if(listSolicitudByPerfil.size() == 0) {
			Log.log("No se encontró solicitud con perfil " + perfil);
			new ResourceNotFoundException("SkillLenguaje", "idSolicitud", 0);
		}
		return listSolicitudByPerfil;
	}

	@Override
	public List<Solicitud> consultaSolicitudByCentro(String centro) {
		List<Solicitud> listSolicitudByCentro = solicitudRepository.consultaSolicitudByCentro(centro);
		if(listSolicitudByCentro.size() == 0) {
			Log.log("No se encontró solicitud con centro " + centro);
			new ResourceNotFoundException("SkillLenguaje", "idSolicitud", 0);
		}
		return listSolicitudByCentro;
	}
	
	@Override
	public void actualizaIsAprobada(Long id, boolean isAprobada) {
		solicitudRepository.actualizaIsAprobada(id, isAprobada);
		Log.log("Estatus de la solicitud con Id " + id + " actualizado.");
	}
}
