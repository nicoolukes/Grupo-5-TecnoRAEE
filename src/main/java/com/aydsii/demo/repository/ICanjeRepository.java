package com.aydsii.demo.repository;

import com.aydsii.demo.entity.InventarioCupon;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface ICanjeRepository extends JpaRepository<InventarioCupon, Integer> {
}