package com.aydsii.demo.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import org.hibernate.annotations.Audited.Table;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.aydsii.demo.enums.EstadoConvocatoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "convocatorias")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private int id;
 
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
 
    @Column(name = "nombre", length = 100)
    private String nombre;
 
    @Column(name = "longitud", precision = 10, scale = 7)
    private BigDecimal longitud;
 
    @Column(name = "latitud", precision = 10, scale = 7)
    private BigDecimal latitud;
 
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;
 
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;
 
    @Column(name = "direccion", length = 150)
    private String direccion;
 
    @Column(name = "estado", length = 20)
    private EstadoConvocatoria estado;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
