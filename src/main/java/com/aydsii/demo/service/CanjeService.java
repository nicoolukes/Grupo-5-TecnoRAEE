package com.aydsii.demo.service;

import com.aydsii.demo.dto.CanjeRequest;
import com.aydsii.demo.dto.CanjeResponse;
import com.aydsii.demo.entity.Cupon;
import com.aydsii.demo.enums.EstadoCupon;
import com.aydsii.demo.entity.InventarioCupon;
import com.aydsii.demo.entity.Persona;
import com.aydsii.demo.exception.BusinessException;
import com.aydsii.demo.exception.CuponNotFoundException;
import com.aydsii.demo.exception.PersonaNotFoundException;
import com.aydsii.demo.interfaces.ICanjeService;
import com.aydsii.demo.repository.ICanjeRepository;
import com.aydsii.demo.repository.ICuponRepository;
import com.aydsii.demo.repository.IPersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CanjeService implements ICanjeService {

    
    private final ICuponRepository cuponRepository;
    private final IPersonaRepository personaRepository;
    private final ICanjeRepository canjeRepository;

    @Override
    @Transactional
    public CanjeResponse canjearPuntosPorCupones(CanjeRequest request) {

        Persona persona = personaRepository.findById(request.getIdPersona())
                .orElseThrow(() -> new PersonaNotFoundException("El id de la persona no existe"));

        Cupon cupon = cuponRepository.findById(request.getIdCupon())
                .orElseThrow(() -> new CuponNotFoundException("El id del cupón no existe"));

        if (!cupon.getActivo()) {
            throw new BusinessException("El cupón no está activo");
        }

        int cantidad = request.getCantidadCupones();
        int puntosTotales = cupon.getCostoCupon() * cantidad;

        if (persona.getPuntos() < puntosTotales) {
            throw new BusinessException("La persona no tiene puntos suficientes para realizar el canje");
        }

        persona.setPuntos(persona.getPuntos() - puntosTotales);
        personaRepository.save(persona); 

        // Una fila de inventario por cada cupon canjeado
        LocalDate hoy = LocalDate.now();
        List<InventarioCupon> cuponesCanjeados = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            InventarioCupon inventario = new InventarioCupon();
            inventario.setPersona(persona);
            inventario.setCupon(cupon);
            inventario.setPuntosGastados(cupon.getCostoCupon());
            inventario.setFechaCanje(hoy);
            inventario.setEstado(EstadoCupon.DISPONIBLE);
            cuponesCanjeados.add(inventario);
        }
        canjeRepository.saveAll(cuponesCanjeados);

        return new CanjeResponse(
                persona.getId(),
                cupon.getIdCupon(),
                cupon.getNombre(),
                cantidad,
                puntosTotales,
                persona.getPuntos(),
                hoy);
    }
}