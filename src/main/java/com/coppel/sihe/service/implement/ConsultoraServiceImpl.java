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
import com.coppel.sihe.entity.Consultora;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.ConsultoraRepository;
import com.coppel.sihe.service.ConsultoraService;
import com.coppel.sihe.util.Log;

@Service
public class ConsultoraServiceImpl implements ConsultoraService{
	
	@Autowired
	ConsultoraRepository consRepository;
	
	@Override
	public ResponseEntity<?> createConsultora(Consultora req) {	
		try {
			Consultora mapperCons = new ModelMapper().map(req, Consultora.class);
			if(existsByIdConsultora(mapperCons.getId())) {
				Log.log("Ya existe el ID: " + mapperCons.getId() + " en el catálogo\"");
				return new ResponseEntity<Mensaje>(new Mensaje("Ya existe el ID: " + mapperCons.getId() + " en el catálogo"), HttpStatus.OK);
			}
			return new ResponseEntity<Consultora>(consRepository.save(mapperCons), HttpStatus.OK);
		}catch(Exception e) {
			Log.log("No se pudo realizar la creacion de la consultora: " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se pudo crear la consultora"), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<?> actualizacionConsultora(Long id, Consultora request) {
		try {
			Consultora consEntity = consRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Consultora", "id", id));
			consEntity.setDescripcion(request.getDescripcion());
			consEntity.setActivo(request.isActivo());
			consEntity.setFechaUpdate(request.getFechaUpdate());
			consEntity.setUsuarioUpdate(request.getUsuarioUpdate());
			Consultora updatedCons= consRepository.save(consEntity);
			return new ResponseEntity<Consultora>(updatedCons,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al actualizar la Consultora: "+ id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Ocurrio un error al actualizar el registro"), HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<?> consultaConsultora(Long id) {
		try {
			Consultora consEntity = consRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Consultora", "id", id));
			return new ResponseEntity<Consultora>(consEntity,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al consultar la consultora con ID : " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se encontro la consultora"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Page<Consultora> consultaAllConsultora(Pageable pageable) {
		Page<Consultora> listCompEntity = consRepository.findAll(pageable);
		if(listCompEntity.isEmpty()) {
			new ResourceNotFoundException("Componente", "id", 0);
		}
		return listCompEntity;
	}

	@Override
	public ResponseEntity<?> borrarConsultora(Long id) {
		try {
			Consultora consEntity = consRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Componente", "id", id));
			if(!Objects.isNull(consEntity)) {
				consRepository.delete(consEntity);
				Log.log("Consultora: " + id + " -- Eliminada correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Se elimino el registro exitosamente"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe la consultora"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("OCurrio un error al eliminar la consultora: " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al eliminar consultora"), HttpStatus.OK);
		}
	}

	@Override
	public boolean existsByIdConsultora(Long idConsultora) {
		return consRepository.existsById(idConsultora);
	}
	
	@Override
	public ResponseEntity<Mensaje> actualizaStatusConsultora(Long id, boolean isActivo) {
		try {
			Consultora consEntity = consRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Consultora", "id", id));
			if(!Objects.isNull(consEntity)) {
				consRepository.actualizaIsActivo(id, isActivo);
				Log.log("Actualización de status de consultora: "+ id + " realizada correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Actualización de status exitoso"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe la consultora"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("Error al actualizar el status: "+ id + "-" + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al actualizar status"), HttpStatus.OK);
		}
	}
	
}
