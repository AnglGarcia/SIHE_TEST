package com.coppel.sihe.service.implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.CentroDevEntity;
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.entity.Perfil;
import com.coppel.sihe.error.EmpleadoNotFoundException;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.CentrosDevRepository;
import com.coppel.sihe.repository.EmpleadoRepository;
import com.coppel.sihe.repository.PerfilRepository;
import com.coppel.sihe.service.EmpleadoService;
import com.coppel.sihe.util.Cifrado;
import com.coppel.sihe.util.Log;



@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	CentrosDevRepository centroRepository;
	
	@Autowired
	PerfilRepository perfilRepository;
	
	@Override
	public Page<Empleado> fetchEmpleadoList(Pageable page) {
		return empleadoRepository.findAll(page);
	}
	
	@Override
	public Empleado fetchEmpleadoByIdAndPassword(Long id, String password) throws EmpleadoNotFoundException {
		return empleadoRepository.findEmpleadoByIdAndPassword(id, password)
				.orElseThrow(() ->	new EmpleadoNotFoundException("Empleado no disponible"));
	}
	
	@Override
	public Empleado createEmpleado(Empleado req) {	
		Empleado mapperEmpleado = new ModelMapper().map(req, Empleado.class);
		Log.log("Empleado con Id " + req.getId() + " creado.");
		mapperEmpleado.setPassword(Cifrado.encriptar(mapperEmpleado.getPassword()));
		return empleadoRepository.save(mapperEmpleado);
	}
	
	@Override
	public Empleado actualizacionEmpleado(Long id, Empleado request) {
		CentroDevEntity cntroEmpleado = centroRepository.findById(request.getIdCentro().getId())
				.orElseThrow(() -> new ResourceNotFoundException("CentroDesarrollo-Empleado", "id", request.getIdCentro().getId()));
		Perfil perfilEmpl = perfilRepository.findById(request.getIdPerfil().getIdPerfil())
				.orElseThrow(() -> new ResourceNotFoundException("Perfil-Empleado", "id", request.getIdPerfil().getIdPerfil()));
		Empleado empEntity = empleadoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", id));
		empEntity.setId(empEntity.getId());
		empEntity.setPrimerNombre(request.getPrimerNombre());
		empEntity.setSegundoNombre(request.getSegundoNombre());
		empEntity.setApellidoPaterno(request.getApellidoPaterno());
		empEntity.setApellidoMaterno(request.getApellidoMaterno());
		empEntity.setIdCentro(cntroEmpleado);
		empEntity.setIdPerfil(perfilEmpl);
		empEntity.setLugarPosicion(request.getLugarPosicion());
		empEntity.setExterno(request.isExterno());
		empEntity.setCorreo(request.getCorreo());
		empEntity.setPassword(Cifrado.encriptar(request.getPassword()));
		Empleado updatedEmp = empleadoRepository.save(empEntity);
		Log.log("Empleado con Id " + id + " actualizado.");
		return updatedEmp;
	}
	
	@Override
	public Empleado consultaEmpleado(Long id) {
		Empleado empEntity = empleadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", id));		
		return empEntity;
		
	}

	@Override
	public List<Empleado> consultaAllEmpleado() {
		List<Empleado> listEmpEntity = empleadoRepository.findAll();
		if(listEmpEntity.size() == 0) {
			Log.log("No hay empleados para consultar");
			new ResourceNotFoundException("Empleado", "id", 0);
		}
			
		return listEmpEntity;
	}

	@Override
	public ResponseEntity<?> borrarEmpleado(Long id) {
		Empleado empEntity = empleadoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("Empleado", "id", id));
		empleadoRepository.delete(empEntity);
		Log.log("Empleado con Id " + id + " eliminado.");
		return ResponseEntity.ok().build();
	}

	@Override
	public Empleado consultaEmpleadoByCorreo(String correo) throws EmpleadoNotFoundException {
		return empleadoRepository.findEmpleadoByCorreo(correo)
				.orElseThrow(() -> new EmpleadoNotFoundException("Empleado no disponible"));
	}

	@Override
	public boolean existsById(Long id) {
		return empleadoRepository.existsById(id);
	}

	@Override
	public boolean existsByCorreo(String correo) {
		return empleadoRepository.existsByCorreo(correo);
	}

	@Override
	public List<Empleado> consultaEmpleadoByPerfilAndCentro(Long idPerfil, Long idCentro) {
		List<Empleado> listEmpleadoByIdPerfilAndIdCentro = empleadoRepository.consultaEmpleadoByPerfilAndCentro(idPerfil, idCentro);
		if(listEmpleadoByIdPerfilAndIdCentro.size() == 0) {
			Log.log("No se encontraron empleados con perfil " + idPerfil + " y centro " + idCentro);
			new ResourceNotFoundException("SkillLenguaje", "idEmpleado", 0);
		}
		return listEmpleadoByIdPerfilAndIdCentro;
	}

	@Override
	public List<Empleado> consultaEmpleadoByPerfil(Long idPerfil) {
		List<Empleado> listEmpleadoByIdPerfil = empleadoRepository.consultaEmpleadoByPerfil(idPerfil);
		if(listEmpleadoByIdPerfil.size() == 0) {
			Log.log("No se encontró empleado con perfil " + idPerfil);
			new ResourceNotFoundException("SkillLenguaje", "idEmpleado", 0);
		}
		return listEmpleadoByIdPerfil;
	}

	@Override
	public List<Empleado> consultaEmpleadoByCentro(Long idCentro) {
		List<Empleado> listEmpleadoByIdCentro = empleadoRepository.consultaEmpleadoByCentro(idCentro);
		if(listEmpleadoByIdCentro.size() == 0) {
			Log.log("No se encontró empleado con centro " + idCentro);
			new ResourceNotFoundException("SkillLenguaje", "idEmpleado", 0);
		}
		return listEmpleadoByIdCentro;
	}
	
	@Override
	public void actualizaStatus(Long id, boolean isActivo) {
		empleadoRepository.actualizaIsActivo(id, isActivo);
		Log.log("Estatus del empleado con Id " + id + " actualizado.");
	}
	
}
