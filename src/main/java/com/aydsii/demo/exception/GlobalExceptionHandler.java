package com.aydsii.demo.exception;

import com.aydsii.demo.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    400 - Bad Request.
    Falla alguna validación de los DTOs (@NotNull, @Min, @NotEmpty).
    Devuelve los mensajes de los campos inválidos (FA-02, RN-01 a RN-03).
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .distinct()
                .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.error(mensaje));
    }

    /*
    400 - Bad Request.
    El JSON está mal formado o un campo tiene un tipo incorrecto
    (por ejemplo, cantidad = "abc" o 2.5 en un Integer).
    */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse<Void>> handleUnreadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(APIResponse.error("El cuerpo de la solicitud es inválido o tiene un formato incorrecto"));
    }

    /*
    404 - Not Found.
    El id de RAEE de algún detalle no existe en el sistema (FA-01 de CU-01).
    */
    @ExceptionHandler(RaeeNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleRaeeNotFound(RaeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error(ex.getMessage()));
    }

    /*
    400 - Bad Request.
    Se violó una regla de negocio (por ejemplo, RAEE repetido en los detalles).
    */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIResponse<Void>> handleBusiness(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.error(ex.getMessage()));
    }

    /*
    404 - Not Found.
    La ruta solicitada no corresponde a ningún endpoint de la API.
    */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleNoResource(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(APIResponse.error("El recurso solicitado no existe"));
    }

    /*
    405 - Method Not Allowed.
    El endpoint existe pero no admite el verbo HTTP usado (por ejemplo, GET sobre un POST).
    */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse<Void>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(APIResponse.error("Método no permitido para este recurso"));
    }

    /*
    415 - Unsupported Media Type.
    La solicitud se envió con un Content-Type que la API no acepta (se espera application/json).
    */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<APIResponse<Void>> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(APIResponse.error("Tipo de contenido no soportado, se espera application/json"));
    }

    /*
    404 - Not Found.
    El id de la persona que registra la donación no existe en el sistema.
    */
    @ExceptionHandler(PersonaNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handlePersonaNotFound(PersonaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error(ex.getMessage()));
    }

    /*
    403 - Forbidden.
    La persona no tiene el rol "Personal Convocatoria" y no puede cargar donaciones (RN-06).
    */
    @ExceptionHandler(PersonaSinPermisoException.class)
    public ResponseEntity<APIResponse<Void>> handlePersonaSinPermiso(PersonaSinPermisoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(APIResponse.error(ex.getMessage()));
    }

    /*
    500 - Internal Server Error.
    Cualquier error inesperado que no tenga un handler específico.
    Se loguea el detalle y al cliente solo se le devuelve un mensaje genérico.
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleGeneric(Exception ex) {
        log.error("Error no controlado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponse.error("Error interno del servidor"));
    }
}