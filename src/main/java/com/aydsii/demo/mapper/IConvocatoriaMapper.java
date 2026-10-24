package com.aydsii.demo.mapper;

import java.util.List;
import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.entity.Convocatoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IConvocatoriaMapper {
 
    // Los nombres de los campos coinciden (nombre, fechaHoraInicio, estado),
    // así que MapStruct los mapea solo, sin necesidad de @Mapping.
    ConvocatoriaDTO toDTO(Convocatoria convocatoria);
 
    List<ConvocatoriaDTO> toDTOList(List<Convocatoria> convocatorias);
}
