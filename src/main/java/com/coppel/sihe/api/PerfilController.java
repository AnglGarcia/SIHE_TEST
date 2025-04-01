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
import com.coppel.sihe.entity.Perfil;
import com.coppel.sihe.service.PerfilService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class PerfilController {

	
	@Autowired
	PerfilService perfilService;
	
	@GetMapping(path = Constants.MAPPING_PERFILES+"/r")
	public String run() {
		return "Controller Perfiles"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear perfil", notes="Permite crear un perfil")
	@PostMapping(path = Constants.MAPPING_PERFILES+"/addperfil",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPerfil(@RequestBody @Valid Perfil request) {
		perfilService.createPerfil(request);
		Log.log("Perfil con Id " + request.getDescripcion() + " creado.");
		return new ResponseEntity<>(new Mensaje("Perfil creado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener perfil ID", notes="Permite obtener un perfil mediante ID")
	@GetMapping(path = Constants.MAPPING_PERFILES+"/consultaperfil/{id}")
	public Perfil consultaPerfil( @Valid @PathVariable(value = "id") Long perfilID ) {
		Log.log("Perfil con Id " + perfilID + " obtenido.");
		return perfilService.consultaPerfil(perfilID);
	}
	
	@ApiOperation(value="Obtener perfiles", notes="Permite obtener un listado de perfiles")
	@GetMapping(path = Constants.MAPPING_PERFILES+"/consultaperfiles")
	public Page<Perfil> consultaPerfiles(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Perfil> pagina = perfilService.consultaAllPerfil(pageable);
		return pagina;
	
	}
	
	@ApiOperation(value="Actualizar perfil ID", notes="Permite actualizar un perfil mediante ID")
	@PutMapping(path = Constants.MAPPING_PERFILES+"/updateperfil/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePerfil(@PathVariable(value = "id") Long perfilId,@Valid @RequestBody Perfil perfil) {
		Perfil updP = perfilService.actualizacionPerfil(perfilId, perfil);
		Log.log("Perfil con Id " + perfilId + " actualizado.");
		return new ResponseEntity<>(updP, HttpStatus.OK);
	}
	
	@ApiOperation(value="Eliminar perfil ID", notes="Permite eliminar un perfi mediante ID")
	@DeleteMapping(path = Constants.MAPPING_PERFILES+"/deleteperfil/{id}") 
	public ResponseEntity<?> deletePerfil( @Valid @PathVariable(value = "id") Long perfilId ) {
		perfilService.borrarPerfil(perfilId);
		Log.log("Perfil con Id " + perfilId + " eliminado.");
		return new ResponseEntity<>(new Mensaje("Perfil eliminado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus del perfil", notes="Permite actualizar el perfil mediante el ID")
	@PutMapping(path = Constants.MAPPING_PERFILES + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
		perfilService.actualizaStatusPerfil(id, activo);
		Log.log("Estatus de perfil con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}
	
}
