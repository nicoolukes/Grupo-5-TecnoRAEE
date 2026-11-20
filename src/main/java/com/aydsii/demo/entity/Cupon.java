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
import jakarta.persistence.MapsId;
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
@Table(name = "Cupones")
@Check(constraints = "costo_cupon > 0")
@Getter
@Setter
@NoArgsConstructor
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cupon")
    private Integer idCupon;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "costo_cupon", nullable = false)
    private Integer costoCupon;

    @Column(name = "valor_descuento", nullable = false, columnDefinition = "INT")
    private Integer valorDescuento;

    @Column(name = "activo", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean activo;
}
