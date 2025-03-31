package com.coppel.sihe.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coppel.sihe.entity.CentroDevEntity;
import com.coppel.sihe.entity.Empleado;
import com.coppel.sihe.entity.Requerimiento;
import com.coppel.sihe.entity.RequerimientoAsignado;
import com.coppel.sihe.error.ResourceNotFoundException;
import com.coppel.sihe.repository.CentrosDevRepository;
import com.coppel.sihe.repository.EmpleadoRepository;
import com.coppel.sihe.repository.RequerimientoAsignadoRepository;
import com.coppel.sihe.repository.RequerimientoRepository;
import com.coppel.sihe.service.RequerimientoAsignadoService;

@Service
public class RequerimientoAsignadoServiceImpl implements RequerimientoAsignadoService{

	@Autowired
	RequerimientoAsignadoRepository reqAsignadoRepository;
	
	@Autowired
	RequerimientoRepository reqRepository;
	
	@Autowired
	EmpleadoRepository empRepository;
	
	@Autowired
	CentrosDevRepository cdRepository;
	
	@Override
	public int createRequerimientoAsignado(RequerimientoAsignado req) {	
		//RequerimientoAsignado mapperReqAsig = new ModelMapper().map(req, RequerimientoAsignado.class);
		return reqAsignadoRepository.createRequerimientoAsignado(req.getIdRequerimiento().getIdRequerimiento(), req.getIdEmpleado().getId(), req.getIdCentro().getId());
	}

	@Override
	public RequerimientoAsignado actualizacionRequerimientoAsignado(Long id, RequerimientoAsignado request) {
		RequerimientoAsignado reqAsigEntity = reqAsignadoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("RequerimientoAsignado", "id", id));
		Requerimiento reqEntity = reqRepository.findById(request.getIdRequerimiento().getIdRequerimiento())
				  .orElseThrow(() -> new ResourceNotFoundException("ReqAsignado-Requerimiento", "id", id));
		Empleado empEntity = empRepository.findById(request.getIdEmpleado().getId())
				  .orElseThrow(() -> new ResourceNotFoundException("ReqAsignado-Empleado", "id", id));
		CentroDevEntity cdEntity = cdRepository.findById(request.getIdCentro().getId())
				  .orElseThrow(() -> new ResourceNotFoundException("ReqAsignado-cd", "id", id));
		reqAsigEntity.setId(reqAsigEntity.getId());
		reqAsigEntity.setIdRequerimiento(reqEntity);
		reqAsigEntity.setIdEmpleado(empEntity);
		reqAsigEntity.setIdCentro(cdEntity);
		RequerimientoAsignado updatedReqAsig = reqAsignadoRepository.save(reqAsigEntity);
		return updatedReqAsig;

	}

	@Override
	public RequerimientoAsignado consultaRequerimientoAsignado(Long id) {
		RequerimientoAsignado reqAsigEntity = reqAsignadoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("RequerimientoAsignado", "id", id));		
		return reqAsigEntity;
	}

	@Override
	public Page<RequerimientoAsignado> consultaAllRequerimientoAsignados(Pageable pageable) {
		Page<RequerimientoAsignado> listReqAsigEntity = reqAsignadoRepository.findAll(pageable);
		if(listReqAsigEntity.isEmpty()) {
			new ResourceNotFoundException("RequerimientoAsignado", "id", 0);
		}
		return listReqAsigEntity;
	}

	@Override
	public ResponseEntity<?> borrarRequerimientoAsignado(Long id) {
		RequerimientoAsignado reqAsigEntity = reqAsignadoRepository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("RequerimientoAsignado", "id", id));
		reqAsignadoRepository.delete(reqAsigEntity);
		return ResponseEntity.ok().build();
	}

	@Override
	public List<RequerimientoAsignado> consultaRequerimientosAsignadosByIdEmpleado(Long idEmpleado) {
		List<RequerimientoAsignado> listRequerimientosAsignadosByIdEmpleado = reqAsignadoRepository.consultaRequerimientosAsignadosByIdEmpleado(idEmpleado);
		if(listRequerimientosAsignadosByIdEmpleado.size() == 0) {
			new ResourceNotFoundException("RequerimientoAsignado", "idEmpleado", 0);
		}
		return listRequerimientosAsignadosByIdEmpleado;
	}
	
	public void actualizaStatusRequerimientoAsignado(Long id, boolean isActivo) {
		reqAsignadoRepository.actualizaIsActivo(id, isActivo);
	}
	
}
