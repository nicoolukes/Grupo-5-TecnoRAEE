package com.aydsii.demo.repository;

import com.aydsii.demo.entity.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuponRepository extends JpaRepository<Cupon, Integer> {
    
}
