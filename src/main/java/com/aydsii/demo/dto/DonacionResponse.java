package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import java.time.LocalDate;
import java.util.List;
 
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Donación registrada con sus puntos totales")
public class DonacionResponse {
 
    @Schema(description = "Identificador de la donación", example = "10")
    private Integer id;
 
    @Schema(description = "Fecha de registro", example = "2026-10-23")
    private LocalDate fecha;
 
    @Schema(description = "Puntos totales: suma de cantidad × (puntos + puntos extra si es funcional)", example = "120")
    private Integer puntosTotales;
 
    @Schema(description = "Detalles registrados")
    private List<DetalleDonacionResponse> detalles;
}