package com.aydsii.demo.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.aydsii.demo.converter.EstadoConvocatoriaConverter;
import com.aydsii.demo.enums.EstadoConvocatoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "Convocatorias")
@Getter
@Setter
@NoArgsConstructor
public class Convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private Integer id;
 
    private String descripcion;
 
    private String nombre;
 
    private BigDecimal longitud;
 
    private BigDecimal latitud;
 
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;
 
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;
 
    private String direccion;
 
    @Convert(converter = EstadoConvocatoriaConverter.class)
    private EstadoConvocatoria estado;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
