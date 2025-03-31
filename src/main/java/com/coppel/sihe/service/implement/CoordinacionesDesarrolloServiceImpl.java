package com.coppel.sihe.service.implement;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.entity.CoordinacionDesarrollo;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.CoordinacionesDesarrolloRepository;
import com.coppel.sihe.service.CoordinacionesDesarrolloService;
import com.coppel.sihe.util.Log;

@Service
public class CoordinacionesDesarrolloServiceImpl implements CoordinacionesDesarrolloService{

	@Autowired
	CoordinacionesDesarrolloRepository coordDevRepository;
	
	@Override
	public ResponseEntity<?> createCoordinacionesDesarrollo(CoordinacionDesarrollo req) {	
		try {
			CoordinacionDesarrollo mapperCoor = new ModelMapper().map(req, CoordinacionDesarrollo.class);
			if(existsById(mapperCoor.getId())) {
				Log.log("Ya existe el ID: " + mapperCoor.getId() + " en el catálogo\"");
				return new ResponseEntity<Mensaje>(new Mensaje("Ya existe el ID: " + mapperCoor.getId() + " en el catálogo"), HttpStatus.OK);
			}
			return new ResponseEntity<CoordinacionDesarrollo>(coordDevRepository.save(mapperCoor), HttpStatus.OK);
		}catch(Exception e) {
			Log.log("No se pudo realizar la creacion de la coordinacion: " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se pudo crear la coordinacion"), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<?> actualizacionCoordinacionesDesarrollo(Long id, CoordinacionDesarrollo request) {
		try {
			CoordinacionDesarrollo coorEntity = coordDevRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Coordinacion desarrollo", "id", id));
			coorEntity.setDescripcion(request.getDescripcion());
			coorEntity.setActivo(request.isActivo());
			coorEntity.setFechaUpdate(request.getFechaUpdate());
			coorEntity.setUsuarioUpdate(request.getUsuarioUpdate());
			CoordinacionDesarrollo updatedCoor = coordDevRepository.save(coorEntity);
			return new ResponseEntity<CoordinacionDesarrollo>(updatedCoor,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al actualizar la coordinacion: "+ id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Ocurrio un error al actualizar el registro"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> consultaCoordinacionDesarrollo(Long id) {
		try {
			CoordinacionDesarrollo coorEntity = coordDevRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Coordinacion", "id", id));
			return new ResponseEntity<CoordinacionDesarrollo>(coorEntity,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al consultar la coordinacion con ID : " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se encontro la coordinacion"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Page<CoordinacionDesarrollo> consultaAllCoordinacionesDesarrollo(Pageable pegeable) {
		Page<CoordinacionDesarrollo> listCoordDevEntity = coordDevRepository.findAll(pegeable);
		if(listCoordDevEntity.isEmpty()) {
			new ResourceNotFoundException("CoordinacionesDesarrollo", "id", 0);
		}
		return listCoordDevEntity;
	}

	@Override
	public ResponseEntity<?> borrarCoordinacionesDesarrollo(Long id) {
		try {
			CoordinacionDesarrollo coorEntity = coordDevRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Coordinacion", "id", id));
			if(!Objects.isNull(coorEntity)) {
				coordDevRepository.delete(coorEntity);
				Log.log("Coordinacion: " + id + " -- Eliminada correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Se elimino el registro exitosamente"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe la coordinacion"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("OCurrio un error al eliminar la consultora: " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al eliminar coordinacion"), HttpStatus.OK);
		}
	}

	@Override
	public boolean existsById(Long id) {
		return coordDevRepository.existsCoordinacionById(id);
	}
	
	@Override
	public ResponseEntity<Mensaje> actualizaStatus(Long id, boolean isActivo) {
		try {
			CoordinacionDesarrollo coorEntity = coordDevRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Coordinacion", "id", id));
			if(!Objects.isNull(coorEntity)) {
				coordDevRepository.actualizaIsActivo(id, isActivo);
				Log.log("Actualización de status de coordinacion: "+ id + " realizada correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Actualización de status exitoso"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe la coordinacion"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("Error al actualizar el status: "+ id + "-" + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al actualizar status"), HttpStatus.OK);
		}
	}
	
}
