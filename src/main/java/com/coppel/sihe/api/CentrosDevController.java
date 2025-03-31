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
import com.coppel.sihe.entity.CentroDevEntity;
import com.coppel.sihe.service.CentrosDevService;

import io.swagger.annotations.ApiOperation;


@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class CentrosDevController {
	
	@Autowired 
	CentrosDevService cdev;


	@GetMapping(path = Constants.MAPPING_CENTROSDEV+"/r")
	public String run() {
		return "Controller Centros de Desarrollo Main "+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear Centro Desarrollo", notes="Permite crear un centro de desarrollo")
	@PostMapping(path = Constants.MAPPING_CENTROSDEV+"/addcentrosdev",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCentrosDev(@RequestBody @Valid CentroDevEntity request) {
		return cdev.createCentrosDesarrollo(request);
	}
	
	@ApiOperation(value="Obtener Centro Desarrollo ID", notes="Permite obtener un centro de desarrollo mediante ID")
	@GetMapping(path = Constants.MAPPING_CENTROSDEV+"/consultacentrosdev/{id}")
	public ResponseEntity<?> consultaCentrosDev( @Valid @PathVariable(value = "id") int centDevId ) {
		return cdev.consultaCentroDesarrollo(centDevId);
	}
	
	@ApiOperation(value="Obtener centros desarrollo", notes="Permite obtener un listado de los centros de desarrollo")
	@GetMapping(path = Constants.MAPPING_CENTROSDEV+"/consultacentrosdev")
	public Page<CentroDevEntity> consultaCentrosDev(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<CentroDevEntity> pagina = cdev.consultaAllCentrosDesarrollo(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar centro desarrollo por ID", notes="Permite actualizar un centro de desarrollo mediante el ID")
	@PutMapping(path = Constants.MAPPING_CENTROSDEV+"/updatecentrosdev/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCentrosDev( @Valid @PathVariable(value = "id") int centDevId, @RequestBody CentroDevEntity centrosDevEntity ) {
		return cdev.actualizacionCentrosDesarrollo(centDevId, centrosDevEntity);
	}
	
	@ApiOperation(value="Eliminar centro de desarrollo", notes="Permite eliminar un centro de desarrollo mediante ID")
	@DeleteMapping(path = Constants.MAPPING_CENTROSDEV+"/deletecentrosdev/{id}") 
	public ResponseEntity<?> deleteCentrosDev( @Valid @PathVariable(value = "id") int centDevId ) {
		return cdev.borrarCentrosDev(centDevId);
	}
	
	@ApiOperation(value="Actualizar centro desarrollo por ID", notes="Permite actualizar el status de centro de desarrollo mediante el ID")
	@PutMapping(path = Constants.MAPPING_CENTROSDEV+"/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") int id, @PathVariable(value="activo") Boolean activo){
		return cdev.actualizaStatus(id, activo);
	}
	

}
