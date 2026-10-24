package com.aydsii.demo.mapper;

import com.aydsii.demo.dto.DetalleDonacionRequest;
import com.aydsii.demo.dto.DetalleDonacionResponse;
import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
import com.aydsii.demo.entity.DetalleDonacion;
import com.aydsii.demo.entity.Donacion;
import com.aydsii.demo.entity.Raee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
 
@Mapper(componentModel = "spring")
public interface IDonacionMapper {
 
    /** La fecha siempre es la actual; los detalles se agregan luego con agregarDetalle(). */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "detalles", ignore = true)
    @Mapping(target = "persona", ignore = true)
    Donacion toDonacion(DonacionRequest request);
 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "donacion", ignore = true)
    @Mapping(target = "raee", source = "raee")
    @Mapping(target = "cantidad", source = "request.cantidad")
    @Mapping(target = "estado", source = "request.estado")
    DetalleDonacion toDetalle(DetalleDonacionRequest request, Raee raee);
 
    @Mapping(target = "id", source = "donacion.id")
    @Mapping(target = "fecha", source = "donacion.fecha")
    @Mapping(target = "detalles", source = "donacion.detalles")
    @Mapping(target = "puntosTotales", source = "puntosTotales")
    DonacionResponse toResponse(Donacion donacion, Integer puntosTotales);
 
    @Mapping(target = "nombreRAEE", source = "raee.nombre")
    DetalleDonacionResponse toDetalleResponse(DetalleDonacion detalleDonacion);
}