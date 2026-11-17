package com.aydsii.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CanjeRequest {

    @NotNull(message = "El id de la persona es obligatorio")
    private Integer idPersona;

    @NotNull(message = "El id del cupón es obligatorio")
    private Integer idCupon;

    @NotNull(message = "La cantidad de cupones es obligatoria")
    @Min(value = 1, message = "La cantidad de cupones debe ser mayor a 0")
    private Integer cantidadCupones;
}
