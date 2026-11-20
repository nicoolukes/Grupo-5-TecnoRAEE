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
import org.hibernate.annotations.Check;
import com.aydsii.demo.enums.EstadoCupon;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Inventario_Cupones")
@Check(constraints = "puntos_gastados >= 0")
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

    @Column(name = "puntos_gastados", nullable = false)
    private Integer puntosGastados;

    @Column(name = "fecha_canje", nullable = false)
    private LocalDate fechaCanje;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 45)
    private EstadoCupon estado;

    // null hasta que el cupón se use
    @Column(name = "fecha_uso", columnDefinition = "DATETIME")
    private LocalDateTime fechaUso;
}
