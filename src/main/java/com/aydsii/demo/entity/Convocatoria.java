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
import org.hibernate.annotations.Check;

@Entity 
@Table (name = "Convocatorias")
@Check(constraints = "fecha_hora_fin >= fecha_hora_inicio")
@Getter
@Setter
@NoArgsConstructor
public class Convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private Integer id;
 
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
 
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
 
    @Column(name = "longitud", precision = 10, scale = 7)
    private BigDecimal longitud;
 
    @Column(name = "latitud", precision = 10, scale = 7)
    private BigDecimal latitud;
 
    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;
 
    @Column(name = "fecha_hora_fin", nullable = false)
    private LocalDateTime fechaHoraFin;
 
    @Column(name = "direccion", length = 150)
    private String direccion;
 
    @Convert(converter = EstadoConvocatoriaConverter.class)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoConvocatoria estado;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
}
