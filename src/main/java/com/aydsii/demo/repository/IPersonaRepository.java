package com.aydsii.demo.repository;

import com.aydsii.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
}