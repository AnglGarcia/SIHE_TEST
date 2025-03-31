package com.coppel.sihe.api;

import javax.validation.Valid;

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
import com.coppel.sihe.entity.CoordinacionDesarrollo;
import com.coppel.sihe.service.CoordinacionesDesarrolloService;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH + "/" + Constants.MAPPING_COORDINACIONDEV)
public class CoordinacionesDevController {
	
	@Autowired
	CoordinacionesDesarrolloService coordService;
	
	@GetMapping(path = "/r")
	public String run() {
		return "Controller coordinaciones desarrollo"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear Coordinacion desarrollo", notes="Permite crear un coordinacion desarrollo")
	@PostMapping(path ="/addcoordinaciondev",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCoordDev(@RequestBody @Valid CoordinacionDesarrollo request) {
		return coordService.createCoordinacionesDesarrollo(request);
			
	}
	
	@ApiOperation(value="Consulta coordinacion desarrollo ID", notes="Permite consultar una coordinacion mediante ID")
	@GetMapping(path = "/consultacoordinacion/{id}")
	public ResponseEntity<?> consultaCoordDevId( @Valid @PathVariable(value = "id") Long coordID ) {
		return coordService.consultaCoordinacionDesarrollo(coordID);
	}
	
	@ApiOperation(value="Consulta coordinaciones desarrollo", notes="Permite obtener un listado con las coordinaciones de desarrollo")
	@GetMapping(path = "/consultalistacoordinacionesdev")
	public Page<CoordinacionDesarrollo> consultaCoordinaciones(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<CoordinacionDesarrollo> pagina = coordService.consultaAllCoordinacionesDesarrollo(pageable);
		return pagina;
	}
	
	@ApiOperation(value="Actualizar coordinacion desarrollo", notes="Permite actualizar una coordinacion de desarrollo mediante ID")
	@PutMapping(path = "/updatecoorddev/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCoordinacion( @Valid @PathVariable(value = "id") Long coordId, @RequestBody CoordinacionDesarrollo coordDev ) {
		return coordService.actualizacionCoordinacionesDesarrollo(coordId, coordDev);
	}
	
	@ApiOperation(value="Eliminar coordinacion desarrollo", notes="Permite eliminar una coordinacion de desarrollo mediante ID")
	@DeleteMapping(path = "/deletecoordinaciondev/{id}") 
	public ResponseEntity<?> deleteCoordinacion( @Valid @PathVariable(value = "id") Long coordId ) {
		return coordService.borrarCoordinacionesDesarrollo(coordId);
	}
	
	@ApiOperation(value="Actualizar status de coordinacion de desarrollo por ID", notes="Permite actualizar el status de la coordinacion de desarrollo mediante el ID")
	@PutMapping(path = "/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		return coordService.actualizaStatus(id, activo);
	}

}
