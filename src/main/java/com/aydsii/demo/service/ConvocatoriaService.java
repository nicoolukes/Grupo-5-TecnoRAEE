package com.aydsii.demo.service;

import java.util.List;
 
import org.springframework.stereotype.Service;
import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.entity.Convocatoria;
import com.aydsii.demo.interfaces.IConvocatoriaService;
import com.aydsii.demo.repository.IConvocatoriaRepository;
import com.aydsii.demo.mapper.ConvocatoriaMap;

@Service
public class ConvocatoriaService implements IConvocatoriaService {
    private final IConvocatoriaRepository convocatoriaRepository;
    private final ConvocatoriaMap convocatoriaMapper;
 
    public ConvocatoriaService(IConvocatoriaRepository convocatoriaRepository, ConvocatoriaMap convocatoriaMapper) {
        this.convocatoriaRepository = convocatoriaRepository;
        this.convocatoriaMapper = convocatoriaMapper;
    }
 
    
    public List<ConvocatoriaDTO> consultarConvocatorias() {
        List<Convocatoria> convocatorias = convocatoriaRepository.findAll();
        return convocatoriaMapper.toDTOList(convocatorias);
    }
}
