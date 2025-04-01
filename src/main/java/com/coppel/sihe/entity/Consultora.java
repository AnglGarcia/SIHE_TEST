package com.coppel.sihe.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "consultora")
@Table(name = "consultora")
public class Consultora {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultora_seq")
	@SequenceGenerator(name = "consultora_seq", sequenceName = "consultora_seq", allocationSize = 1)
	private Long id;

	@Column(name = "descripcion", length=50)
	@NotNull
	private String descripcion;

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
