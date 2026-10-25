package com.aydsii.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "Detalle_Donaciones")
@Getter
@Setter
@NoArgsConstructor
public class DetalleDonacion {
 
    @EmbeddedId
    private DetalleDonacionId id = new DetalleDonacionId();
 
    @MapsId("idDonacion")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_donacion")
    private Donacion donacion;
 
    @MapsId("idRaee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raee")
    private Raee raee;
 
    @Column(nullable = false)
    private Integer cantidad;
 
    /** true = "Funcional", false = "No funcional" (RN-04). */
    @Column(nullable = false)
    private Boolean estado;
}