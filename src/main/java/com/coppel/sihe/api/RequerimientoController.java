package com.coppel.sihe.api;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.coppel.sihe.entity.CentroDevEntity;
import com.coppel.sihe.entity.Requerimiento;
import com.coppel.sihe.service.RequerimientoService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class RequerimientoController {
	
	@Autowired
	RequerimientoService requerimientosService;

	@GetMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/r")
	public String run() {
		return "Controller Requerimientos"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear requerimiento", notes="Permite crear un requerimiento")
	@PostMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/addrequerimiento",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRequerimiento(@RequestBody @Valid Requerimiento request) {
		requerimientosService.createRequerimiento(request);
		Log.log("Requerimiento con Id " + request.getIdRequerimiento() + " creado.");
			return new ResponseEntity<>(new Mensaje("Requerimiento creado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener requerimiento ID", notes="Permite consultar un requerimiento mediante ID")
	@GetMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/consultarequerimiento/{id}")
	public Requerimiento consultaRequerimiento( @Valid @PathVariable(value = "id") Long reqID ) {
		Log.log("Requerimiento con Id " + reqID + " consultado.");
		return requerimientosService.consultaRequerimiento(reqID);
	}
	
	@ApiOperation(value="Obtener requerimientos", notes="Permite obtner un listado de los requerimientos")
	@GetMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/consultarequerimientos")
	public Page<Requerimiento> consultaRequerimiento(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Requerimiento> pagina = requerimientosService.consultaAllRequerimiento(pageable);
		return pagina;
	}
	
	@ApiOperation(value="Actualizar requerimiento", notes="Permite actualizar un requerimiento mediante ID")
	@PutMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/updaterequerimiento/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public Requerimiento updateRequerimiento( @Valid @PathVariable(value = "id") Long reqId, @RequestBody Requerimiento requerimiento) {
		Log.log("Requerimiento con Id " + reqId + " actualizado.");
		return requerimientosService.actualizacionRequerimiento(reqId, requerimiento);
	}
	
	@ApiOperation(value="Eliminar requerimiento", notes="Permite eliminar un requerimiento mediante ID")
	@DeleteMapping(path = Constants.MAPPING_REQUERIMIENTOS+"/deleterequerimiento/{id}") 
	public ResponseEntity<?> deleteRequerimiento( @Valid @PathVariable(value = "id") Long reqId ) {
		requerimientosService.borrarRequerimiento(reqId);
		Log.log("Requerimiento con Id " + reqId + " eliminado.");
		return new ResponseEntity<>(new Mensaje("Requerimiento eliminado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus del requerimiento", notes="Permite actualizar el requerimiento mediante el ID")
	@PutMapping(path = Constants.MAPPING_REQUERIMIENTOS + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
		requerimientosService.actualizaStatusRequerimiento(id, activo);
		Log.log("Estatus de requerimiento con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}
}
