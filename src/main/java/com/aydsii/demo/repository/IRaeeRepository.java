package com.aydsii.demo.repository;

import com.aydsii.demo.entity.Raee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRaeeRepository extends JpaRepository<Raee, Integer> {
    // findAllById(Iterable<Integer> ids) es provisto por JpaRepository
}