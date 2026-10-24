package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aydsii.demo.entity.Convocatoria;

public interface IConvocatoriaRepository extends JpaRepository<Convocatoria, Integer> {
    
}
