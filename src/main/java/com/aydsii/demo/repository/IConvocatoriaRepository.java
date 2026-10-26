package com.aydsii.demo.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.entity.Convocatoria;
import com.aydsii.demo.enums.EstadoConvocatoria;

public interface IConvocatoriaRepository extends JpaRepository<Convocatoria, Integer> {
    List<Convocatoria> findByEstadoInOrderByFechaHoraInicioAsc(List<EstadoConvocatoria> estados);
}
