package com.coppel.sihe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.coppel.sihe.entity.RequerimientoAsignado;

public interface RequerimientoAsignadoService {

	public int createRequerimientoAsignado(RequerimientoAsignado request);
	RequerimientoAsignado actualizacionRequerimientoAsignado(Long id, RequerimientoAsignado request);
	List<RequerimientoAsignado> consultaRequerimientosAsignadosByIdEmpleado(Long idEmpleado);
	RequerimientoAsignado consultaRequerimientoAsignado(Long id);
	Page<RequerimientoAsignado> consultaAllRequerimientoAsignados(Pageable pageable);
	ResponseEntity<?> borrarRequerimientoAsignado(Long id);
	public void actualizaStatusRequerimientoAsignado(Long id, boolean isActivo);
}
