package com.aydsii.demo.service;

import com.aydsii.demo.dto.DetalleDonacionRequest;
import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
import com.aydsii.demo.entity.DetalleDonacion;
import com.aydsii.demo.entity.Donacion;
import com.aydsii.demo.entity.Raee;
import com.aydsii.demo.interfaces.ICalculadoraPuntos;
import com.aydsii.demo.interfaces.IDonacionService;
import com.aydsii.demo.mapper.IDonacionMapper;
import com.aydsii.demo.repository.IDonacionRepository;
import com.aydsii.demo.repository.IRaeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class DonacionService implements IDonacionService {
 
    private final IRaeeRepository raeeRepository;
    private final IDonacionRepository donacionRepository;
    private final IDonacionMapper mapper;
    private final ICalculadoraPuntos calculadora;
 
    @Override
    @Transactional
    public DonacionResponse registrarDonacion(DonacionRequest request) {
        List<Integer> ids = request.getDetalles().stream()
                .map(DetalleDonacionRequest::getId)
                .toList();
 
        Map<Integer, Raee> raeePorId = raeeRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(Raee::getId, Function.identity()));
 
        Donacion donacion = mapper.toDonacion(request);
 
        for (DetalleDonacionRequest detalleRequest : request.getDetalles()) {
            Raee raee = raeePorId.get(detalleRequest.getId());
            DetalleDonacion detalle = mapper.toDetalle(detalleRequest, raee);
            donacion.agregarDetalle(detalle);
        }
 
        donacionRepository.save(donacion);
 
        Integer puntosTotales = calculadora.calcularTotal(donacion);
        return mapper.toResponse(donacion, puntosTotales);
    }
}