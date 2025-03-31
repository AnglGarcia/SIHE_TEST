create table if not exists coordinacion_desarrollo
(
	id integer not null
		primary key,
	descripcion varchar(255) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);

create table if not exists centro_desarrollo
(
	id integer not null
		primary key,
	descripcion varchar(255) not null,
	id_coordinacion_desarrollo bigint,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint fk_coord_dev foreign key (id_coordinacion_desarrollo) references coordinacion_desarrollo(id) on delete restrict on update cascade
);

create sequence if not exists leng_prog_seq as integer INCREMENT 1 START WITH 1;
create table if not exists lenguaje_programacion
(
	id integer not null default nextval('leng_prog_seq')
		primary key,
	descripcion varchar(255) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);
ALTER SEQUENCE leng_prog_seq OWNED BY lenguaje_programacion.id;

create sequence if not exists perfil_seq as integer INCREMENT 1 START WITH 1;
create table if not exists perfil
(
	id integer not null default nextval('perfil_seq')
		primary key,
	descripcion varchar(255) not null,
	su boolean default false not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);
ALTER SEQUENCE perfil_seq OWNED BY perfil.id;

create sequence if not exists requerimiento_seq as integer INCREMENT 1 START WITH 1;
create table if not exists requerimiento
(
	id integer not null default nextval('requerimiento_seq')
		primary key,
	descripcion varchar(255) not null,
	codigo varchar(100) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint uq_codigo unique (codigo)
);
alter sequence requerimiento_seq owned by requerimiento.id;

create sequence if not exists componente_seq as integer INCREMENT 1 START WITH 1;
create table if not exists componente
(
	id integer not null default nextval('componente_seq')
		primary key,
	descripcion varchar(255) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);
alter sequence componente_seq owned by componente.id;

create sequence if not exists ponderacion_seq as integer INCREMENT 1 START WITH 1;
create table if not exists ponderacion
(
	id integer not null default nextval('ponderacion_seq')
		primary key,
	descripcion varchar(255) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);
alter sequence ponderacion_seq owned by ponderacion.id;

create sequence if not exists consultora_seq as integer INCREMENT 1 START WITH 1;
create table if not exists consultora
(
	id integer not null default nextval('consultora_seq')
		primary key,
	descripcion varchar(255) not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL
);
alter sequence consultora_seq owned by consultora.id;


create table if not exists empleado
(
	id bigint not null
		primary key,
	primer_nombre varchar(255) not null,
	segundo_nombre varchar(255) null,
	apellido_paterno varchar(255)not null,
	apellido_materno varchar(255),
	correo varchar(255) not null,
	password varchar(255) not null,
	externo boolean default false,
	id_centro bigint,
	id_perfil bigint,
	lugar_posicion varchar(255),
	id_consultora bigint,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint uq_emp_correo unique (correo),
	constraint fk_centro_dev foreign key (id_centro) references centro_desarrollo (id) on delete restrict on update cascade,
	constraint fk_perfil foreign key (id_perfil) references perfil (id) on delete restrict on update cascade,
	constraint fk_consultora foreign key (id_consultora) references consultora (id) on delete restrict on update cascade
);

create table if not exists solicitud (
    id bigint NOT NULL primary key,
	primer_nombre varchar(255) NOT NULL,
    segundo_nombre varchar(255) NULL,
	apellido_paterno varchar(255) NOT NULL,
    apellido_materno varchar(255) NULL,
    correo varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
    externo boolean default false,
    id_centro bigint,
	id_perfil bigint,
	id_consultora bigint,
    lugar_posicion varchar(255) NULL,
	aprobada boolean NOT NULL DEFAULT true,
    usuario_insert bigint NOT NULL DEFAULT 111111111,
    usuario_update bigint NULL,
    fecha_insert timestamp NOT NULL DEFAULT NOW(),
    fecha_update timestamp NULL,
	constraint uq_solic_correo unique (correo),
	constraint fk_centro_dev foreign key (id_centro) references centro_desarrollo (id) on delete restrict on update cascade,
	constraint fk_perfil foreign key (id_perfil) references perfil (id) on delete restrict on update cascade,
	constraint fk_consultora foreign key (id_consultora) references consultora (id) on delete restrict on update cascade
);

create sequence sk_com_emp_seq as bigint INCREMENT 1 START WITH 1;
create table if not exists skill_componente_empleado
(
	id bigint not null default nextval('sk_com_emp_seq') 
				primary key,
	id_empleado bigint not null,
	id_componente integer not null,
	id_ponderacion integer not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint uq_com_emp unique (id_empleado, id_componente),
	constraint fk_empleado foreign key (id_empleado) references empleado (id) on delete restrict on update cascade,
	constraint fk_componente foreign key (id_componente) references componente (id) on delete restrict on update cascade,
	constraint fk_ponderacion foreign key (id_ponderacion) references ponderacion (id) on delete restrict on update cascade
);
alter sequence sk_com_emp_seq owned by skill_componente_empleado.id;

create sequence sk_leng_p_emp_seq as bigint INCREMENT 1 START WITH 1;
create table if not exists skill_lenguaje_programacion_empleado
(	
	id bigint not null default nextval('sk_leng_p_emp_seq') 
			  primary key,
	id_empleado bigint not null,
	id_lenguaje_programacion integer not null,
	id_ponderacion integer not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint uq_len_emp unique (id_empleado, id_lenguaje_programacion),
	constraint fk_empleado foreign key (id_empleado) references empleado (id) on delete restrict on update cascade,
	constraint fk_leng_programacion foreign key (id_lenguaje_programacion) references lenguaje_programacion (id) on delete restrict on update cascade,
	constraint fk_ponderacion foreign key (id_ponderacion) references ponderacion (id) on delete restrict on update cascade
);
alter sequence sk_leng_p_emp_seq owned by skill_lenguaje_programacion_empleado.id;

create sequence req_asignado_seq as bigint INCREMENT 1 START WITH 1;
create table if not exists requerimiento_asignado
(
	id bigint not null default nextval('req_asignado_seq')
			  primary key,
	id_requerimiento integer not null,
	id_empleado bigint not null,
	id_centro integer not null,
	activo boolean default true not null,
	usuario_insert bigint NOT NULL DEFAULT 111111111,
	usuario_update bigint NULL,
	fecha_insert timestamp NOT NULL DEFAULT NOW(),
	fecha_update timestamp NULL,
	constraint uq_req_emp unique (id_requerimiento, id_empleado),
	constraint fk_requerimiento foreign key (id_requerimiento) references requerimiento (id) on delete restrict on update cascade,
	constraint fk_empleado foreign key (id_empleado) references empleado (id) on delete restrict on update cascade,
	constraint fk_centro foreign key (id_centro) references centro_desarrollo (id) on delete restrict on update cascade
);
alter sequence req_asignado_seq owned by requerimiento_asignado.id;

create table if not exists authority
(
	id bigint not null
		primary key,
	authority varchar(255)
);

create table if not exists authorities_users
(
	usuario_id bigint not null,
	authority_id bigint not null,
	primary key (usuario_id, authority_id),
	constraint fk_aut_empleado foreign key (usuario_id) references empleado (id) on delete restrict on update cascade,
	constraint fk_requerimiento foreign key (authority_id) references authority (id) on delete restrict on update cascade
);

