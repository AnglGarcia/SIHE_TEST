package com.coppel.sihe.constants;


/**
 * Clase de constantes generales dela aplicacion.
 */
public class Constants {
  
  /**
   * Constante que contiene el valor del base path.
   */
  public static final String BASE_PATH = "${api.uri.basePath}";
  
  public static final String MAPPING = "${api.uri.domain.context.mapping}";
  
  public static final String MAPPING_CENTROSDEV = "${api.uri.domain.context.mapping}${api.uri.sihe.centrosdev}";
  
  public static final String MAPPING_COMPONENTES = "${api.uri.domain.context.mapping}${api.uri.sihe.componente}";
  
  public static final String MAPPING_CONSULTORA = "${api.uri.domain.context.mapping}${api.uri.sihe.consultora}";
  
  public static final String MAPPING_COORDINACIONDEV = "${api.uri.domain.context.mapping}${api.uri.sihe.coordinacionesdev}";
  
  public static final String MAPPING_LENGUAJES = "${api.uri.domain.context.mapping}${api.uri.sihe.lenguajes}";
  
  public static final String MAPPING_PERFILES = "${api.uri.domain.context.mapping}${api.uri.sihe.perfiles}";
  
  public static final String MAPPING_PONDERACION = "${api.uri.domain.context.mapping}${api.uri.sihe.ponderaciones}";
  
  public static final String MAPPING_REQUERIMIENTOS = "${api.uri.domain.context.mapping}${api.uri.sihe.requerimientos}";
  
  public static final String MAPPING_EMPLEADOS = "${api.uri.domain.context.mapping}${api.uri.sihe.empleados}";
  
  public static final String MAPPING_SOLICITUDES = "${api.uri.domain.context.mapping}${api.uri.sihe.solicitudes}";
    
  public static final String MAPPING_REQ_ASIGNADOS = "${api.uri.domain.context.mapping}${api.uri.sihe.reqasignado}";
  
  public static final String MAPPING_SKILL_COMP = "${api.uri.domain.context.mapping}${api.uri.sihe.skcomponente}";
  
  public static final String MAPPING_SKILL_LENG = "${api.uri.domain.context.mapping}${api.uri.sihe.sklenguaje}";
  
  /**
   * Constante que indica si se imprimiran headers o no.
   */
  public static final String PRINT_HEADERS = "${api.headers.print}";

  /**
   * Constante que contiene la cadena OK.
   */
  public static final String OK = "OK";

  /**
   * Constante que representa la cadena JSON.
   */
  public static final String JSON_STRING = "JSON";

  /**
   * Constante que representa la cadena N.
   */
  public static final String N = "N";
  
  /**
   * Constante que representa la cadena Y.
   */
  public static final String Y = "Y";

  /**
   * Constante que representa el tipo de error.
   */
  public static final String ERROR_TYPE = "type: {}";

  /**
   * Constante que representa el codigo de error.
   */
  public static final String ERROR_CODE = "code: {}";

  /**
   * Constante que representa el detalle del error.
   */
  public static final String ERROR_DETAILS = "details: {}";

  /**
   * Constante que representa la URI invocada.
   */
  public static final String ERROR_LOCATION = "location: {}";

  /**
   * Constante que representa informacion adicional del error.
   */
  public static final String ERROR_MORE_INFORMATION = "moreInfo: {}";

  /**
   * Constante usada como llave para el atributo uuid header.
   */
  public static final String UUID_MDC_LABEL = "mdc.uuid";

  /**
   * Constante para limprimir los headers de la peticion.
   */
  public static final String MSG_TO_LOG_HEADER = "[{} : {}]";

  /**
   * Constante para la llave req.t0 que almacenara el momento de inicio de la peticion.
   */
  public static final String T0_REQ_ATTRIBUTE = "req.t0";

  /**
   * Constante para mostrar el inicio de la peticion.
   */
  public static final String START_REQUEST = "Inicia Llamado [{}]";

  /**
   * Constante para mostrar el tiempo de petición y respuesta.
   */
  public static final String TIME_ELAPSED_MESSAGE =
      "Time elapsed for request/response roundtrip [{}], {} ms";

  /**
   * Constante que repsenta la cadena {}.
   */
  public static final String MSG_CURLY_BRACKETS = "{}";

  /**
   * Constante que representa un mensaje de error cuando no trae cuerpo.
   */
  public static final String MSG_ERROR_RESPONSE_HAS_NO_BODY =
      "Failed to parse the playload: Response has no body.";

  /**
   * Constante que representa un mensaje de error cuando el body de error no corresponde con el pre
   * definido.
   */
  public static final String MSG_ERROR_FORMAT =
      "Failed to parse the playload. The format of the message does not "
          + "correspond with the predefined {}";

  /**
   * Constante del mensaje de estatus de la peticion.
   */
  public static final String MSG_STATUS = "status";

  /**
   * Constante del mensaje de la petición.
   */
  public static final String MSG_REQUEST = "request";

  /**
   * Constante del mensaje de la respuesta.
   */
  public static final String MSG_RESPONSE = "response";

  /**
   * Constante de los detalles de error del cliente feign.
   */
  public static final String ERROR_FEIGN_DETAILS = "Error feign details {}";

