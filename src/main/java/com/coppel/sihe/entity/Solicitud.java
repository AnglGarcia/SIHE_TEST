package com.coppel.sihe.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "solicitud")
@Table(name = "solicitud")
public class Solicitud {
	
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

	@Column(name ="centro", length=30)
	private String centro;
	
	@Column(name ="perfil", length=30)
	private String perfil;
	
	@Column(name = "lugar_posicion", length=30)
	private String lugarPosicion;
	
	@Column(name = "externo")
	private boolean isExterno;
	
	@Column(name = "correo", length=40)
	@NotNull
	@Email(message="Por favor ingrese un formato de correo")
	private String correo;
	
	@Column(name = "password", length=20)
	@NotNull
	private String password;
	
	@Column(name = "usuario_insert")
	private Long usuarioInsert = 111111111L;
	
	@Column(name = "usuario_update")
	private Long usuarioUpdate;
	
	@Column(name = "fecha_insert")
	private Date fechaInsert = Calendar.getInstance().getTime();
	
	@Column(name = "fecha_update")
	private Date fechaUpdate;
	
	@Column(name = "aprobada")
	@NotNull
	private boolean isAprobada = false;
	
	@Column(name = "empresa")
	private String empresa;

}
