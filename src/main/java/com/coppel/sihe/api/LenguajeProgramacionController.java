package com.coppel.sihe.api;

import java.util.List;

import javax.validation.Valid;

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
import com.coppel.sihe.entity.LenguajeProgramacion;
import com.coppel.sihe.service.LenguajesProgramacionService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class LenguajeProgramacionController {

	@Autowired
	LenguajesProgramacionService lenguajeService;
	
	@GetMapping(path = Constants.MAPPING_LENGUAJES+"/r")
	public String run() {
		return "Controller Lenguajes Programacion"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear Lenguaje de programacion", notes="Permite crear un Lenguaje de programacion")
	@PostMapping(path = Constants.MAPPING_LENGUAJES+"/addlenguaje",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createLenguaje(@RequestBody @Valid LenguajeProgramacion request) {
		lenguajeService.createLenguajeProgramacion(request);
		Log.log("Lenguaje con Id " + request.getDescripcion() + " creado.");
		return new ResponseEntity<>(new Mensaje("Lenguaje creado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener Lenguaje de programacion ID", notes="Permite obtener un Lenguaje de programacion mediante ID")
	@GetMapping(path = Constants.MAPPING_LENGUAJES+"/consultalenguaje/{id}")
	public LenguajeProgramacion consultaLenguajeId( @Valid @PathVariable(value = "id") Long lengID ) {
		Log.log("Lenguaje con Id " + lengID + " Obtenido.");
		return lenguajeService.consultaLenguajeProgramacion(lengID);
	}
	
	@ApiOperation(value="Obtener Lenguajes de programacion", notes="Permite obtener un listado con Lenguajes de programacion")
	@GetMapping(path = Constants.MAPPING_LENGUAJES+"/consultalenguajes")
	public Page<LenguajeProgramacion> consultaLenguajes(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<LenguajeProgramacion> pagina = lenguajeService.consultaAllLenguajeProgramacion(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar Lenguaje de programacion ID", notes="Permite actualizar un Lenguaje de programacion mediante ID")
	@PutMapping(path = Constants.MAPPING_LENGUAJES+"/updatelenguaje/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public LenguajeProgramacion updateLenguaje( @Valid @PathVariable(value = "id") Long lengId, @RequestBody LenguajeProgramacion lenguaje ) {
		Log.log("Lenguaje con Id " + lengId + " actualizado.");
		return lenguajeService.actualizacionLenguajeProgramacion(lengId, lenguaje);
	}
	
	@ApiOperation(value="Eliminar Lenguaje de programacion ID", notes="Permite eliminar un Lenguaje de programacion mediante ID")
	@DeleteMapping(path = Constants.MAPPING_LENGUAJES+"/deletelenguaje/{id}") 
	public ResponseEntity<?> deleteLenguaje( @Valid @PathVariable(value = "id") Long lengId ) {
		lenguajeService.borrarLenguajeProgramacion(lengId);
		Log.log("Lenguaje con Id " + lengId + " eliminado.");
		return new ResponseEntity<>(new Mensaje("Lenguaje eliminado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus del lenguaje de programación", notes="Permite actualizar el lenguaje de programación mediante el ID")
	@PutMapping(path = Constants.MAPPING_LENGUAJES + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
	    lenguajeService.actualizaStatusLenguajeProgramacion(id, activo);
	    Log.log("Lenguaje con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}

}
