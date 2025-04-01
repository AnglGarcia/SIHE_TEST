package com.coppel.sihe.api;

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
import com.coppel.sihe.entity.Ponderacion;
import com.coppel.sihe.service.PonderacionService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class PonderacionController {

	@Autowired
	PonderacionService ponderacionService;
	
	@GetMapping(path = Constants.MAPPING_PONDERACION+"/r")
	public String run() {
		return "Controller Ponderaciones"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear ponderación", notes="Permite crear una ponderación")
	@PostMapping(path = Constants.MAPPING_PONDERACION+"/addponderacion",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPonderacion(@RequestBody @Valid Ponderacion request) {
		ponderacionService.createPonderacion(request);
		Log.log("Ponderacion con Id " + request.getDescripcion() + " creada.");
		return new ResponseEntity<>(new Mensaje("Ponderación creada"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener ponderación ID", notes="Permite obtener una ponderación mediante ID")
	@GetMapping(path = Constants.MAPPING_PONDERACION+"/consultaponderacion/{id}")
	public Ponderacion consultaPonderacion( @Valid @PathVariable(value = "id") Long ponderacionID ) {
		Log.log("Ponderacion con Id " + ponderacionID + " obtenida.");
		return ponderacionService.consultaPonderacion(ponderacionID);
	}
	
	@ApiOperation(value="Obtener ponderaciones", notes="Permite obtener un listado de ponderaciones")
	@GetMapping(path = Constants.MAPPING_PONDERACION+"/consultaponderaciones")
	public Page<Ponderacion> consultaPonderaciones(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Ponderacion> pagina = ponderacionService.consultaAllPonderacion(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar ponderación", notes="Permite actualizar una ponderación mediante ID")
	@PutMapping(path = Constants.MAPPING_PONDERACION+"/updateponderacion/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public Ponderacion updatePonderacion( @Valid @PathVariable(value = "id") Long ponderacionId, @RequestBody Ponderacion ponderacion) {
		Log.log("Ponderacion con Id " + ponderacionId + " actualizada.");
		return ponderacionService.actualizacionPonderacion(ponderacionId, ponderacion);
	}
	
	@ApiOperation(value="Eliminar ponderación", notes="Permite eliminar una ponderación mediante ID")
	@DeleteMapping(path = Constants.MAPPING_PONDERACION+"/deleteponderacion/{id}") 
	public ResponseEntity<?> deletePonderacion( @Valid @PathVariable(value = "id") Long ponderacionId ) {
		ponderacionService.borrarPonderacion(ponderacionId);
		Log.log("Ponderacion con Id " + ponderacionId + " eliminada.");
		return new ResponseEntity<>(new Mensaje("Ponderación eliminada"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus de ponderación", notes="Permite actualizar la ponderación mediante el ID")
	@PutMapping(path = Constants.MAPPING_PONDERACION + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
		ponderacionService.actualizaStatusPonderacion(id, activo);
		Log.log("Estatus de ponderacion con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}
	
}
