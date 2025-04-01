package com.coppel.sihe.api;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.sihe.api.dto.Mensaje;
import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.entity.Solicitud;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.service.SolicitudService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = Constants.BASE_PATH)
public class SolicitudController {
	
	@Autowired
	private SolicitudService solicitudService;
	
	@ApiOperation(value="Obtener solicitudes", notes="Permite obtener un listado de las solicitudes")
	@GetMapping(path = Constants.MAPPING_SOLICITUDES+"/solicitudesAll")
	public Page<Solicitud> fetchSolicitudList(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Solicitud> pagina = solicitudService.fetchSolicitudList(pageable);
		return pagina;
	}
	
	@ApiOperation(value="Obtener solicitud por ID", notes="Permite obtener una solicitud mediante su ID")
	@GetMapping(path = Constants.MAPPING_SOLICITUDES+"/solicitud/{id}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Solicitud consultaSolicitud(@Valid @PathVariable(value = "id") Long id){
		return solicitudService.consultaSolicitud(id);
	}
	
	@ApiOperation(value="Obtener solicitud por correo", notes="Permite obtener una solicitud mediante su correo")
	@GetMapping(path = Constants.MAPPING_SOLICITUDES+"/solicitudcorreo/{correo}")
	public Solicitud consultaSolicitudByCorreo(@Valid @PathVariable(value = "correo") String correo) throws EmpleadoNotFoundException{
		return solicitudService.consultaSolicitudByCorreo(correo);
	}
	
	
	@ApiOperation(value="Crear solicitud", notes="Permite crear una solicitud")
	@PostMapping(path = Constants.MAPPING_SOLICITUDES+"/solicitudessave",
			produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>saveSolicitud(@Valid @RequestBody Solicitud solicitud) {
		if (solicitudService.existsByCorreo(solicitud.getCorreo())) {
			Log.log("Correo de solicitud ya existente.");
            return new ResponseEntity<>(new Mensaje("El Correo de la solicitud ya existe"), HttpStatus.METHOD_NOT_ALLOWED);
		}
		if (solicitudService.existsById(solicitud.getId())) {
			Log.log("Numero de solicitud ya existente.");
            return new ResponseEntity<>(new Mensaje("El Número de solicitud ya existe"), HttpStatus.NOT_FOUND);
		}
		Log.log("Creando solicitud con Id " + solicitud.getId());
		solicitudService.createSolicitud(solicitud);
		return new ResponseEntity<>(new Mensaje("Solicitud creada."), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualizar solicitud", notes="Permite realizar la actualización de una solicitud mediante el ID")
	@PutMapping(path = Constants.MAPPING_SOLICITUDES+"/solicitudUpdate/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateSolicitud(@PathVariable("id") Long id,
		@Valid @RequestBody Solicitud solicitud) {
			Log.log("Actualizanda solicitud con Id " + id);
			Solicitud auxSol = solicitudService.actualizacionSolicitud(id, solicitud);
			return new ResponseEntity<>(auxSol, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="Actualizar status de la solicitud por ID", notes="Permite actualizar el status de la solicitud mediante el ID")
	@PutMapping(path = Constants.MAPPING_SOLICITUDES+"/aprobada/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		Log.log("Actualizando estatus de la solicitud con Id " + id);
		solicitudService.actualizaIsAprobada(id, activo);
		return new ResponseEntity<>(new Mensaje("Estatus actualizado"), HttpStatus.OK);	
	}

}
