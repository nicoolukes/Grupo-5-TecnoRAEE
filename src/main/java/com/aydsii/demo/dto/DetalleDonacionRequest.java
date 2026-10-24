package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Detalle de una donación: un RAEE con su cantidad y estado")
public class DetalleDonacionRequest {
 
    @NotNull(message = "El id del RAEE es obligatorio")
    @Schema(description = "Identificador del RAEE donado", example = "1")
    private Integer id;
 
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    @Schema(description = "Cantidad donada (entero mayor a cero)", example = "2")
    private Integer cantidad;
 
    @NotNull(message = "El estado es obligatorio")
    @Schema(description = "Estado del RAEE: true = Funcional, false = No funcional", example = "true")
    private Boolean estado;
}