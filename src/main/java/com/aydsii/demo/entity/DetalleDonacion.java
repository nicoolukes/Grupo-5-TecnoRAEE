package com.aydsii.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.CheckConstraint;
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
    @JoinColumn(name = "id_donacion", nullable = false)
    private Donacion donacion;
 
    @MapsId("idRaee")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raee", nullable = false)
    private Raee raee;
 
    @Column(name = "cantidad", nullable = false,
            check = @CheckConstraint(constraint = "cantidad > 0"))
    private Integer cantidad;
 
    /** true = funciona (Funcional), false = no funciona (No funcional). */
    @Column(name = "funciona", nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean funciona;
}
