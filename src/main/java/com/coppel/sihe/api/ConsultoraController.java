package com.coppel.sihe.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.coppel.sihe.constants.Constants;
import com.coppel.sihe.entity.Consultora;
import com.coppel.sihe.service.ConsultoraService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH+"/"+Constants.MAPPING_CONSULTORA)
public class ConsultoraController {

	@Autowired 
	ConsultoraService consService;


	@GetMapping("/r")
	public String run() {
		return "Controller Consultora " + new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear consultora", notes="Permite crear una nueva consultora")
	@PostMapping(path = "/addconsultora",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createConsultora(@RequestBody @Valid Consultora request) {
		return consService.createConsultora(request);
	}
	
	@ApiOperation(value="Obtener consultora ID", notes="Permite obtener una consultora mediante ID")
	@GetMapping(path = "/consultacomp/{id}")
	public ResponseEntity<?> consultaConsultoraId( @Valid @PathVariable(value = "id") Long consID ) {
		return consService.consultaConsultora(consID);
	}
	
	@ApiOperation(value="Obtener consultoras", notes="Permite obtener un listado de consultoras")
	@GetMapping(path = "/consultaConsultoras")
	public Page<Consultora> consultaComponentes(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Consultora> pagina = consService.consultaAllConsultora(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar consultora", notes="Permite actualizar una consultora mediante ID")
	@PutMapping(path = "/updatecomp/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateConsultoraId( @Valid @PathVariable(value = "id") Long consId, @RequestBody Consultora componente ) {
		return consService.actualizacionConsultora(consId, componente);
	}
	
	@ApiOperation(value="Eliminar consultora", notes="Permite eliminar una consultora mediante ID")
	@DeleteMapping(path = "/deletecomp/{id}") 
	public ResponseEntity<?> deleteConsultoraId( @Valid @PathVariable(value = "id") Long consId ) {
		return consService.borrarConsultora(consId);
	}
	
	@ApiOperation(value="Actualizar status de una consultora por ID", notes="Permite actualizar el status de una consultora mediante el ID")
	@PutMapping(path = "/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		return consService.actualizaStatusConsultora(id, activo);	
	}
	
}