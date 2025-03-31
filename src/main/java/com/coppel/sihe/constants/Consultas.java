package com.coppel.sihe.constants;

public class Consultas {
	
	public static final String UPDATE_ACTIVO_CDEV = "update centro_desarrollo"
													+ " set activo = :isActivo "
													+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_COMP = "update componente"
													+ " set activo = :isActivo "
													+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_CONSULTORA = "update consultora "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_COORDDEV = "update coordinacion_desarrollo"
													+ " set activo = :isActivo "
													+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_EMPLEADO = "update empleado "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_PASSWORD_EMPLEADO = "update empleado "
															+ "set password = :pass "
															+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_LENG_P = "update lenguaje_programacion "
													+ "set activo = :isActivo "
													+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_PERFIL = "update perfil "
													+ "set activo = :isActivo "
													+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_PONDERACION = "update ponderacion "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_REQ_ASIG = "update requerimiento_asignado "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_REQ = "update requerimiento "
												+ "set activo = :isActivo "
												+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_SK_COMP = "update skill_componente_empleado "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_ACTIVO_SK_LENG = "update skill_lenguaje_programacion_empleado "
														+ "set activo = :isActivo "
														+ "where id = :id";
	
	public static final String UPDATE_SOL_APROBADA = "update solicitud "
													+ "set aprobada = :isAprobada "
													+ "where id = :id";
	
	public static final String CONSULTA_EMPLEADOS_X_CDEV = "select * "
															+ "from empleado "
															+ "where id_centro = :idCentro";
	
	public static final String CONSULTA_EMPLEADOS_X_PERFIL = "select * "
															+ "from empleado "
															+ "where id_perfil = :idPerfil";
	
	public static final String VALIDA_EMPLEADO_X_EMAIL = "select count(*)>0 "
														+ "from empleado "
														+ "where UPPER(correo) = UPPER(:correo);";
	
	public static final String CONSULTA_EMP_X_PERFIL_CDEV = "select * "
															+ "from empleado "
															+ "where id_perfil = :idPerfil and "
															+ "id_centro = :idCentro";
	
	public static final String CONSULTA_REQ_ASIG_X_EMPL = "select *"
															+ " from requerimiento_asignado"
															+ " where id_empleado = :idEmpleado";
	
	public static final String INSERT_REQ_ASIG_EMPL =  "insert into requerimiento_asignado "
													+ "(id_requerimiento, id_empleado, centro_origen) "
													+ "values (:idRequerimiento, :idEmpleado, :idCentro);";
	
	public static final String CONSULTA_SK_LENG_X_EMPLEADO = "select *"
															+ " from skill_lenguaje_programacion_empleado"
															+ " where id_empleado = :idEmpleado";
	
	public static final String INSERT_SK_LENG_EMPL = "insert into skill_lenguaje_programacion_empleado"
													+ " (id_empleado, id_lenguaje_programacion, id_ponderacion) "
													+ "values (:idEmpleado, :idLenguaje, :idPonderacion);";
	
	public static final String INSERT_SK_COMP_EMPL = "insert into skill_componente_empleado "
													+ "(id_empleado, id_componente, id_ponderacion) "
													+ "values (:idEmpleado, :idComponente, :idPonderacion);";
	
	public static final String CONSULTA_SK_COMP_X_EMPL = "select * "
														+ "from skill_componente_empleado "
														+ "where id_empleado = :idEmpleado";
	
	public static final String CONSULTA_SOL_X_CDEV = "select * "
													+ "from solicitud "
													+ "where id_centro = :centro";
	
	public static final String CONSULTA_SOL_X_PERFIL = "select * "
														+ "from solicitud"
														+ " where id_perfil = :perfil";
	
	public static final String CONSULTA_SOL_X_PERFIL_CDEV = "select * "
															+ "from solicitud"
															+ " where id_perfil = :perfil and "
															+ "id_centro = :centro";
	
	public static final String VALIDA_SOL_X_EMAIL = "select count(*)>0 "
													+ "from solicitud "
													+ "where UPPER(correo) = UPPER(:correo);";
	
	private Consultas() {
		
	}

}
