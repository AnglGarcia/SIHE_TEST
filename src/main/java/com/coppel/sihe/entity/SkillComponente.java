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
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "skill_componente_empleado")
@Table(name = "skill_componente_empleado")
public class SkillComponente {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(targetEntity = Empleado.class)
	@JoinColumn(name="id_empleado")
	@NotNull
	private Empleado idEmpleado;
	
	@ManyToOne(targetEntity = Componente.class)
	@JoinColumn(name="id_componente")
	@NotNull
	private Componente idComponente;
	
	
	@ManyToOne(targetEntity = Ponderacion.class)
	@JoinColumn(name="id_ponderacion")
	@NotNull
	private Ponderacion idPonderacion;
	
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
