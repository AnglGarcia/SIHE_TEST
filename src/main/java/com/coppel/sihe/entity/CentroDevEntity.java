package com.coppel.sihe.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "centro_desarrollo")
@Table(name = "centro_desarrollo")
public class CentroDevEntity {
	
	@Id
	@Column(name = "id") 
	@NotNull
	private int id;
	
	@Column(name = "descripcion", length=50)
	@NotNull
	private String descripcion;
	
	@ManyToOne(targetEntity = CoordinacionDesarrollo.class)
	@JoinColumn(name = "id_coordinacion_desarrollo") 
	private CoordinacionDesarrollo idCoordinacionDesarrollo;
	
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
