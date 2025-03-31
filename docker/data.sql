  -- Insertar datos en la tabla 'empleados'
INSERT INTO empleado (id, primer_nombre, apellido_paterno, correo, password, activo )
VALUES (111111111, 'Administrador', 'SIHE', 'sihe@coppel.com', 'g5w7a5g5/9qaM1eDOIVSOQ==', true);

  -- Insertar datos en la tabla 'coordinacion_desarrollo'
INSERT INTO coordinacion_desarrollo (id, descripcion)
VALUES  
  (1234, 'Desarrollo de Aplicaciones Web'),
  (2345, 'Desarrollo de Aplicaciones Móviles'),
  (3456, 'Desarrollo de Sistemas Empresariales'),
  (4567, 'Microservicios'),
  (5678, 'Desarrollo de Juegos');
  
  -- Insertar datos en la tabla 'centros_desarrollo'
INSERT INTO centro_desarrollo (id, descripcion, id_coordinacion_desarrollo)
VALUES
  (012, 'Centro de Desarrollo A', 1234),
  (345, 'Centro de Desarrollo B', 1234),
  (678, 'Centro de Desarrollo C', 2345),
  (901, 'Centro de Desarrollo D', 4567),
  (234, 'Centro de Desarrollo E', 3456);
  
  -- Insertar datos en la tabla 'lenguajes_programacion'
INSERT INTO lenguaje_programacion (descripcion)
VALUES
  ( 'Java'),
  ( 'Python'),
  ( 'C#'),
  ( 'JavaScript'),
  ( 'Ruby'),
  ( 'Swift'), 
  ( 'PHP'),
  ( 'TypeScript');
  
  -- Insertar datos en la tabla 'perfiles'
INSERT INTO perfil (descripcion, su)
VALUES
  ('Desarrollador Junior', false),
  ('Desarrollador Senior', false),
  ('Administrador de Sistemas', true),
  ('Tester', true),
  ('Analista de Datos', false),
  ('Diseñador UX/UI', false),
  ('Gerente de Proyectos', true);
  
  -- Insertar datos en la tabla 'requerimientos'
INSERT INTO requerimiento (codigo, descripcion)
VALUES
  ('RQM-1', 'Desarrollo de una aplicación web para cliente X'),
  ('RQM-2', 'Mantenimiento de la aplicación móvil para cliente X'),
  ('RQM-31', 'Desarrollo de una aplicación móvil para cliente X'),
  ('RQM-4', 'Migración de base de datos existente'),
  ('RQM-5', 'Diseño de interfaz de usuario para sitio web'),
  ('RQM-6', 'Optimización de rendimiento de la aplicación'),
  ('RQM-7', 'Implementación de seguridad en la aplicación'),
  ('RQM-8', 'Desarrollo de un módulo de pago en línea'),
  ('RQM-9', 'Creación de informes y paneles de control'),
  ('RQM-10', 'Integración con servicios de terceros');
  
  -- Insertar datos en la tabla 'componentes'
INSERT INTO componente (descripcion)
VALUES
  ('Front-end'),
  ('Back-end'),
  ('Base de Datos'),
  ('Front-end móvil'),
  ('API de Backend'),
  ('Diseño de Interfaces');
  
  -- Insertar datos en la tabla 'ponderaciones'
INSERT INTO ponderacion (descripcion)
VALUES
  ('Baja'),
  ('Media'),
  ('Alta'),
  ('Nula');
  
INSERT INTO consultora(descripcion)
VALUES 
	('TASF'),
	('KAIROS'),
	('NEORIS');
  
  
  -- Insertar datos en la tabla 'authority'
INSERT INTO authority (id, authority)
VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_ADMIN'),
  (3, 'ROLE_MANAGER'),
  (4, 'ROLE_DEVELOPER'),
  (5, 'ROLE_TESTER');

-- Insertar datos en la tabla 'authorities_users'
INSERT INTO authorities_users (usuario_id, authority_id)
VALUES
  (1, 1),
  (1, 2);



  