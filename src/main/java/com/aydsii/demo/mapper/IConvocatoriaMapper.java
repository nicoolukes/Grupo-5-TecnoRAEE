package com.aydsii.demo.mapper;

import java.util.List;
import com.aydsii.demo.dto.ConvocatoriaResponse;
import com.aydsii.demo.entity.Convocatoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IConvocatoriaMapper {
 
    // nombre y fechaHoraInicio se mapean solos por nombre; el estado se convierte
    // a su descripción legible ("En curso") en lugar del nombre de la constante del enum.
    @Mapping(target = "estado", source = "estado.descripcion")
    ConvocatoriaResponse toDTO(Convocatoria convocatoria);
 
    List<ConvocatoriaResponse> toDTOList(List<Convocatoria> convocatorias);
}
