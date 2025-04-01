package com.coppel.sihe.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity(name = "empleado")
@Table(name = "empleado")
public class Empleado {

	@Id
	@Column(name="id")
	@NotNull
	private Long id;
	
	@Column(name = "primer_nombre", length=30)
	@NotNull
	private String primerNombre;

	@Column(name = "segundo_nombre", length=30)
	private String segundoNombre;
	
	@Column(name = "apellido_paterno", length=30)
	@NotNull
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno", length=30)
	private String apellidoMaterno;

	@ManyToOne(targetEntity = CentroDevEntity.class)
	@JoinColumn(name ="id_centro")
	private CentroDevEntity idCentro;
	
	@ManyToOne(targetEntity = Perfil.class)
	@JoinColumn(name ="id_perfil")
	private Perfil idPerfil;
	
	@Column(name = "lugar_posicion", length=30)
	private String lugarPosicion;
	
	@Column(name = "externo")
	@NotNull
	private boolean isExterno = false;
	
	@Column(name = "correo", length=40)
	@NotNull
	@Email(message="Por favor ingrese un formato de correo")
	private String correo;
	
	@Column(name = "password", length=20)
	@NotNull
	private String password;
	
	@Column(name = "activo")
	@NotNull
	private boolean isActivo = true;
	
	@ManyToOne(targetEntity = Consultora.class)
	@JoinColumn(name = "id_consultora")
	private Consultora idConsultora;
	
	@Column(name = "usuario_insert")
	private Long usuarioInsert = 111111111L;
	
	@Column(name = "usuario_update")
	private Long usuarioUpdate;
	
	@Column(name = "fecha_insert")
	private Date fechaInsert = Calendar.getInstance().getTime();
	
	@Column(name = "fecha_update")
	private Date fechaUpdate;
	
}