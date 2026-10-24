package com.aydsii.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.aydsii.demo.enums.EstadoConvocatoria;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class ConvocatoriaDTO {
    private String nombre;
    private LocalDateTime fechaHoraInicio;
    private EstadoConvocatoria estado; 
}
