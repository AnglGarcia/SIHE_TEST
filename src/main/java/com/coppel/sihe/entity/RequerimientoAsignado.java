package com.coppel.sihe.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "requerimiento_asignado")
@Table(name = "requerimiento_asignado")
public class RequerimientoAsignado {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(targetEntity = Requerimiento.class)
	@JoinColumn(name="id_requerimiento")
	@NotNull
	private Requerimiento idRequerimiento;
	
	@ManyToOne(targetEntity = Empleado.class)
	@JoinColumn(name="id_empleado")
	@NotNull
	private Empleado idEmpleado;
	
	@ManyToOne(targetEntity = CentroDevEntity.class)
	@JoinColumn(name="id_centro")
	@NotNull
	private CentroDevEntity idCentro;
	
	@Column(name = "activo")
	@NotNull
	private boolean isActivo = true;
	
	@Column(name = "usuario_insert")
	private Long usuarioInsert = 111111111L;
	
	@Column(name = "usuario_update")
	private Long usuarioUpdate;
	
	@Column(name = "fecha_insert")
	private Date fechaInsert = Calendar.getInstance().getTime();
	
	@Column(name = "fecha_update")
	private Date fechaUpdate;
	
}
