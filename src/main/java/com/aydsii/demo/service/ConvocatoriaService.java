package com.aydsii.demo.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.entity.Convocatoria;
import com.aydsii.demo.enums.EstadoConvocatoria;
import com.aydsii.demo.exception.ConvocatoriaNoDisponibleException;
import com.aydsii.demo.interfaces.IConvocatoriaService;
import com.aydsii.demo.repository.IConvocatoriaRepository;

import lombok.RequiredArgsConstructor;

import com.aydsii.demo.mapper.IConvocatoriaMapper;

@Service
@RequiredArgsConstructor
public class ConvocatoriaService implements IConvocatoriaService {

    // RN-02: solo se consultan las convocatorias que no estén finalizadas
    private static final List<EstadoConvocatoria> ESTADOS_DISPONIBLES =
            List.of(EstadoConvocatoria.PENDIENTE, EstadoConvocatoria.EN_CURSO);

    private final IConvocatoriaRepository convocatoriaRepository;
    private final IConvocatoriaMapper convocatoriaMapper;
 
    @Override
    @Transactional (readOnly = true)
    public List<ConvocatoriaDTO> consultarConvocatorias() {
        try {
            List<Convocatoria> convocatorias =
                convocatoriaRepository.findByEstadoInOrderByFechaHoraInicioAsc(ESTADOS_DISPONIBLES);
            return convocatoriaMapper.toDTOList(convocatorias);
        } catch (DataAccessException ex) {
            // Postcondición de fallo de CU-02
            throw new ConvocatoriaNoDisponibleException(ex);
        }
    }
}
