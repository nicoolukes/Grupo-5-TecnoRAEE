package com.aydsii.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Schema(description = "Convocatoria disponible para consulta")
public class ConvocatoriaDTO {

    @Schema(description = "Nombre de la convocatoria", example = "Jornada de reciclaje tecnológico")
    private String nombre;

    @Schema(description = "Estado de la convocatoria (Pendiente o En curso)", example = "En curso")
    private String estado;

    @Schema(description = "Fecha y hora de inicio", example = "2026-10-30T09:00:00")
    private LocalDateTime fechaHoraInicio;
}
