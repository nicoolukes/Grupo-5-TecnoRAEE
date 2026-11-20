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
import org.hibernate.annotations.Check;
 
@Entity
@Table(name = "RAEES")
@Check(constraints = "puntos >= 0 AND puntos_extra >= 0")
@Getter
@Setter
@NoArgsConstructor
public class Raee {
 
    @Id
    @Column(name = "id_raee")
    private Integer id;
 
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
 
    @Column(name = "puntos", nullable = false)
    private Integer puntos;
 
    @Column(name = "unidad_medida", nullable = false, length = 10)
    private String unidadMedida;
 
    @Column(name = "puntos_extra", nullable = false)
    private Integer puntosExtra;
 
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
}
