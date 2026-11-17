package com.aydsii.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.aydsii.demo.enums.EstadoCupon;
import java.time.LocalDate;

@Entity
@Table(name = "inventario_cupones") //chequear mismo nombre que el DER
@Getter
@Setter
@NoArgsConstructor
public class InventarioCupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventarioCupon")
    private Integer idInventarioCupon;

    // Sin @MapsId: la PK es propia (IDENTITY), no compuesta
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cupon", nullable = false)
    private Cupon cupon;

    //chequear que respete nombre con el DER
    @Column(nullable = false)
     @JoinColumn(name = "puntos_gastados")
    private Integer puntosGastados;

    @Column(nullable = false)
    @JoinColumn(name = "fecha_canje")
    private LocalDate fechaCanje;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCupon estado;

    // null hasta que el cupón se use
    @JoinColumn(name = "fecha_uso")
    private LocalDate fechaUso;
}
