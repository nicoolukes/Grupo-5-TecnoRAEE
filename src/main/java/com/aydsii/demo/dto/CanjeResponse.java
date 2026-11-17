package com.aydsii.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CanjeResponse {

    private Integer idPersona;
    private Integer idCupon;
    private String nombreCupon;
    private Integer cantidadCupones;
    private Integer puntosGastados;
    private Integer puntosRestantes;
    private LocalDate fechaCanje;
}
