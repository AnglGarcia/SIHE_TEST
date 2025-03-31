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
import com.coppel.sihe.entity.SkillLenguaje;
import com.coppel.sihe.service.SkillLenguajeService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class SkillLenguajeController {

	@Autowired
	SkillLenguajeService skLengService;
	
	@GetMapping(path = Constants.MAPPING_SKILL_LENG+"/r")
	public String run() {
		return "Controller Skill Lenguaje"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear Skill por Lenguaje-empleado", notes="Permite crear skill por lenguaje y empleado")
	@PostMapping(path = Constants.MAPPING_SKILL_LENG+"/addskilleng",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createSkLeng(@RequestBody @Valid SkillLenguaje request) {
		skLengService.createSkillLenguaje(request);
		Log.log("Habilidad por lenguaje y empleado creada.");
		return new ResponseEntity(new Mensaje("Habilidad asignada al empleado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener Skill por Lenguaje-empleado ID", notes="Permite obtener skill por lenguaje y empleado mediante ID")
	@GetMapping(path = Constants.MAPPING_SKILL_LENG+"/consultaskilleng/{id}")
	public SkillLenguaje consultaSkLeng( @Valid @PathVariable(value = "id") Long skLengID ) {
		Log.log("Habilidad por lenguaje y empleado con Id " + skLengID + " obtenida.");
		return skLengService.consultaSkillLenguaje(skLengID);
	}
	
	@ApiOperation(value="Obtener Skills por empleado ID", notes="Permite obtener skills por lenguaje  mediante ID de empleado")
	@GetMapping(path = Constants.MAPPING_SKILL_LENG+"/consultaskillengByIdEmpleado/{idEmpleado}")
	public List<SkillLenguaje> consultaSkLenguajesByIdEmpleado( @Valid @PathVariable(value = "idEmpleado") Long idEmpleado ) {
		Log.log("Habilidad por lenguaje y empleado con Id " + idEmpleado + " obtenida.");
		return skLengService.consultaSkillLenguajeByIdEmpleado(idEmpleado);
	}
	
	@ApiOperation(value="Obtener Skills por Lenguaje-empleado", notes="Permite obtener un listado de skills por lenguaje y empleado")
	@GetMapping(path = Constants.MAPPING_SKILL_LENG+"/consultalistskillengs")
	public Page<SkillLenguaje> consultaSkLenguajes(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<SkillLenguaje> pagina = skLengService.consultaAllSkillLenguajes(pageable);
		return pagina;
	}
	
	@ApiOperation(value="Actualizar Skill por Lenguaje-empleado ID", notes="Permite actualizar skill por lenguaje y empleado mediante ID")
	@PutMapping(path = Constants.MAPPING_SKILL_LENG+"/updateskilleng/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public SkillLenguaje updateSkillLeng( @Valid @PathVariable(value = "id") Long skLengId, @RequestBody SkillLenguaje skLeng) {
		Log.log("Habilidad por lenguaje y empleado con Id " + skLengId + " actualizada.");
		return skLengService.actualizacionSkillLenguaje(skLengId, skLeng);
	}
	
	@ApiOperation(value="Eliminar Skill por Lenguaje-empleado ID", notes="Permite eliminar skill por lenguaje y empleado mediante ID")
	@DeleteMapping(path = Constants.MAPPING_SKILL_LENG+"/deleteskilleng/{id}") 
	public ResponseEntity<?> deleteSkillLeng( @Valid @PathVariable(value = "id") Long skLengId ) {
		skLengService.borrarSkillLenguaje(skLengId);
		Log.log("Habilidad por lenguaje y empleado con Id " + skLengId + " eliminada.");
		return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualizar status de la habilidad en lenguaje por ID", notes="Permite actualizar el status de la habilidad en un lenguaje por empleado mediante el ID")
	@PutMapping(path = Constants.MAPPING_SKILL_LENG+"/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		skLengService.actualizaStatus(id, activo);
		Log.log("Estatus de habilidad por lenguaje y empleado con Id " + id + " actualizado.");
		return new ResponseEntity(new Mensaje("Estatus actualizado"), HttpStatus.OK);	
	}
	
	
}
