package com.aydsii.demo.converter;

import java.util.Arrays;

import com.aydsii.demo.enums.EstadoConvocatoria;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EstadoConvocatoriaConverter implements AttributeConverter<EstadoConvocatoria, String> {

    @Override
    public String convertToDatabaseColumn(EstadoConvocatoria estado) {
        return estado == null ? null : estado.getDescripcion();
    }

    @Override
    public EstadoConvocatoria convertToEntityAttribute(String texto) {
        if (texto == null) return null;
        return Arrays.stream(EstadoConvocatoria.values())
                .filter(e -> e.getDescripcion().equalsIgnoreCase(texto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado de convocatoria desconocido: " + texto));
    }
}
