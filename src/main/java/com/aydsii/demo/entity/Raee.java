package com.aydsii.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "RAEES")
@Getter
@Setter
@NoArgsConstructor
public class Raee {
 
    @Id
    @Column(name = "id_raee")
    private Integer id;
 
    private String nombre;
 
    @Column(nullable = false)
    private Integer puntos;
 
    @Column(name = "unidad_medida")
    private String unidadMedida;
 
    @Column(name = "puntos_extra", nullable = false)
    private Integer puntosExtra;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}