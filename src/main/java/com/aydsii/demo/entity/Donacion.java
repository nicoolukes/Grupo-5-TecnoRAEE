package com.aydsii.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 
@Entity
@Table(name = "Donaciones")
@Check(constraints = "puntos_totales >= 0")
@Getter
@Setter
@NoArgsConstructor
public class Donacion {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donacion")
    private Integer id;
 
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "puntos_totales", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer puntosTotales;
 
    @OneToMany(mappedBy = "donacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleDonacion> detalles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
