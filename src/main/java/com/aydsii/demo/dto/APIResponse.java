package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Estructura común de todas las respuestas de la API.
 * T define el tipo del contenido de "data" según el endpoint.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Estructura común de respuesta de la API")
public class APIResponse<T> {

    @Schema(description = "Indica si la operación fue exitosa", example = "true")
    private boolean success;

    @Schema(description = "Mensaje descriptivo del resultado", example = "Donación registrada correctamente")
    private String message;

    @Schema(description = "Contenido de la respuesta (null cuando hay error)")
    private T data;

    public static <T> APIResponse<T> ok(String message, T data) {
        return new APIResponse<>(true, message, data);
    }

    public static <T> APIResponse<T> error(String message) {
        return new APIResponse<>(false, message, null);
    }
}