package com.aydsii.demo.mapper;

import java.util.List;
import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.entity.Convocatoria;

public class ConvocatoriaMap {
    public ConvocatoriaDTO toDTO(Convocatoria convocatoria) {
        ConvocatoriaDTO dto = new ConvocatoriaDTO();
        dto.setNombre(convocatoria.getNombre());
        dto.setFechaHoraInicio(convocatoria.getFechaHoraInicio());
        dto.setEstado(convocatoria.getEstado());
        return dto;
    }
 
    public List<ConvocatoriaDTO> toDTOList(List<Convocatoria> convocatorias) {
        return convocatorias.stream().map(this::toDTO).toList();
    }
}
