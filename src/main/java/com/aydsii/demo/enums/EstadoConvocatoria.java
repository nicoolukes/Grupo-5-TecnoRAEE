package com.aydsii.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EstadoConvocatoria {
    PENDIENTE("Pendiente"),
    EN_CURSO("En curso"),
    FINALIZADA("Finalizada");

    private final String descripcion;
}