  /**
   * Constante que representa la cadena type.
   */
  public static final String ERROR_RESPONSE_TYPE = "type";

  /**
   * Constante que representa la cadena code.
   */
  public static final String ERROR_RESPONSE_CODE = "code";

  /**
   * Constante que representa la cadena details.
   */
  public static final String ERROR_RESPONSE_DETAILS = "details";

  /**
   * Constante que representa la cadena location.
   */
  public static final String ERROR_RESPONSE_LOCATION = "location";

  /**
   * Constante que representa la cadena moreInfo.
   */
  public static final String ERROR_RESPONSE_MORE_INFORMATION = "moreInfo";

  /**
   * Constante que representa la cadena timestamp.
   */
  public static final String ERROR_RESPONSE_TIMESTAMP = "timestamp";

  /**
   * Constante que representa el nombre de la excepcion DownstreamException.
   */
  public static final String DOWNSTREAMEXCEPTION = "DownstreamException";

  /**
   * Constante que representa el header uuid.
   */
  public static final String UUID = "uuid";

  /**
   * Constante que representa el header Content-Type.
   */
  public static final String CONTENT_TYPE = "Content-Type";

  /**
   * Constante que representa el header Accept.
   */
  public static final String ACCEPT = "Accept";

  /**
   * Constante que representa el header Authorization.
   */
  public static final String AUTHORIZATION = "Authorization";
  
  /**
   * Constante que representa el header deviceId.
   */
  public static final String DEVICE_ID = "deviceId";
  
  /**
   * Constante que representa el header client_id.
   */
  public static final String CLIENT_ID = "client_id";
  
  /**
   * Constante que representa el header Accept-Language.
   */
  public static final String ACCEPT_LANGUAGE = "Accept-Language";
  
  /**
   * Constante que representa el header Host.
   */
  public static final String HOST = "Host";
  
  /**
   * Constante que representa el header User-Agent.
   */
  public static final String USER_AGENT = "User-Agent";
  
  /**
   * Constante que representa el header Content-Encoding.
   */
  public static final String CONTENT_ENCODING = "Content-Encoding";
  
  /**
   * Constante que representa el header Content-Language.
   */
  public static final String CONTENT_LANGUAGE = "Content-Language";
  
  /**
   * Constante que representa el header Content-Length.
   */
  public static final String CONTENT_LENGTH = "Content-Length";
  
  /**
   * Constante que representa el header Content-MD5.
   */
  public static final String CONTENT_MD5 = "Content-MD5";
  
  /**
   * Constante que representa el header Accept-Charset.
   */
  public static final String ACCEPT_CHARSET = "Accept-Charset";
  
  /**
   * Constante que representa el header Date.
   */
  public static final String DATE = "Date";
  
  /**
   * Constante que representa el header Accept-Encoding.
   */
  public static final String ACCEPT_ENCODING = "Accept-Encoding";
  
  /**
   * Constante que representa el header ChannelId.
   */
  public static final String CHANNEL_ID = "channel_id";
  
  /**
   * Constante utilizada para mostrar un mensaje acerca de una petición mal formada.
   */
  public static final String BAD_REQUEST = "Bad Request";

  /**
   * Constante utilizada para mostrar un mensaje acerca de una petición no autorizada.
   */
  public static final String UNAUTHORIZED = "Unauthorized";

  /**
   * Constante utilizada para mostrar un mensaje acerca de recurso que no pudo ser encontrado.
   */
  public static final String RESOURCE_NOT_FOUND = "Resource not found";

  /**
   * Constante utilizada para mostrar un mensaje acerca de un Internal server error.
   */
  public static final String INTERNAL_ERROR = "Internal server error";
  
  /**
   * Constante utilizada para mostrar el status code 200.
   */
  public static final int CODE_OK = 200;
  /**
   * Constante utilizada para mostrar el status code 400.
   */
  public static final int CODE_BAD_REQUEST = 400;
  /**
   * Constante utilizada para mostrar el status code 401.
   */
  public static final int CODE_UNAUTHORIZED = 401;
  /**
   * Constante utilizada para mostrar el status code 403.
   */
  public static final int CODE_ACCESS_NOT_CONFIGURED = 403;
  /**
   * Constante utilizada para mostrar el status code 404.
   */
  public static final int CODE_RESOURCE_NOT_FOUND = 404;
  /**
   * Constante utilizada para mostrar el status code 422.
   */
  public static final int CODE_BUSINESS_VALIDATION_FAILED = 422;
  /**
   * Constante utilizada para mostrar el status code 500.
   */
  public static final int CODE_INTERNAL_ERROR = 500;

  /**
   * Constante que representa el status HTTP por default.
   */
  public static final int DEFAULT_STATUS_HTTP = 0;
  
  /**
   * Constantes utilizadas para el envio de correo
   */
  public static final String FROM_EMAIL_ADDRESS = "${email.from.address}";
  
  public static final String EMAIL_RESET_PASSWORD_SUBJECT = "Restablecer contraseña";
  
  public static final String EMAIL_RESET_PASSWORD_TEMPLATE = "ResetPassEmailTemplate";
  
  /**
   * Constructor privado para evitar que la clase ea instanciada.
   */
  private Constants() {

  }

}
