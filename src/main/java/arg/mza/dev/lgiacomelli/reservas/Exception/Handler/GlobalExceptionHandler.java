package arg.mza.dev.lgiacomelli.reservas.Exception.Handler;

import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceAlreadyExistsException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceInvalidException;
import arg.mza.dev.lgiacomelli.reservas.Exception.Generics.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());
        return buildError(HttpStatus.NOT_FOUND, "Recurso no encontrado",
                ex.getMessage(), request);
    }

    @ExceptionHandler(ResourceInvalidException.class)
    public ResponseEntity<ApiError> handleInvalidResource(
            ResourceInvalidException ex, WebRequest request) {
        log.warn("Recurso inválido: {}", ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, "Recurso inválido",
                ex.getMessage(), request);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleResourceAlreadyExists(
            ResourceAlreadyExistsException ex, WebRequest request) {
        log.warn("Recurso ya existe: {}", ex.getMessage());
        return buildError(HttpStatus.CONFLICT, "Recurso duplicado",
                ex.getMessage(), request);
    }

    /* ======================================================
       VALIDACIONES DTO (@Valid)
       ====================================================== */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        log.warn("Errores de validación: {}", ex.getBindingResult().getFieldErrors().size());

        String errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Errores de validación",
                "Los datos enviados no cumplen con las validaciones requeridas",
                LocalDateTime.now(),
                getPath(request),
                errores
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /* ======================================================
       FALLBACK GENERAL
       ====================================================== */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception ex, WebRequest request) {
        // Log completo del error para debugging
        log.error("Error inesperado: ", ex);

        // Respuesta genérica para el cliente (sin detalles sensibles)
        return buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                "Ha ocurrido un error inesperado. Por favor contacte al administrador.",
                request
        );
    }

    /* ======================================================
       MÉTODOS AUXILIARES
       ====================================================== */

    private ResponseEntity<ApiError> buildError(
            HttpStatus status, String error, String message, WebRequest request) {
        ApiError apiError = new ApiError(
                status.value(),
                error,
                message,
                LocalDateTime.now(),
                getPath(request),
                null
        );
        return new ResponseEntity<>(apiError, status);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}
