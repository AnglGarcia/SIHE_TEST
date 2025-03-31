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
import com.coppel.sihe.entity.RequerimientoAsignado; 
import com.coppel.sihe.service.RequerimientoAsignadoService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;

@RestController
@Validated
@RequestMapping(value = Constants.BASE_PATH)
public class RequerimientoAsignadoController {
	
	@Autowired
	RequerimientoAsignadoService reqAsigService;
	
	@GetMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/r")
	public String run() {
		return "Controller Requerimientos Asignados"+new String(Character.toChars(0x1F4A3));
	}
	
	@ApiOperation(value="Crear requerimiento asignado", notes="Permite crear un requerimiento asignado al empleado")
	@PostMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/addreqasignado",
				 produces = MediaType.APPLICATION_JSON_VALUE,
				 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createReqAsignado(@RequestBody @Valid RequerimientoAsignado request) {
		reqAsigService.createRequerimientoAsignado(request);
		Log.log("Requerimiento asignado creado.");
		return new ResponseEntity<>(new Mensaje("Requerimiento asignado al empleado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Obtener requerimiento asignado ID", notes="Permite obtner un requerimiento asignado al empleado mediante ID")
	@GetMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/consultareqasignado/{id}")
	public RequerimientoAsignado consultaReqAsignado( @Valid @PathVariable(value = "id") Long reqAsID ) {
		Log.log("Requerimiento asignado con Id " + reqAsID + " obtenido.");
		return reqAsigService.consultaRequerimientoAsignado(reqAsID);
	}
	
	@ApiOperation(value="Obtener requerimientos asignados", notes="Permite obtener un listado de requerimientos asignados")
	@GetMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/consultalistreqasignados")
	public Page<RequerimientoAsignado> consultaReqAsignados(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<RequerimientoAsignado> pagina = reqAsigService.consultaAllRequerimientoAsignados(pageable);
		return pagina;
	}
	
	
	@ApiOperation(value="Obtener requerimientos por empleado ID", notes="Permite obtener los requerimientos asignados mediante ID de empleado")
	@GetMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/consultaslistreqasignadosByIdEmpleado/{idEmpleado}")
	public List<RequerimientoAsignado> consultaRequerimientosAsignadosByIdEmpleado( @Valid @PathVariable(value = "idEmpleado") Long idEmpleado ) {
		Log.log("Requerimientos asignado con Id de empleado " + idEmpleado + " obtenidos.");
		return reqAsigService.consultaRequerimientosAsignadosByIdEmpleado(idEmpleado);
	}
	
	@ApiOperation(value="Actualizar requerimiento asignado ID", notes="Permite actualizar un requerimiento asignado al empleado mediante ID")
	@PutMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/updatereqasignado/{id}",
			 produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public RequerimientoAsignado updateReqAsignados( @Valid @PathVariable(value = "id") Long reqAsId, @RequestBody RequerimientoAsignado reqAsig) {
		Log.log("Requerimiento asignado con Id " + reqAsId + " actualizado.");
		return reqAsigService.actualizacionRequerimientoAsignado(reqAsId, reqAsig);
	}
	
	
	@ApiOperation(value="Eliminar requerimiento asignado", notes="Permite eliminar un requerimiento asignado al empleado mediante ID")
	@DeleteMapping(path = Constants.MAPPING_REQ_ASIGNADOS+"/deletereqasignado/{id}") 
	public ResponseEntity<?> deleteReqAsignado( @Valid @PathVariable(value = "id") Long reqAsId ) {
		reqAsigService.borrarRequerimientoAsignado(reqAsId);
		Log.log("Requerimiento asignado con Id " + reqAsId + " eliminado.");
		return new ResponseEntity<>(new Mensaje("Requerimiento eliminado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualiza el estatus de los requerimientos asignados", notes="Permite actualizar los requerimientos asignados mediante el ID")
	@PutMapping(path = Constants.MAPPING_REQ_ASIGNADOS + "/actualizarStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsActivo(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo) {
		reqAsigService.actualizaStatusRequerimientoAsignado(id, activo);
		Log.log("Estatus de requerimiento asignado con Id " + id + " actualizado.");
	    return new ResponseEntity<>(new Mensaje("Estatus Actualizado"), HttpStatus.OK);
	}
}
