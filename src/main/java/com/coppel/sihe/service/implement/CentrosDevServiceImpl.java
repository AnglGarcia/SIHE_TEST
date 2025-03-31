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
import com.coppel.sihe.entity.CentroDevEntity;
import com.coppel.sihe.entity.CoordinacionDesarrollo;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.CentrosDevRepository;
import com.coppel.sihe.repository.CoordinacionesDesarrolloRepository;
import com.coppel.sihe.service.CentrosDevService;
import com.coppel.sihe.util.Log;

@Service
public class CentrosDevServiceImpl implements CentrosDevService{
	
	@Autowired
	CentrosDevRepository centrosDevRepository;
	
	@Autowired
	CoordinacionesDesarrolloRepository coordinacionesDesarrolloRepository;

	@Override
	public ResponseEntity<?> createCentrosDesarrollo(CentroDevEntity req) {	
		try {
			CentroDevEntity mapperCenDev = new ModelMapper().map(req, CentroDevEntity.class);
			if(existsById(mapperCenDev.getId())) {
				Log.log("Ya existe el ID: " + mapperCenDev.getId() + " en el catálogo\"");
				return new ResponseEntity<Mensaje>(new Mensaje("Ya existe el ID: " + mapperCenDev.getId() + " en el catálogo"), HttpStatus.OK);
			}
			return new ResponseEntity<CentroDevEntity>(centrosDevRepository.save(mapperCenDev), HttpStatus.OK);
		}catch(Exception e) {
			Log.log("No se pudo realizar la creacion del centro de desarrollo: " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se pudo crear el centro de desarrollo"), HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<?> actualizacionCentrosDesarrollo(int id, CentroDevEntity request) {
		try {
			CentroDevEntity cdEntity = centrosDevRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("CentrosDev", "id", id));
			CoordinacionDesarrollo cdDesarrollo = coordinacionesDesarrolloRepository.findById(request.getIdCoordinacionDesarrollo().getId())
					  .orElseThrow(() -> new ResourceNotFoundException("CordinacionDesarrollo", "id", id));
			cdEntity.setId(cdEntity.getId());
			cdEntity.setDescripcion(request.getDescripcion());
			cdEntity.setIdCoordinacionDesarrollo(cdDesarrollo);
			CentroDevEntity updatedCentDev = centrosDevRepository.save(cdEntity);
			return new ResponseEntity<CentroDevEntity>(updatedCentDev, HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al actualizar el Centro de Desarrollo: "+ id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Ocurrio un error al actualizar el registro"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<?> consultaCentroDesarrollo(int id) {
		try {
			CentroDevEntity cdEntity = centrosDevRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("CentrosDev", "id", id));
			return new ResponseEntity<CentroDevEntity>(cdEntity, HttpStatus.OK);
		}catch(Exception e) {
			Log.log("Ocurrio un error al consultar el centro de desarrollo con ID : " + id + " - " + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("No se pudo consultar el centro de desarrollo"), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Page<CentroDevEntity> consultaAllCentrosDesarrollo(Pageable pageable) {
		Page<CentroDevEntity> listCdEntity = centrosDevRepository.findAll(pageable);
		if(listCdEntity.isEmpty()) {
			new ResourceNotFoundException("CentrosDev", "id", 0);
		}
		return listCdEntity;
	}

	@Override
	public ResponseEntity<?> borrarCentrosDev(int id) {
		try {
			CentroDevEntity cdEntity = centrosDevRepository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("CentrosDev", "id", id));
			if(!Objects.isNull(cdEntity)) {
				centrosDevRepository.delete(cdEntity);
				Log.log("Centro de desarrollo: " + id + " -- Eliminado correctamente");
				return new ResponseEntity<Mensaje>(new Mensaje("Se elimino el registro correctamente"), HttpStatus.OK);
			}
			Log.log("No existe el centro de desarrollo: " + id + " en el catalogo");
			return new ResponseEntity<Mensaje>(new Mensaje("No existe el centro de desarrollo"), HttpStatus.NOT_FOUND);
		}catch(Exception e ) {
			Log.log("Error al eliminar el centro de desarrollo: "+ id + "-" + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al eliminar centro de desarrollo"), HttpStatus.OK);	
		}
	}

	@Override
	public boolean existsById(int id) {
		return centrosDevRepository.existsCentroDevById(id);
	}
	
	@Override
	public ResponseEntity<Mensaje> actualizaStatus(int id, boolean isActivo) {
		try {
			CentroDevEntity cdEntity = centrosDevRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("CentrosDev", "id", id));
			if(!Objects.isNull(cdEntity)) {
				centrosDevRepository.actualizaIsActivo(id, isActivo);
				return new ResponseEntity<Mensaje>(new Mensaje("Actualización de status exitoso"), HttpStatus.OK);
			}
			return new ResponseEntity<Mensaje>(new Mensaje("No existe el centro de desarrollo"), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Log.log("Error al actualizar el status: "+ id + "-" + e.getMessage());
			return new ResponseEntity<Mensaje>(new Mensaje("Error al actualizar status"), HttpStatus.OK);
		}
	}

}
