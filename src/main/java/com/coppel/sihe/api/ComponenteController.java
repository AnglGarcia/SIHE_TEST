package com.coppel.sihe.api;

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
import com.coppel.sihe.entity.Componente;
import com.coppel.sihe.service.ComponenteService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class ComponenteController {

	@Autowired 
	ComponenteService compService;


	@GetMapping(path = Constants.MAPPING_COMPONENTES+"/r")
	public String run() {
		return "Controller Componentes"+new String(Character.toChars(0x1F4A3));
	}
	@ApiOperation(value="Crear componente", notes="Permite crear un nuevo componente")
	@PostMapping(path = Constants.MAPPING_COMPONENTES+"/addcomponente",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createComponente(@RequestBody @Valid Componente request) {
		compService.createComponente(request);
		Log.log("Componente con id " + request.getDescripcion() + " creado.");
		return new ResponseEntity<>(new Mensaje("Componente creado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener componente ID", notes="Permite obtener un componente mediante ID")
	@GetMapping(path = Constants.MAPPING_COMPONENTES+"/consultacomp/{id}")
	public ResponseEntity<?> consultaComponenteId( @Valid @PathVariable(value = "id") Long compID ) {
		Log.log("Consulta componente con id " + compID);
		return compService.consultaComponente(compID);
	}
	
	@ApiOperation(value="Obtener componentes", notes="Permite obtener un listado de componentes")
	@GetMapping(path = Constants.MAPPING_COMPONENTES+"/consultalistacomp")
	public Page<Componente> consultaComponentes(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Componente> pagina = compService.consultaAllComponente(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar componente", notes="Permite actualizar un componente mediante ID")
	@PutMapping(path = Constants.MAPPING_COMPONENTES+"/updatecomp/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateComponente( @Valid @PathVariable(value = "id") Long compId, @RequestBody Componente componente ) {
		Log.log("Componente con id " + compId +" actualizado.");
		return compService.actualizacionComponente(compId, componente);
	}
	
	@ApiOperation(value="Eliminar componente", notes="Permite eliminar un componente mediante ID")
	@DeleteMapping(path = Constants.MAPPING_COMPONENTES+"/deletecomp/{id}") 
	public ResponseEntity<?> deleteComponente( @Valid @PathVariable(value = "id") Long compId ) {
		return compService.borrarComponente(compId);
	}
	
	@ApiOperation(value="Actualizar status de componente por ID", notes="Permite actualizar el status del componente mediante el ID")
	@PutMapping(path = Constants.MAPPING_COMPONENTES+"/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		return compService.actualizaStatus(id, activo);	
	}
	
}
