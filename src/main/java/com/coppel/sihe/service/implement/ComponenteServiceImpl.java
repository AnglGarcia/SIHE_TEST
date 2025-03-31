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
import com.coppel.sihe.entity.Componente;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.ComponenteRepository;
import com.coppel.sihe.service.ComponenteService;
import com.coppel.sihe.util.Log;


@Service
public class ComponenteServiceImpl implements ComponenteService{

	@Autowired
	ComponenteRepository componenteRepository;
	
	@Override
	public ResponseEntity<?> createComponente(Componente req) {	
		try {
			Componente mapperComp = new ModelMapper().map(req, Componente.class);
			if(existsByIdComponente(mapperComp.getIdComponente())) {
				Log.log("Ya existe el ID: " + mapperComp.getIdComponente() + " en el catálogo\"");
				return new ResponseEntity<Mensaje>(new Mensaje("Ya existe el ID: " + mapperComp.getIdComponente() + " en el catálogo"), HttpStatus.OK);
			}
			return new ResponseEntity<Componente>(componenteRepository.save(mapperComp), HttpStatus.OK);
		}catch(Exception e) {
			Log.log("No se pudo realizar la creacion del centro de desarrollo: " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se pudo crear el centro de desarrollo"), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<?> actualizacionComponente(Long id, Componente request) {
		try {
			Componente compEntity = componenteRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Componente", "id", id));
			compEntity.setIdComponente(compEntity.getIdComponente());
			compEntity.setDescripcion(request.getDescripcion());
			Componente updatedComp= componenteRepository.save(compEntity);
			return new ResponseEntity<Componente>(updatedComp,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al actualizar el Componente: "+ id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Ocurrio un error al actualizar el registro"), HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<?> consultaComponente(Long id) {
		try {
			Componente compEntity = componenteRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Componente", "id", id));
			return new ResponseEntity<Componente>(compEntity,HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al consultar el componente con ID : " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se encontro el componente"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Page<Componente> consultaAllComponente(Pageable pageable) {
		Page<Componente> listCompEntity = componenteRepository.findAll(pageable);
		if(listCompEntity.isEmpty()) {
			new ResourceNotFoundException("Componente", "id", 0);
		}
		return listCompEntity;
	}

	@Override
	public ResponseEntity<?> borrarComponente(Long id) {
		try {
			Componente compEntity = componenteRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("Componente", "id", id));
			if(!Objects.isNull(compEntity)) {
				componenteRepository.delete(compEntity);
				Log.log("Componente: " + id + " -- Eliminado correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Se elimino el registro exitosamente"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe el componente"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("OCurrio un error al eliminar el componente: " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al eliminar componente"), HttpStatus.OK);
		}
	}

	@Override
	public boolean existsByIdComponente(Long idComponente) {
		return componenteRepository.existsComponenteByIdComponente(idComponente);
	}
	
	@Override
	public ResponseEntity<Mensaje> actualizaStatus(Long id, boolean isActivo) {
		try {
			Componente compEntity = componenteRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Componente", "id", id));
			if(!Objects.isNull(compEntity)) {
				componenteRepository.actualizaIsActivo(id, isActivo);
				Log.log("Actualización de status de componente: "+ id + " realizada correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Actualización de status exitoso"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe el componente"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("Error al actualizar el status: "+ id + "-" + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al actualizar status"), HttpStatus.OK);
		}
	}

	
	
	
}
