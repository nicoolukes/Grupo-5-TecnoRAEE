package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Datos para registrar una donación de RAEEs")
public class DonacionRequest {

    @NotEmpty(message = "La donación debe tener como mínimo un detalle")
    @Valid
    @Schema(description = "Detalles de la donación (mínimo uno)")
    private List<DetalleDonacionRequest> detalles;

    @NotNull(message = "El id de la persona es obligatorio")
    @Schema(description = "Identificador del personal de convocatoria que registra la donación", example = "3")
    private Integer idPersona;
}