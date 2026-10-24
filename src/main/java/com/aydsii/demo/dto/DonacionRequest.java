package com.aydsii.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Datos para registrar una donación de RAEEs")
public class DonacionRequest {

    @Schema(description = "Opcional. El sistema registra siempre la fecha actual (CU-01, paso 5)",
            example = "2026-10-23", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate fecha;

    @NotEmpty(message = "La donación debe tener como mínimo un detalle")
    @Valid
    @Schema(description = "Detalles de la donación (mínimo uno)")
    private List<DetalleDonacionRequest> detalles;
}