package com.aydsii.demo.exception;

public class ConvocatoriaNoDisponibleException extends RuntimeException {
    
    public ConvocatoriaNoDisponibleException(Throwable cause) {
        super("No es posible recuperar la información de las convocatorias", cause);
    }
}
