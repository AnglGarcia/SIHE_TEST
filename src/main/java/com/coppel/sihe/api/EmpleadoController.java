package com.coppel.sihe.api;

import java.security.MessageDigest;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.service.EmpleadoService;
import com.coppel.sihe.util.Log;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(value = Constants.BASE_PATH)
public class EmpleadoController {
	
	@Autowired
	private EmpleadoService empleadoService;  
	
	@ApiOperation(value="Obtener empleados", notes="Permite obtener un listado de los empleados")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleados")
	public Page<Empleado> fetchEmpleadoList(@RequestParam(value = "page") int page, @RequestParam(value = "size") int pSize){
		Pageable pageable = PageRequest.of(page, pSize);
		Page<Empleado> pagina = empleadoService.fetchEmpleadoList(pageable);
		Log.log("Total paginas catalogo empleados: " + pagina.getTotalPages());
		return pagina;
	}
	
	
	@ApiOperation(value="Obtener empleado por ID", notes="Permite obtener un empleado mediante su ID")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleado/{id}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public Empleado consultaEmpleado(@Valid @PathVariable(value = "id") Long id){
		Log.log("Empleado con Id " + id + " Obtenido.");
		return empleadoService.consultaEmpleado(id);
	}
	
	@ApiOperation(value="Obtener empleados por correo", notes="Permite obtener un empleado mediante su correo")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadocorreo/{correo}")
	public Empleado consultaEmpleadoByCorreo(@Valid @PathVariable(value = "correo") String correo) throws EmpleadoNotFoundException{
		Log.log("Empleado con correo " + correo + " Obtenido.");
		return empleadoService.consultaEmpleadoByCorreo(correo);
	}
	
	@ApiOperation(value="Obtener empleado por ID perfil, ID centro", notes="Permite obtener Empleados por ID de perfil, ID centro")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadosByIdPerfilAndIdCentro/{idPerfil},{idCentro}")
	public List<Empleado> consultaEmpleadosByIdPerfilAndIdCentro( @Valid @PathVariable(value = "idPerfil") Long idPerfil, @Valid @PathVariable(value = "idCentro") Long idCentro ) {
		Log.log("Empleado con Id perfil " + idPerfil + " y id centro " + idCentro + " Obtenido.");
		return empleadoService.consultaEmpleadoByPerfilAndCentro(idPerfil, idCentro);
	}
	
	@ApiOperation(value="Obtener empleado por ID perfil", notes="Permite obtener Empleados por ID de perfil")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadosByIdPerfil/{idPerfil}")
	public List<Empleado> consultaEmpleadosByIdPerfil( @Valid @PathVariable(value = "idPerfil") Long idPerfil) {
		Log.log("Empleado con id perfil " + idPerfil + " Obtenido.");
		return empleadoService.consultaEmpleadoByPerfil(idPerfil);
	}
	
	@ApiOperation(value="Obtener empleado por ID centro", notes="Permite obtener Empleados por ID de centro")
	@GetMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadosByIdCentro/{idCentro}")
	public List<Empleado> consultaEmpleadosByIdCentro( @Valid @PathVariable(value = "idCentro") Long idCentro) {
		Log.log("Empleado con id centro " + idCentro + " Obtenido.");
		return empleadoService.consultaEmpleadoByCentro(idCentro);
	}
	
	@ApiOperation(value="Crear empleado", notes="Permite crear un empleado")
	@PostMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadossave",
			produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mensaje> saveEmpleado(@Valid @RequestBody Empleado empleado) {
		if (empleadoService.existsByCorreo(empleado.getCorreo())) {
			Log.log("Correo de empleado ya existente.");
            return new ResponseEntity<Mensaje>(new Mensaje("El Correo del empleado ya existe"), HttpStatus.METHOD_NOT_ALLOWED);
		}
		if (empleadoService.existsById(empleado.getId())) {
			Log.log("Numero de empleado ya existente.");
            return new ResponseEntity(new Mensaje("El Número de empleado ya existe"), HttpStatus.NOT_FOUND);
		}
			Log.log("Creando empleado con Id " + empleado.getId());
			empleadoService.createEmpleado(empleado);
			return new ResponseEntity(new Mensaje("Empleado creado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualizar empleado", notes="Permite realizar la actualización de un empleado mediante el ID")
	@PutMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadosupdate/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE,
			 consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmpleado(@PathVariable("id") Long id,
		@Valid @RequestBody Empleado empleado) {
			Log.log("Actualizando empleado con Id " + id);
			Empleado auxEmp = empleadoService.actualizacionEmpleado(id, empleado);
			return new ResponseEntity<>(auxEmp, HttpStatus.OK);
	}
	
	@ApiOperation(value="Eliminar empleado", notes="Permite eliminar un empleado mediante el ID")
	@DeleteMapping(path = Constants.MAPPING_EMPLEADOS+"/empleadosdelete/{id}")
	public ResponseEntity<?> deleteEmpleadoById( @Valid @PathVariable(value = "id") Long id ) {
		Log.log("Eliminando empleado con Id " + id);
		empleadoService.borrarEmpleado(id);
		return new ResponseEntity(new Mensaje("Empleado eliminado"), HttpStatus.OK);
	}
	
	@ApiOperation(value="Actualizar status del empleado por ID", notes="Permite actualizar el status del empleado mediante el ID")
	@PutMapping(path = Constants.MAPPING_EMPLEADOS+"/actualizaStatus/{id}/{activo}")
	public ResponseEntity<?> actualizaIsEnabled(@Valid @PathVariable(value="id") Long id, @PathVariable(value="activo") Boolean activo){
		Log.log("Actualizando estatus del empleado con Id " + id);
		empleadoService.actualizaStatus(id, activo);
		return new ResponseEntity(new Mensaje("Estatus actualizado"), HttpStatus.OK);	
	}
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "/menu";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);               
            md5 = bytesToHex(md5Byte);                             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
	
	public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
	
}
