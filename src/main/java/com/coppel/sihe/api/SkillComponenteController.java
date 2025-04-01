package com.coppel.sihe.api;

import java.util.List;

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
import com.coppel.sihe.entity.SkillComponente;
import com.coppel.sihe.service.SkillComponenteService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class SkillComponenteController {

	@Autowired
	SkillComponenteService skCompService;
	
	@GetMapping(path = Constants.MAPPING_SKILL_COMP+"/r")
	public String run() {
		return "Controller Skill Componente"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear Skill por Componente-empleado", notes="Permite crear skill por componente y empleado")
	@PostMapping(path = Constants.MAPPING_SKILL_COMP+"/addskillcomp",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createSkComp(@RequestBody @Valid SkillComponente request) {
		skCompService.createSkillComponente(request);
		Log.log("Habilidad por componente y empleado con id " + request.getId());
		return new ResponseEntity<>(new Mensaje("Habilidad asignada al empleado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener Skill por Componente-empleado ID", notes="Permite obtener skill por componente y empleado mediante ID")
	@GetMapping(path = Constants.MAPPING_SKILL_COMP+"/consultaskillcomp/{id}")
	public SkillComponente consultaSkComp( @Valid @PathVariable(value = "id") Long reqSkID ) {
		Log.log("Habilidad por componente y empleado con Id " + reqSkID + " obtenida.");
		return skCompService.consultaSkillComponente(reqSkID);
	}
	
	@ApiOperation(value="Obtener Skills por Componente-empleado", notes="Permite obtener un listado de skills por componente y empleado")
	@GetMapping(path = Constants.MAPPING_SKILL_COMP+"/consultalistskillcomps")
	public Page<SkillComponente> consultaSkComponentes(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<SkillComponente> pagina = skCompService.consultaAllSkillComponentes(pageable);
		return pagina;
	}
	
	@ApiOperation(value="Obtener Skills por empleado ID", notes="Permite obtener skills por componente mediante ID de empleado")
	@GetMapping(path = Constants.MAPPING_SKILL_COMP+"/consultaskillcompByIdEmpleado/{idEmpleado}")
	public List<SkillComponente> consultaSkComponentesByIdEmpleado( @Valid @PathVariable(value = "idEmpleado") Long idEmpleado ) {
		Log.log("Habilidad por componente y empleado con Id de empleado " + idEmpleado + " obtenida.");
		return skCompService.consultaSkillComponenteByIdEmpleado(idEmpleado);
	}
	
	@ApiOperation(value="Actualizar Skill por Componente-empleado ID", notes="Permite actualizar skill por componente y empleado mediante ID")
	@PutMapping(path = Constants.MAPPING_SKILL_COMP+"/updateskillcomp/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public SkillComponente updateSkillComp( @Valid @PathVariable(value = "id") Long skCompId, @RequestBody SkillComponente skComp) {
		Log.log("Habilidad por componente y empleado con Id " + skCompId + " actualizada.");
		return skCompService.actualizacionSkillComponente(skCompId, skComp);
	}
	
	@ApiOperation(value="Eliminar Skill por Componente-empleado id", notes="Permite eliminar skill por componente y empleado mediante ID")
	@DeleteMapping(path = Constants.MAPPING_SKILL_COMP+"/deleteskillcomp/{id}") 
	public ResponseEntity<?> deleteSkComp( @Valid @PathVariable(value = "id") Long skCompId) {
		skCompService.borrarSkillComponente(skCompId);
		Log.log("Habilidad por componente y empleado con Id " + skCompId + " eliminada.");
		return new ResponseEntity<>(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus de skill componente empleado", notes="Permite actualizar el skill componente empleado mediante el ID")
	@PutMapping(path = Constants.MAPPING_SKILL_COMP + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
		skCompService.actualizaStatusSkillComponente(id, activo);
		Log.log("Estatus de habilidad por componente y empleado con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}
}
