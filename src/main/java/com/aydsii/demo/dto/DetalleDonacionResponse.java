package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Detalle de una donación registrada")
public class DetalleDonacionResponse {
 
    @Schema(description = "Nombre del RAEE donado", example = "Notebook")
    private String nombreRAEE;
 
    @Schema(description = "Cantidad donada", example = "2")
    private Integer cantidad;
 
    @Schema(description = "true = Funcional, false = No funcional", example = "true")
    private Boolean estado;
}