package com.aydsii.demo.service;

import com.aydsii.demo.dto.DetalleDonacionRequest;
import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
import com.aydsii.demo.entity.DetalleDonacion;
import com.aydsii.demo.entity.Donacion;
import com.aydsii.demo.entity.Persona;
import com.aydsii.demo.entity.Raee;
import com.aydsii.demo.exception.BusinessException;
import com.aydsii.demo.exception.PersonaNotFoundException;
import com.aydsii.demo.exception.RaeeNotFoundException;
import com.aydsii.demo.interfaces.ICalculadoraPuntos;
import com.aydsii.demo.interfaces.IDonacionService;
import com.aydsii.demo.mapper.IDonacionMapper;
import com.aydsii.demo.repository.IDonacionRepository;
import com.aydsii.demo.repository.IPersonaRepository;
import com.aydsii.demo.repository.IRaeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class DonacionService implements IDonacionService {
 
    private final IRaeeRepository raeeRepository;
    private final IDonacionRepository donacionRepository;
    private final IDonacionMapper mapper;
    private final ICalculadoraPuntos calculadora;
    private final IPersonaRepository personaRepository;
 
    @Override
    @Transactional
    public DonacionResponse registrarDonacion(DonacionRequest request) {

        Persona persona = personaRepository.findById(request.getIdPersona())
            .orElseThrow(() -> new PersonaNotFoundException("El id de la persona no existe"));

        /* ¿Esto va?
        if (!"Personal Convocatoria".equals(persona.getRol().getNombre())) {
            throw new BusinessException("La persona no tiene permisos para registrar donaciones");
        }
        */

        Map<Integer, Raee> raeePorId = obtenerRaeesValidados(request);
 
        Donacion donacion = mapper.toDonacion(request);
        donacion.setPersona(persona);
 
        for (DetalleDonacionRequest detalleRequest : request.getDetalles()) {
            Raee raee = raeePorId.get(detalleRequest.getId());
            DetalleDonacion detalle = mapper.toDetalle(detalleRequest, raee);
            detalle.setDonacion(donacion);
            donacion.getDetalles().add(detalle);
        }
 
        donacion.setPuntosTotales(calculadora.calcularTotal(donacion));
        donacionRepository.save(donacion);

        return mapper.toResponse(donacion);
    }

    /** FA-01: los ids deben existir y no pueden repetirse (PK compuesta). */
    private Map<Integer, Raee> obtenerRaeesValidados(DonacionRequest request) {
        List<Integer> ids = request.getDetalles().stream()
                .map(DetalleDonacionRequest::getId)
                .toList();

        Set<Integer> idsUnicos = new HashSet<>(ids);
        if (idsUnicos.size() != ids.size()) {
            throw new BusinessException("No se puede repetir un mismo RAEE en los detalles de la donación");
        }

        Map<Integer, Raee> raeePorId = raeeRepository.findAllById(idsUnicos).stream()
                .collect(Collectors.toMap(Raee::getId, Function.identity()));

        if (raeePorId.size() != idsUnicos.size()) {
            throw new RaeeNotFoundException("El id del RAEE no existe");
        }
        return raeePorId;
    }
}