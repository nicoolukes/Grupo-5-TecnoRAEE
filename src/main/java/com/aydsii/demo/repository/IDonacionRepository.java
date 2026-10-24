package com.aydsii.demo.repository;

import com.aydsii.demo.entity.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface IDonacionRepository extends JpaRepository<Donacion, Integer> {
}