package com.aydsii.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
import java.io.Serializable;
 
/**
 * Clave primaria compuesta de Detalle_Donaciones (id_donacion, id_raee).
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DetalleDonacionId implements Serializable {
 
    @Column(name = "id_donacion")
    private Integer idDonacion;
 
    @Column(name = "id_raee")
    private Integer idRaee;
}